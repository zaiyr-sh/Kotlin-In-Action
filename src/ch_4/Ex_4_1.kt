package ch_4

import ch_4.Expr.Sum
import ch_4.Expr.Num
import java.io.Serializable
import java.lang.IllegalArgumentException

fun main() {
    // 4.1.1
    println("---------------4.1.1--------------")
    Button().click()
    Button().setFocus(true)
    Button().showOff()

    // 4.1.5
    println("---------------4.1.5--------------")
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

// 4.1.1
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!") // Method with a default implementation
}

interface Focusable {
    fun setFocus(b: Boolean) {
        println("I ${if (b) "got" else "lost"} focus.")
    }
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        // You must provide an explicit implementation
        // if more than one implementation for the same member is inherited.
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

// 4.1.2
// This class is open: others can inherit from it.
open class RichButton : Clickable {

    // This function is final: you can't override it in a subclass.
    fun disable() {}

    // This function is open: you may override it in a subclass.
    open fun animate() {}

    // This function overrides an open function and is open as well.
    override fun click() {}

    // "final" isn't redundant here because "override" without "final" implies being open.
    final override fun showOff() {
        super.showOff()
    }
}

// This class is abstract: you can't create an instance of it.
abstract class Animated {

    // This function is abstract: it doesn't have an implementation and must be overridden in subclasses
    abstract fun animate()

    // Non-abstract functions in abstract classes aren't open by default but can be marked as open
    open fun stopAnimating() {}
    fun animaTwice() {}
}

// 4.1.4
interface State: Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}

    // This class is an analogue of a static nested class in Java.
    class ButtonState: State {}
}

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}

// 4.1.5
// Mark a base class as sealed ...
sealed class Expr {
    // ... and list all possible subclasses as nested classes.
    class Num(val value: Int): Expr()
    class Sum(val left: Expr, val right: Expr): Expr()
}

fun eval(e: Expr): Int =
    // The "when" expression covers all possible cases, so no "else" branch is needed
    when(e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
    }
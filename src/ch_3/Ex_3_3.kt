package ch_3

fun main() {
    println("Hello".lastChar())

    // 3.3.3
    println("---------------3.3.3--------------")
    val list = listOf(1,2,3)
    println(list.jointToString())
    println(list.jointToString(separator = ";", prefix = "(", postfix = ")"))

    // 3.3.4
    println("---------------3.3.4--------------")
    val view: View = Button()
    view.showOff()

    // 3.3.5
    println("---------------3.3.5--------------")
    println("World".lastChar)
    val sb = StringBuilder("Hello, World.")
    sb.lastChar = '!'
    println(sb)
}

fun String.lastChar(): Char = this[length - 1]

// 3.3.3
fun <T> Collection<T>.jointToString(
    separator: String = "",
    prefix: String = "",
    postfix: String = ""

): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 3.3.4
open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I am a view")

fun Button.showOff() = println("I am a button")

// 3.3.5
val String.lastChar: Char
    get() = this[length - 1]

var StringBuilder.lastChar: Char
    get() = this[length - 1]
    set(value: Char) {
        setCharAt(length - 1, value)
    }

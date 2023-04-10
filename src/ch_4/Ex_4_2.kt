package ch_4

import javax.naming.Context
import javax.swing.text.AttributeSet
import javax.swing.text.SimpleAttributeSet

fun main() {
    // 4.2.3
    println("---------------4.2.3--------------")
    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)

    // 4.2.4
    println("---------------4.2.4--------------")
    val user  = User3("Alice")
    user.address = "ap. 123, 80678"
    user.address = "ap. 864, 12345"

    // 4.2.5
    println("---------------4.2.5--------------")
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hello")
    println(lengthCounter.counter)
}

// 4.2.1
class User1 constructor(nickname: String) {
    val nickname: String

    init {
        this.nickname = nickname
    }
}

// 4.2.2
open class SomeView {
    // Secondary constructor
    constructor(ctx: Context) {
        // some code
    }

    // Secondary constructor
    constructor(ctx: Context, attr: AttributeSet) {
        // some code
    }
}

class MyButton : SomeView {
    // Calling superclass constructor
    constructor(ctx: Context): super(ctx, SimpleAttributeSet.EMPTY) { // Delegates to another constructor of the class
        // some code
    }

    constructor(ctx: Context, attr: AttributeSet): super(ctx, attr) {
        // some code
    }
}

class MySecondButton : SomeView {
    // Delegates to another constructor of this class
    constructor(ctx: Context): this(ctx, SimpleAttributeSet()) {
        // some code
    }

    constructor(ctx: Context, attr: AttributeSet): super(ctx, attr) {
        // some code
    }
}

// 4.2.3
interface User {
    val nickname: String
    val gmail: String
        // Property doesn't have a backing field: the result value is computed on each access
        get() = "$nickname@gmail.com"
}

class PrivateUser(override val nickname: String): User // Primary Constructor property

class SubscribingUser(val email: String): User {
    override val nickname: String
        // Custom getter
        get() = email.substringBefore('@')
}

class FacebookUser(val accountId: Int): User {
    override val nickname = getFaceBookName(accountId) // Property initializer

    private fun getFaceBookName(accountId: Int) = "user-$accountId"
}

// 4.2.4
class User3(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            // Reads the backing field value
            println("""
                Address was changed for $name:
                "$field" -> "$value"
            """.trimIndent())
            // Updates the backing field value
            field = value
        }

}

// 4.2.5
class LengthCounter {
    var counter: Int = 0
        // You can't change this property outside of the class.
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}
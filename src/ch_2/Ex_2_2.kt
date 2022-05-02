package ch_2

fun main() {
    // 2.2.1
    println("---------------2.2.1--------------")
    val person = Person("Bob", true)
    println(person.name)
    println(person.isMarried)
    person.isMarried = false
    println(person.isMarried)

    // 2.2.2
    println("---------------2.2.2--------------")
    val rectangle = Rectangle(2, 2)
    println(rectangle.isSquare)
}

// 2.2.1
class Person(
    val name: String,
    var isMarried: Boolean
)

// 2.2.2
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width
}
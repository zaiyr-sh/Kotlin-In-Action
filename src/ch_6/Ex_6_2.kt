package ch_6

fun main() {
    // 6.2.1
    println("---------------6.2.1--------------")
    showProgress(150)

    // 6.2.2
    println("---------------6.2.2--------------")
    println(Employee("Sam", 35).isOlderThan(Employee("Amy", 42)))
    println(Employee("Sam", 35).isOlderThan(Employee("Jane")))

    // 6.2.3
    println("---------------6.2.3--------------")
    val i = 1
    val l: Long = i.toLong()

    val x = 1
    val list = listOf(1L, 2L, 3L)
    println(x.toLong() in list)

    val b: Byte = 1 // Constant value gets the correct type
    val c = b + 1L // + works with Byte and Long arguments
    printLong(42) // The compiler interprets 42 as a Long value
    println("42".toInt())

    // 6.2.6
    println("---------------6.2.6--------------")
//    fail("Error occurred")
}

// 6.2.1
fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're $percent done!")
}

// 6.2.2
data class Employee(val name: String, val age: Int? = null) {
    fun isOlderThan(other: Employee): Boolean? {
        if (age == null || other.age == null)
            return null
        return age > other.age
    }
}

// 6.2.3
fun printLong(l: Long) = println(l)

// 6.2.5
interface Processor<T> {
    fun process(): T
}

class NoResultProcess : Processor<Unit> {
    override fun process() { // Returns Unit, but you omit the type specification
        // do stuff
        // You don't need an explicit return type here
    }
}

// 6.2.6
fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}
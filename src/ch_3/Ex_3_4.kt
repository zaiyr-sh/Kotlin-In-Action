package ch_3

fun main(args: Array<String>) {
    // 3.4.2
    println("---------------3.4.2--------------")
    val list = listOf("args: ", *args) // Spread operator unpacks the array contents
    println(list)
    // 3.4.3
    println("---------------3.4.3--------------")
    val (number, name) = 1 to "one"
    println(number)
    println(name)
}

// 3.4.1
fun <T> List<T>.last(): T { /* returns the last element */ TODO() }
fun Collection<Int>.max(): Int { /* finding a maximum in a collection */ TODO() }

// 3.4.2
fun <T> listOfArgs(vararg values: T): List<T> { TODO() }

// 3.4.3
infix fun Any.to(other: Any) = Pair(this, other)
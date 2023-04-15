package ch_9

import java.lang.IllegalArgumentException

fun main() {
    // 9.2.1
    println("---------------9.2.1--------------")
    printSum(listOf(1, 2, 3))
    // printSum(setOf(1, 2, 3)) // Set isn't a list, so an exception is thrown
    // printSum(listOf("a", "b", "c")) // The cast succeeds, and another exception is thrown later

    // 9.2.2
    println("---------------9.2.2--------------")
    println(isA<String>("abc"))
    println(isA<String>(123))
    println(isA<String>('c'))

    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())
}

// 9.2.1
fun printSum(c: Collection<*>) {
    val intList = c as? List<Int> // Warning here. Unchecked cast: List<*> to List<Int>
        ?: throw IllegalArgumentException("List<Int> is expected")
    println(intList.sum())
}

fun printIntSum(c: Collection<Int>) {
    if (c is List<Int>) { // This check is legitimate
        println(c.sum())
    }
}

// 9.2.2
// Now this code compiles
inline fun <reified T> isA(value: Any) = value is T

// "reified" declares that this type parameter will not be erased at runtime
inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> {
    val destination = mutableListOf<T>()
    for (element in this) {
        // You can check whether the element is an instance of the class specified as a type argument
        if (element is T) {
            destination.add(element)
        }
    }
    return destination
}
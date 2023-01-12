package ch_8

import ch_3.jointToString

fun main() {
    // 8.1.1
    println("---------------8.1.1--------------")
    // Function that takes two int parameters and returns an Int value
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    // Function that takes no arguments and doesn't return a value
    val action: () -> Unit = { println(42) }
    // Nullable return type
    var canReturnNull: (Int, Int) -> Int? = { x: Int, y: Int -> null }
    // Nullable variable of a function type
    var funOrNull: ((Int, Int) -> Int)? = null

    val url = "https://kotlinlang.org/"
    // You can use the names provided in the API as lambda arguments names ...
    performRequest(url) { code, contnet -> /*...*/ }
    // ... or you can change them
    performRequest(url) { code, page -> /*...*/ }

    // 8.1.2
    println("---------------8.1.2--------------")
    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }
    // Passes a lambda as an argument for "predicate"
    println("ab1c".filter { it in 'a'..'z' })

    // 8.1.4
    println("---------------8.1.4--------------")
    val letters = listOf("Alpha", "Beta")
    // Passes a lambda as an argument
    println(letters.joinToString { it.lowercase() })
    // Uses the named argument syntax for passing several arguments including a lambda
    println(letters.joinToString(separator = "! ", postfix = "! ", transform = { it.lowercase() }))

    // 8.1.5
    println("---------------8.1.5--------------")
    // Stores the returned function in a variable
    val calculator: (Order) -> Double = getShippingCostCalculator(Delivery.EXPEDITED)
    // Invoked the returned function
    println("Shipping costs ${calculator(Order(3))}")

    val contacts = listOf(
        Person("Dmitry", "Jemerov", "123-4567"),
        Person("Svetlana", "Isakova", null),
        Person("Daniil", "Vasiliev", null),
    )
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "D"
        onlyWithPhoneNumber = true
    }
    // Passes the function returned by getPredicate as an argument to "filter"
    println(contacts.filter(contactListFilters.getPredicate()))

    // 8.1.6
    println("---------------8.1.6--------------")
    val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/login", 12.0, OS.WINDOWS),
        SiteVisit("/signup", 8.0, OS.IOS),
        SiteVisit("/", 16.3, OS.ANDROID)
    )

    val averageWindowsDuration = log
        .filter { it.os == OS.WINDOWS }
        .map(SiteVisit::duration)
        .average()
    println(averageWindowsDuration)

    println(log.averageDurationFor(OS.WINDOWS))
    println(log.averageDurationFor(OS.MAC))

    println(log.averageDurationFor {
        it.os in setOf(OS.ANDROID, OS.IOS)
    })
    println(log.averageDurationFor {
        it.os == OS.IOS && it.path == "/signup"
    })
}

// 8.1.1
fun performRequest(
    url: String,
    callback: (code: Int, content: String) -> Unit // The function type now has named parameters
) {
    /*...*/
}

// 8.1.2
fun twoAndThree(operation: (Int, Int) -> Int) { // Declares a parameter of a function type
    val result = operation(2, 3) // Calls the parameter of a function type
    println("The result is $result")
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in indices) {
        val element = get(index)
        // Calls the function passed as the argument for the "predicate" parameter
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

// 8.1.3
// see Example.java
fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

// 8.1.4
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    // Declares a parameter of a function type with a lambda as a default value
    transform: (T) -> String = { it.toString() }
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        // Calls the function passed as an argument for the "transform" parameter
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

@JvmName("joinToStringT")
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    // Declares a nullable parameter of a function type
    transform: ((T) -> String)? = null
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        // Uses the Elvis operator to handle the case when a callback wasn't specified
        val str = transform?.invoke(element) ?: element.toString()
        result.append(str)
    }
    result.append(postfix)
    return result.toString()
}

// 8.1.5
enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

// Declares a function that returns a function
fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
    // Returns lambdas from the function
    if (delivery == Delivery.EXPEDITED)
        return { order -> 6 + 2.1 * order.itemCount }
    return { order -> 1.2 * order.itemCount }
}

data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
)

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = true

    // Declares a function that returns a function
    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }
        if (!onlyWithPhoneNumber) {
            return startsWithPrefix // Returns a variable of a function type
        }
        return { startsWithPrefix(it) && it.phoneNumber != null } // Returns a lambda from this function

    }
}

// 8.1.6
data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

// Duplicated code extracted into the function
fun List<SiteVisit>.averageDurationFor(os: OS) =
    filter { it.os == os }.map(SiteVisit::duration).average()

fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()

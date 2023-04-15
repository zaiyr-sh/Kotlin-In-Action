package ch_9

import java.lang.Appendable
import kotlin.String

fun main() {
    // 9.1.1
    println("---------------9.1.1--------------")
    val letters = ('a'..'z').toList()
    // Specifies the type argument explicitly
    println(letters.slice<Char>(0..2))
    // The compiler infers that T is Char here
    println(letters.slice(10..13))
    // The type parameter T is inferred to be Int in this invocation
    println(listOf(1, 2, 3, 4).penultimate)

    // 9.1.3
    println("---------------9.1.3--------------")
    println(listOf(1, 2, 3).sum())
    println(oneHalf(3))
    println(max("kotlin", "java"))

    val helloWorld = StringBuilder("Hello World")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld)

    // 9.1.4
    println("---------------9.1.4--------------")
    // String?, which is a nullable type, is substituted for T
    val nullableStringProcessor = Processor<String?>()
    // The code compiles fine, having "null" as the "value" argument
    nullableStringProcessor.process(null)
}

// 9.1.1
// The generic extension property can be called on a list of any kind
val <T> List<T>.penultimate: T
    get() = this[size - 2]

// 9.1.3
// Specifies Number as the type parameter upper bound
fun <T: Number> oneHalf(value: T): Double {
    return value.toDouble() / 2.0 // Invokes a method defined in the Number class
}

// The arguments of this function must be comparable elements
fun <T: Comparable<T>> max(first: T, second: T): T {
    return if (first > second) first else second
}

fun <T> ensureTrailingPeriod(seq: T) where T: CharSequence, T: Appendable { // List of type parameter constraints
    // Calls an extension function defined for the CharSequence interface
    if (!seq.endsWith("."))
        // Calls the method from the Appendable interface
        seq.append(".")
}

// 9.1.4
class Processor<T> {
    fun process(value: T) {
        value?.hashCode() // "value" is nullable, so you have to use a safe call
    }
}

class ProcessorWithNonNull<T: Any> { // Specifying a non-null upper bound
    fun process(value: T) {
        value.hashCode() // "value" of type T is now non-null
    }
}
















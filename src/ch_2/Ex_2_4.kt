package ch_2

import java.util.*

fun main() {
    // 2.4.2
    println("---------------2.4.2--------------")
    for (i in 100 downTo 1 step 2) {
        println(fizzBuzz(i) + " ")
    }

    // 2.4.3
    println("---------------2.4.3--------------")
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }
    for ((letter, binary) in binaryReps) {
        println("$letter - $binary")
    }

    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) { // Iterates over a collection with an index
        println("$index: $element")
    }

    // 2.4.4
    println("---------------2.4.4--------------")
    println(isLetter('g'))
    println(isNotDigit('g'))
}

// 2.4.2
fun fizzBuzz(i: Int) = when  {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

// 2.4.4
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
package ch_2

import java.io.BufferedReader
import java.io.StringReader

fun main() {
    // 2.5.1
    println("---------------2.5.1--------------")
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
    val reader2 = BufferedReader(StringReader("2"))
    readNumber(reader2)
}

// 2.5.1
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}
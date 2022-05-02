package ch_2

fun main() {
    println(max(1, 2))
}

// 2.1.2
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}
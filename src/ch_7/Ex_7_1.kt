package ch_7

import java.math.BigDecimal

fun main() {
    // 7.1.1
    println("---------------7.1.1--------------")
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2) // Calls the "plus" function using the + sign
    println(p1 * 1.5)
    println('a' * 3)

    println(0x0F and 0xF0)
    println(0x0F or 0xF0)
    println(0x1 shl 4)

    // 7.1.2
    println("---------------7.1.2--------------")
    var point = Point(1, 2)
    point += Point(3, 4)
    println(point)

    val numbers = ArrayList<Int>()
    numbers += 42 // The Kotlin standard library defines a function plusAssign on a mutable collection
    println(numbers[0])

    var list = arrayListOf(1, 2)
    list += 3 // += changes "list"
    val newList = list + listOf(4, 5) // + returns a new list containing all the elements
    println(list)
    println(newList)

    // 7.1.3
    println("---------------7.1.3--------------")
    val p = Point(10, 20)
    println(-p)

    var bd = BigDecimal.ZERO
    println(bd++)
    println(++bd)
}

// 7.1.1
data class Point(val x: Int, val y: Int) {
    // Defines an operator function named "plus"
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}

operator fun Point.times(scale: Double): Point {
    return Point((x * scale).toInt(), (y * scale).toInt())
}

operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

// 7.1.3
operator fun Point.unaryMinus(): Point { // The unary minus function has no parameters
    return Point(-x, -y)
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE
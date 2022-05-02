package ch_2

import ch_2.Color.*
import java.lang.IllegalArgumentException

fun main() {
    // 2.3.1
    println("---------------2.3.1--------------")
    println(Color.RED.rgb())

    // 2.3.3
    println("---------------2.3.3--------------")
    println(mix(VIOLET, BLUE))
    println(mixMemoryOptimized(BLUE, YELLOW))

    // 2.3.5
    println("---------------2.3.5--------------")
    println(eval(Sum(Sum(Num(1), Num(2)), Num (4))))

    // 2.3.6
    println("---------------2.3.6--------------")
    println(eval(Sum(Sum(Num(2), Num(5)), Num (10))))
}

// 2.3.1
enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    RED(255, 0, 0), ORANGE(255, 165, 0), INDIGO(75, 0, 130),
    VIOLET(238, 130, 238); // The semicolon here is required

    fun rgb() = (r * 256 + g) * 256 + b
}

// 2.3.3
fun mix(c1: Color, c2: Color) =
    when(setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }

// 2.3.4
fun mixMemoryOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
        (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
        else -> throw Exception("Dirty color")
    }

// 2.3.5
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num // This explicit cast is redudant
        return n.value
    }
    if (e is Sum) {
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("Unknown expression")
}

// 2.3.5
fun evalOptimized(e: Expr): Int =
    when(e) {
        is Num -> e.value
        is Sum -> evalOptimized(e.left) + evalOptimized(e.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }
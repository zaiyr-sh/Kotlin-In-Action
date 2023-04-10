package ch_7

import java.time.LocalDate

fun main() {
    // 7.3.1
    println("---------------7.3.1--------------")
    val p = Point(10, 20)
    println(p[1])

    val mp = MutablePoints(10, 20)
    mp[1] = 42
    println(mp)

    // 7.3.2
    println("---------------7.3.2--------------")
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20, 30) in rect)
    println(Point(5, 5) in rect)

    // 7.3.3
    println("---------------7.3.3--------------")
    val now = LocalDate.now()
    val vacation: ClosedRange<LocalDate> = now..now.plusDays(10) // Creates a 10-day range starting from now
    println(now.plusWeeks(1) in vacation)

    val n = 9
    println(0..(n + 1))
    (0..n).forEach { println(it) }

    // 7.3.4
    println("---------------7.3.4--------------")
    for (c in "abs") { }

    val newYear = LocalDate.ofYearDay(2023, 1)
    val daysOff = newYear.minusDays(1)..newYear
    // Iterates over daysOff when the corresponding iterator function is available
    for (dayOff in daysOff) {
        println(dayOff)
    }
}

// 7.3.1
operator fun Point.get(index: Int): Int {
    return when(index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

data class MutablePoints(var x: Int, var y: Int)

operator fun MutablePoints.set(index: Int, value: Int) {
    when(index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

// 7.3.2
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    // Build a range, and checks that coordinate "x" belongs to this range
    // Uses the "until" function to build an open range
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

// 7.3.4
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    // This object implements an iterator over LocalDate elements
    object : Iterator<LocalDate> {
        var current = start

        override fun hasNext() =
            current <= endInclusive // Note that compareTo convention used for dates

        override fun next() = current.apply { // Returns the current date as a result before changing it
            current = plusDays(1) // Increments the current date by one day
        }

    }
package ch_7

fun main() {
    // 7.2.1
    println("---------------7.2.1--------------")
    println(Coordinate(10, 20) == Coordinate(10, 20))
    println(Coordinate(10, 20) != Coordinate(5, 5))
    println(null == Coordinate(1, 2))
    val coordinate = Coordinate(2, 2)
    println(coordinate == coordinate)

    // 7.2.2
    println("---------------7.2.2--------------")
    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    val p3 = Person("Alice", "Smith")
    println(p1 < p2)
    println(p1 <= p3)

    println("abc" < "bac")
}

// 7.2.1
class Coordinate(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        // Optimization: checks whether the parameter is the same object as "this"
        if (other === this) return true
        if (other !is Coordinate) return false
        return other.x == x && other.y == y
    }
}

// 7.2.2
class Person(val firstName: String, val lastName: String): Comparable<Person> {
    override fun compareTo(other: Person): Int {
        // compare by last name, and then, if the last name is the same, compare by first name
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}
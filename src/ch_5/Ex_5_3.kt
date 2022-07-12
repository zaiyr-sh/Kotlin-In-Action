package ch_5

import java.io.File

fun main() {
    // 5.3.0
    println("---------------5.3.0--------------")
    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Martin", 45), Person("Carol", 31))
    println(people.map(Person::name).filter { it.startsWith("A") })
    // no intermediate collections to store the elements are created,
    // so performance for a large number of elements will be noticeably better
    println(
        people.asSequence() // converts the initial collection to Sequence
            .map(Person::name)
            .filter { it.startsWith("A") }
            .toList() // converts the resulting Sequence back into a list
    )

    // 5.3.1
    println("---------------5.3.1--------------")
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { println("filter($it) "); it % 2 == 0 }
        .toList()

    // 5.3.2
    println("---------------5.3.2--------------")
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum()) // all the delayed operations are performed when the result "sum" is obtained

    val file = File("/Users/svtk/.HiddenDir/a.txt")
    println(file.isInsideHiddenDirectory())
}

// 5.3.2
fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }.any { it.isHidden }
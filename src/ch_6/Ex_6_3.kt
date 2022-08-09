package ch_6

import java.io.File

fun main() {
    // 6.3.1
    println("---------------6.3.1--------------")
    addValidNumbers(listOf(1, 2, 3, 4, null, 6, null, 8, 9, null))

    // 6.3.2
    println("---------------6.3.2--------------")
    val source: Collection<Int> = arrayListOf(3, 5, 7)
    // You can’t pass a variable of a read-only collection type as the target argument,
    // even if its value is a mutable collection
    // val target: Collection<Int> = arrayListOf(1)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)

    // 6.3.3
    println("---------------6.3.3--------------")
    val list = listOf("a", "b", "c")
    printInUppercase(list)

    // 6.3.5
    println("---------------6.3.5--------------")
    val letters = Array<String>(26) { i -> ('a' + i).toString() }
    println(letters.joinToString(""))

    val strings = listOf("a", "b", "c")
    // This spread operator (*) is used to pass an array when vararg parameter is expected
    println("%s/%s/%s".format(*strings.toTypedArray()))

    val fiveZeros = IntArray(5)
    println(fiveZeros.joinToString())
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)
    println(fiveZerosToo.joinToString())
    val squares = IntArray(5) { i -> (i+1) * (i+1) }
    println(squares.joinToString())
}

// 6.3.1
fun addValidNumbers(numbers: List<Int?>) {
    val validNumbers = numbers.filterNotNull()
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid numbers: ${numbers.size - validNumbers.size}")
}

// 6.3.2
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source) {
        target.add(item)
    }
}

// 6.3.3
fun printInUppercase(list: List<String>) { // Declares a read-only parameter
    // Calls a Java function that modifies the collection
    println(CollectionUtils.uppercaseAll(list))
    // Shows that the collection has been modified
    println(list.first())
}

// 6.3.4
class FileIndexer : FileContentProcessor {
    override fun processContents(path: File, binaryContents: ByteArray?, textContents: List<String>?) {
        // ...
    }
}

class PersonParser : DataParser<Person> {
    override fun parseData(input: String, output: MutableList<Person>, errors: MutableList<String?>) {
        // ...
    }
}

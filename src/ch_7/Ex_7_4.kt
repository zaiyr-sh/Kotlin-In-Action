package ch_7

fun main() {
    // 7.4
    println("---------------7.4--------------")
    val p = Point(10, 20)
    val (x, y) = p // Declares variables x and y, initialized with components of p
    println(x)
    println(y)

    // Uses the destructuring declaration syntax to unpack the class
    val (name, ext) = splitFilename("example.kt")
    println(name)
    println(ext)


    // 7.4.1
    println("---------------7.4.1--------------")
    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    printEntries(map)
}

// 7.4
data class NameComponent(val name: String, val extension: String)

fun splitFilename(fullName: String): NameComponent {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponent(name, extension)
}

// 7.4.1
fun printEntries(map: Map<String, String>) {
    for ((key, value) in map) { // Destructuring declaration in a loop
        println("$key -> $value")
    }
}

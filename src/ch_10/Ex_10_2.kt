package ch_10

import kotlin.reflect.full.memberProperties

fun main() {
    // 10.2.1
    println("---------------10.2.1--------------")
    val person = Person("Alice", 29)
    val kClass = person.javaClass.kotlin // Returns an instance of KClass<Person>
    println(kClass.simpleName)
    kClass.memberProperties.forEach { println(it.name) }
}

// 10.2.1
class Person(val name: String, val age: Int)


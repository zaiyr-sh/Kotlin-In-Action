package ch_8

fun main() {
    // 8.3.1
    println("---------------8.3.1--------------")
    val people = listOf(Human("Alice", 29), Human("Bob", 31))
    lookForAlice(people)
    lookForAliceWithLambda(people)

    // 8.3.2
    println("---------------8.3.2--------------")
    lookForAliceWithLabel(people)
    lookForAliceWithDefaultLabel(people)

    // This lambda's implicit receiver is accessed by this@sb
    println(StringBuilder().apply sb@{
        listOf(1, 2, 3).apply {
            // "this" refers to the closet implicit receiver in the scope
            this@sb.append(this.toString())
            // All implicit receivers can be accessed, the outer ones via explicit labels
        }
    })

    // 8.3.3
    println("---------------8.3.3--------------")
    lookForAliceWithAnonymousFunction(people)

    // Using an anonymous function with filter
    println(
        people.filter(fun (person): Boolean {
            return person.age < 30
        })
    )
    // Using an anonymous function with an expression body
    println(
        people.filter(fun (person) = person.age < 30)
    )
}

// 8.3.1
data class Human(val name: String, val age: Int)

fun lookForAlice(people: List<Human>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }
    }
    println("Alice is not found")
}

fun lookForAliceWithLambda(people: List<Human>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            // returns from a function
            return
        }
    }
    println("Alice is not found")
}

// 8.3.2
fun lookForAliceWithLabel(people: List<Human>) {
    // Labels the lambda expression
    people.forEach label@{
        // return@label refers to this label
        if (it.name == "Alice") return@label
    }
    println("Alice might be everywhere") // This line is always printed
}

fun lookForAliceWithDefaultLabel(people: List<Human>) {
    people.forEach {
        // return@forEach returns from the lambda expression
        if (it.name == "Alice") return@forEach
    }
    println("Alice might be everywhere")
}

// 8.3.3
fun lookForAliceWithAnonymousFunction(people: List<Human>) {
    people.forEach(fun (person) { // Uses an anonymous function instead of a lambda expression
        // "return" refers to the closet function: an anonymous function
        if (person.name == "Alice") return@forEach
        println("${person.name} is not Alice")
    })
    println("Alice might be everywhere")
}



























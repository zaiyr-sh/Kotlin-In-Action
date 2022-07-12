package ch_5

fun main() {
    // 5.2.1
    println("---------------5.2.1--------------")
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 }) // only even numbers remain
    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Martin", 45), Person("Carol", 31))
    println(people.filter { it.age > 30 })

    println(list.map { it * it })
    println(people.map { it.name })
    println(people.map(Person::name)) // using member reference

    println(people.filter { it.age > 30 }.map(Person::name)) // chaining several calls

    println(people.filter { it.age == people.maxByOrNull(Person::age)?.age }) // this code repeats the process of finding the maximum age for every person
    val maxAge = people.maxByOrNull(Person::age)?.age
    people.filter { it.age == maxAge } // improves solution

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.uppercase() })

    // 5.2.2
    println("---------------5.2.2--------------")
    val canBeInClub27 = { p: Person -> p.age <= 29 }
    println(people.all(canBeInClub27))
    println(people.any(canBeInClub27))

    println(people.count(canBeInClub27)) // tracks only the number of matching elements, not the elements themselves, and is therefore more efficient
    println(people.filter(canBeInClub27).size) // an intermediate collection is created to store all the elements that satisfy the predicate

    println(people.find(canBeInClub27))

    // 5.2.3
    println("---------------5.2.3--------------")
    println(people.groupBy { it.age })

    val listOfStrings = listOf("a", "ab", "b")
    println(listOfStrings.groupBy(String::first))

    // 5.2.4
    println("---------------5.2.4--------------")
    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

    val books = listOf(
        Book("Thursday Next", listOf("Jasper Fforde")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
    )
    println(books.flatMap { it.authors }.toSet())
}

// 5.2.4
class Book(val title: String, val authors: List<String>)
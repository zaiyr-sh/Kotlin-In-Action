package ch_6

fun main() {
    // 6.1.1
    println("---------------6.1.1--------------")
    println(strLenSafe("abc"))
    println(strLenSafe(null))

    // 6.1.3
    println("---------------6.1.3--------------")
    printAllCaps("abs")
    printAllCaps(null)

    // 6.1.4
    println("---------------6.1.4--------------")
    println(strLenSafeWithElvis("abc"))
    println(strLenSafeWithElvis(null))

    // 6.1.5
    println("---------------6.1.5--------------")
    val p1 = Person("Zaiyr", "Sharsheev")
    val p2 = Person("Zaiyr", "Sharsheev")
    println(p1 == p2) // The == operator calls the "equals" method
    println(p1.equals(42))

    // 6.1.6
    println("---------------6.1.6--------------")
    ignoreNulls("abc")
    //ignoreNulls(null) // NullPointerException

    // 6.1.7
    println("---------------6.1.7--------------")
    var myEmail: String? = "zaiyr.00@gmail.com"
    myEmail?.let { email -> sendEmailTo(email) }
    myEmail = null
    myEmail?.let { sendEmailTo(it) }

    // 6.1.9
    println("---------------6.1.9--------------")
    verifyUserInput(" ")
    verifyUserInput(null)

    // 6.1.10
    println("---------------6.1.10--------------")
    printHashCodeOrNull("abc")
    printHashCodeOrNull(null)

    printHashCode("abc")
    //printHashCode(null) // this code doesn't compile

    // 6.1.11
    println("---------------6.1.11--------------")
    val human = Human("Zaiyr")
    yellAt(human)
    //yellAt(Human(null)) // NullPointerException

    yellAtSafe(Human(null))
    val h: String = human.name // Java's property can be seen as nullable
    val h1: String? = human.name // or non-null
}

// 6.1.1
fun strLenSafe(s: String?) =
    if (s != null) s.length else 0

// 6.1.3
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase()
    println(allCaps)
}

// 6.1.4
fun strLenSafeWithElvis(s: String?) =
    s?.length ?: 0

// 6.1.5
class Person(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        // Checks the type and returns false if no match
        val otherPerson = other as? Person ?: return false
        // After the safe cast, the variable otherPerson is smart-cast to the Person type
        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }

    override fun hashCode(): Int =
        firstName.hashCode() * 37 + lastName.hashCode()
}

// 6.1.6
fun ignoreNulls(s: String?) {
    val sNotNull: String = s!! // The exception points to this line
    println(sNotNull.length)
}

// 6.1.7
fun sendEmailTo(email: String) {
    println("The email $email was sent.")
}

// 6.1.9
fun String?.isNullOrBlank(): Boolean =
    this == null || this.isBlank()

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) // No safe call is needed
        println("Please fill in the required fields.")
}

// 6.1.10
fun <T>printHashCodeOrNull(t: T) {
    println(t?.hashCode()) // You have to use a safe call because "t" might be null
}

fun <T: Any>printHashCode(t: T) { // Now "t" can't be nullable
    println(t.hashCode())
}

// 6.1.11
fun yellAt(human: Human) {
    // The receiver person.name of the uppercase() is null, so an exception is thrown
    println(human.name.uppercase() + "!!!")
}

fun yellAtSafe(human: Human) {
    // The receiver person.name of the uppercase() is null, so an exception is thrown
    println((human.name ?: "Anyone").uppercase() + "!!!")
}

class StringPrinter : StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        if (value != null) {
            println(value)
        }
    }
}
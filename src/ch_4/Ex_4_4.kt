package ch_4

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File


fun main() {
    // 4.4.1
    println("---------------4.4.1--------------")
    Payroll.allEmployees.addAll(
        arrayListOf(
            Person("Alice", 2500.4),
            Person("Jack", 3500.5),
            Person("Butt", 1000.1)
        )
    )
    Payroll.calculateSalary()

    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/Desktop")))
    val files = listOf(File("/User"), File("/Desktop"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    val employees = listOf(Employee("Alice"), Employee("Bob"))
    println(employees.sortedWith(Employee.NameComparator))

    // 4.4.2
    println("---------------4.4.2--------------")
    A.B.bar()

    val subscribingUser = CommonUser.newSubstringUser("bob@gmail.com")
    val facebookUser = CommonUser.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(facebookUser.nickname)

    // 4.4.3
    println("---------------4.4.3--------------")
    // the name of the Patient class is used as an instance of JSONFactory
    loadFromJSON(Patient)
    Patient.toJSON()
}

// 4.4.1
class Person(
    val name: String,
    var salary: Double
)

object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        var allSalary: Double = 0.0
        for (person in allEmployees) {
            allSalary += person.salary
        }
        println("All employees salary = $allSalary")
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

data class Employee(val name: String) {
    object NameComparator : Comparator<Employee> {
        override fun compare(e1: Employee, e2: Employee): Int =
            e1.name.compareTo(e2.name)
    }
}

// 4.4.2
class A {
    companion object B {
        fun bar() {
            println("Companion object called")
        }
    }
}

// Mark the primary constructor as private
class CommonUser private constructor(val nickname: String) {

    companion object {
        // Declaring a named companion object
        fun newSubstringUser(email: String) =
            CommonUser(email.substringBefore("@"))

        // Factory method creating a new user by Facebook account ID
        fun newFacebookUser(accountId: Int) =
            CommonUser(getFaceBookName(accountId))

        private fun getFaceBookName(accountId: Int) = "user-$accountId"
    }
}

// 4.4.3
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Patient(val name: String) {
    companion object : JSONFactory<Patient> {
        // Companion object implementing an interface
        override fun fromJSON(jsonText: String): Patient {
            TODO("Not yet implemented")
        }

    }
}

fun <T> loadFromJSON(factory: JSONFactory<T>): String {
    // ...
    return "Loaded successfully"
}

fun Patient.Companion.toJSON() : String {
    return "..."
}

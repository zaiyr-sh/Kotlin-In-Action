package ch_11

fun main() {
    // 11.3.1
    println("---------------11.3.1--------------")
    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Jack") // Calls the Greeter instance as a function

    // 11.3.2
    println("---------------11.3.2--------------")
    val i1 = Issue("IDEA-154446", "IDEA", "Bug", "Major", "Save settings failed")
    val i2 = Issue("KT-12183", "Kotlin", "Feature", "Normal", "Intention: convert several calls on the same receiver to with/apply")
    val predicate = ImportantIssuesPredicate("IDEA")
    for (issue in listOf(i1, i2).filter(predicate)) { // Passes the predicate to filter()
        println(issue.id)
    }

    // 11.3.3
    println("---------------11.3.3--------------")
    val dependencies = DependencyHandler()
    dependencies.compile("org.jetbrains.kotlin:kotlin-stdlib:1.0.0")
    dependencies {
        compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    }
    // The second call is effectively translated to the following:
    dependencies.invoke({
        this.compile("org.jetbrains.kotlin:kotlin-reflect:1.0.0")
    })
}

// 11.3.1
class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name!") // Defines the "invoke" method on Greeter
    }
}

// 11.3.2
data class Issue(
    val id: String, val project: String, val type: String,
    val priority: String, val description: String
)

class ImportantIssuesPredicate(val project: String) : (Issue) -> Boolean { // Uses the function type as a base class
    override fun invoke(issue: Issue): Boolean { // Implements the "invoke" method
        return issue.project == project && issue.isImportant()
    }

    private fun Issue.isImportant(): Boolean {
        return type == "Bug" && (priority == "Major" || priority == "Critical")
    }
}

// 11.3.3
class DependencyHandler {
    fun compile(coordinate: String) { // Defines a regular command API
        println("Added dependency on $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) { // Defines "invoke" to support the DSL API
        body() // "this" becomes a receiver of the body function: this.body()
    }
}





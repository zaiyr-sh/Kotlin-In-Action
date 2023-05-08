package ch_11

fun main() {
    // 11.2.1
    println("---------------11.2.1--------------")
    val s = buildString {
        this.append("Hello, ") // The "this" keyword refers to the StringBuilder instance
        append("world!") // Alternatively, you can omit "this" and refer to StringBuilder implicitly
    }
    println(s)

    val map = mutableMapOf(1 to "one")
    map.apply { this[2] = "two" }
    with(map) { this[3] = "three" }
    println(map)

    // 11.2.2
    println("---------------11.2.2--------------")
    fun createTable() =
        table {
            tr {
                td {

                }
            }
        }
    println(createTable())

    fun createAnotherTable() = table {
        for (i in 1..2) {
            tr { // Each call to "tr" creates a new TR tag and adds it to the children of TABLE
                td {

                }
            }
        }
    }
    println(createAnotherTable())
}

// 11.2.1
fun buildString(
    builderAction: StringBuilder.() -> Unit // Declares a parameter of a function type with a receiver
): String {
    val sb = StringBuilder()
    sb.builderAction() // Passes a StringBuilder as a receiver to the lambda
    return sb.toString()
}

// 11.2.2
open class Tag(val name: String) {
    private val children = mutableListOf<Tag>() // Stores all nested tags

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init() // Initializes the child tag
        children.add(child) // Stores a reference to the child tag
    }

    override fun toString() =
        "<$name>${children.joinToString("")}</$name>" // Returns the resulting HTML as String
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")
package ch_4

fun main() {
    // 4.3.1
    println("---------------4.3.1--------------")
    val client1 = Client("Alice", 34577)
    println(client1)
    val client2 = Client("Alice", 34577)
    /* In Kotlin, == checks whether the objects are equal, not the references.
    It is compiled to a call of "equals" */
    println(client1 == client2)
    val processed = hashSetOf(Client("Alice", 34577))
    println(processed.contains(Client("Alice", 34577)))
    println(client1.copy(postalCode = 23241))

    // 4.3.2
    println("---------------4.3.2--------------")
    val dataClient1 = DataClient("Alice", 34577)
    println(dataClient1)
    val dataClient2 = DataClient("Alice", 34577)
    println(dataClient1 == dataClient2)
    val dataProcessed = hashSetOf(DataClient("Alice", 34577))
    println(dataProcessed.contains(DataClient("Alice", 34577)))
    println(dataClient1.copy())

    // 4.3.3
    println("---------------4.3.3--------------")
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1,1,2,2,2,2))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}

// 4.3.1
class Client(val name: String, val postalCode: Int) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client)
            return false
        return name == other.name && postalCode == other.postalCode
    }
    override fun hashCode() =
        name.hashCode() * 31 + postalCode
    override fun toString() =
        "Client(name=$name, postalCode=$postalCode)"
    fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client(name, postalCode)
}

// 4.3.2
data class DataClient(val name: String, val postalCode: Int)

// 4.3.3
class DelegatingCollection<T>() : Collection<T> {

    private val innerList = arrayListOf<T>()

    override val size: Int
        get() = innerList.size

    override fun contains(element: T): Boolean =
        innerList.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean =
        innerList.containsAll(elements)

    override fun isEmpty(): Boolean =
        innerList.isEmpty()

    override fun iterator(): Iterator<T> =
        innerList.iterator()

}

class DelegatingCollectionWithBy<T>(
    innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList

class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
    // Delegates the MutableCollection implementation to innerSet
) : MutableCollection<T> by innerSet {

    var objectsAdded = 0

    /* Does not delegate: provide a
    *  different implementation */
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    /* Does not delegate: provide a
    *  different implementation */
    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }

}
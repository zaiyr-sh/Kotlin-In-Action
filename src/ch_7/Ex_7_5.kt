package ch_7

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main() {
    // 7.5.2
    println("---------------7.5.2--------------")
    val employee = Employee("Mark")
    employee.emails
    employee.name

    // 7.5.3
    println("---------------7.5.3--------------")
    //val human = Human("Zaiyr", 21, 200)
    //val human = HumanWithObservable("Zaiyr", 21, 200)
    val human = HumanWithDelegatedObservable("Zaiyr", 21, 200)
    human.addPropertyChangeListener { event -> // Attaches a property change listener
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    }
    human.age = 22
    human.salary = 5000

    // 7.5.5
    println("---------------7.5.5--------------")
    val e = Engineer()
    val data = mapOf("name" to "Zaiyr", "company" to "JetBrains")
    for ((attrName, value) in data) {
        e.setAttributes(attrName, value)
    }
    println("${e.name} works in the ${e.company} company!")
}

// 7.5.2
class Email { /*...*/ }
fun loadEmails(employee: Employee): List<Email> {
    println("Load emails for ${employee.name}")
    return listOf(/*...*/)
}

//class Employee(val name: String) {
//    // "_emails" property that stores the data and to which "emails" delegates
//    private var _emails: List<Email>? = null
//
//    val emails: List<Email>
//        get() {
//            if (_emails == null) {
//               _emails = loadEmails(this)
//            }
//            return _emails!!
//        }
//
//}

class Employee(val name: String) {
    val emails by lazy { loadEmails(this) }
}

// 7.5.3
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Human(val name: String, age: Int, salary: Int): PropertyChangeAware() {

    var age: Int = age
        set(newValue) {
            // The "field" identifier lets you access the property backing field
            val oldValue = field
            field = newValue
            // Notifies listeners about the property change
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}

class CustomObservableProperty(
    val propName: String, var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class HumanWithObservable(val name: String, age: Int, salary: Int): PropertyChangeAware() {

    val _age = CustomObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) { _age.setValue(value) }

    val _salary = CustomObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) { _salary.setValue(value) }
}

class ObservableProperty(
    var propValue: Int, val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(human: HumanWithDelegatedObservable, prop: KProperty<*>): Int = propValue
    operator fun setValue(human: HumanWithDelegatedObservable, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class HumanWithDelegatedObservable(val name: String, age: Int, salary: Int): PropertyChangeAware() {

    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    //var age: Int by ObservableProperty(age, changeSupport)
    //var salary: Int by ObservableProperty(salary, changeSupport)
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)

}

// 7.5.5
class Engineer {
    private val _attributes = hashMapOf<String, String>()

    fun setAttributes(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    // Retrieves the attribute from the map manually
    // val name: String
    //    get() = _attributes["name"]!!

    // Uses the map as delegated property
    val name: String by _attributes

    // Uses the map as delegated property
    val company: String by _attributes

}

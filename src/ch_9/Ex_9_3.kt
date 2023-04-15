package ch_9

import kotlin.reflect.KClass

fun main() {
    // 9.3.6
    println("---------------9.3.6--------------")
    printFirst(listOf("Nick", "Mark"))

    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator

    // validators[String::class]!!.validate("") // The value stored in the map has the type FieldValidator<*>
    // Error: Out-projected type 'FieldValidator<*>' prohibits the use of 'fun validate(input: T): Boolean'

    val stringValidator = validators[String::class] as FieldValidator<String> // Warning: unchecked cast
    println(stringValidator.validate(""))

    // val intValidator = validators[String::class] as FieldValidator<Int> // You get an incorrect validator, but this code compiles
    // println(intValidator.validate(0)) // The real error is hidden until you use the validator
    // ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String

    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)
    println(Validators[String::class].validate("Kotlin"))
    println(Validators[Int::class].validate(42))
    // println(Validators[String::class].validate(42)) // Now the "get" method returns an instance of FieldValidator<String>
    // Error: The integer literal does not conform to the expected type String
}

// 9.3.6
fun printFirst(list: List<*>) { // Every list is a possible argument
    if (list.isNotEmpty()) { // isNotEmpty() doesn't use the generic type parameter
        println(list.first()) // first() now returns Any?, but in this case that's enough
    }
}

interface FieldValidator<in T> { // Interface declared as contravariant T
    fun validate(input: T): Boolean // T is used only in the "in" position (this method consumes a value of T)
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String) = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int) = input >= 0
}

object Validators {
    private val validators = // Uses the same map as before, but now you can't access it outside
        mutableMapOf<KClass<*>, FieldValidator<*>>()

    fun <T: Any> registerValidator(
        kClass: KClass<T>, fieldValidator: FieldValidator<T>
    ) {
        // Puts into the map only the correct key-value pairs, when a validator corresponds to a class
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST") // Suppresses the warning about the unchecked cast to FieldValidator<T>
    operator fun <T: Any> get(kClass: KClass<T>) : FieldValidator<T> =
        validators[kClass] as? FieldValidator<T>
            ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
}
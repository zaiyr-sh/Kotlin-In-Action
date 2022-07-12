package ch_5

import ch_5.Ex_5_4.postponeComputation

fun main() {
    // 5.4.1
    println("---------------5.4.1--------------")
    // The compiler will create an instance of an anonymous class implementing Runnable
    // and we will use the lambda as the body of the single abstract method
    postponeComputation(1000) { println(42) } // One instance of Runnable is created for the entire program

    // a new instance is created on each invocation
    postponeComputation(1000, object : Runnable { // Passes an object expression as an implementation of a functional interface
        override fun run() {
            println(42)
        }
    })

    // Compiled to a global variable: only one instance in the program
    val runnable = Runnable { println(42) }
    fun handleComputation() {
        // One object is used for every handleComputation call
        postponeComputation(1000, runnable)
    }

    // Captures the variable "id" in a lambda
    fun handleComputation(id: Long) {
        // Creates a new instance of Runnable on each handleComputation call
        postponeComputation(1000) { println(id) }
    }

    // 5.4.2
    println("---------------5.4.2--------------")
    createAllDoneRunnable().run()
}

// 5.4.2
fun createAllDoneRunnable(): Runnable {
    return Runnable { println("All done!") }
}
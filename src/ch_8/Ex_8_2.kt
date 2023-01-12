package ch_8

import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock

// 8.2.1
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

fun foo(l: Lock) {
    println("Before sync")
    synchronized(l) {
        println("Action")
    }
    println("After sync")
}

// 8.2.5
fun readFirstLineFromFile(path: String): String {
    // Creates the BufferedReader, calls the "use" function, and passes a lambda to execute the operation on the file
    BufferedReader(FileReader(path)).use { br ->
        // Returns the line from the function
        return br.readLine()
    }
}
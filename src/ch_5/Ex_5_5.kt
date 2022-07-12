package ch_5

fun main() {
    // 5.5.1
    println("---------------5.5.1--------------")
    println(alphabetUsingWith())

    // 5.5.2
    println("---------------5.5.2--------------")
    println(alphabetUsingApply())
    println(alphabetUsingBuildString())
}

// 5.5.1
fun alphabetUsingWith() = with(StringBuilder()) { // Specifies the receiver value on which calling the methods
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!")
        toString()
    }

// 5.5.2
fun alphabetUsingApply() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

fun alphabetUsingBuildString() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}


package ch_11

import java.time.LocalDate
import java.time.Period

fun main() {
    // 11.4.1
    println("---------------11.4.1--------------")
    "kotlin" should start with "kot"
    "kotlin".should(start).with("kot")

    // 11.4.2
    println("---------------11.4.2--------------")
    println(1.days.now)
    println(1.days.ago)
    println(1.days.fromNow)
}

// 11.4.1
infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

interface Matcher<T> {
    fun test(value: T)
}

class startWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix))
            throw AssertionError("String $value does not start with $prefix")
    }
}

object start

infix fun String.should(x: start): StartWrapper = StartWrapper(this)

class StartWrapper(val value: String) {
    infix fun with(prefix: String) =
        if (!value.startsWith(prefix))
            throw AssertionError("String does not start with $prefix: $value")
        else
            println(true)
}

// 11.4.2
val Int.days: Period
    get() = Period.ofDays(this) // "this" refers to the value of the numeric constant

val Period.now: LocalDate
    get() = LocalDate.now()

val Period.ago: LocalDate
    get() = LocalDate.now() - this // Invokes LocalDate.minus using operator syntax

val Period.fromNow: LocalDate
    get() = LocalDate.now() + this // Invokes LocalDate.plus using operator syntax










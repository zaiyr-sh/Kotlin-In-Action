package ch_3

fun main() {
    // 3.5.1
    println("---------------3.5.1--------------")
    println("12.345-6.A".split(".", "-"))
    println("12.345-6.A".split("\\.|-".toRegex()))

    // 3.5.2
    println("---------------3.5.2--------------")
    parsePath("Users/yole/kotlin-book/chapter.adoc")

    // 3.5.3
    println("---------------3.5.3--------------")
    val kotlinLogo = """| //
                       .|//
                       .|/ \"""
    println(kotlinLogo.trimMargin("."))
}

// 3.5.2
fun parsePath(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}
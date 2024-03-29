# Chapter 2. Kotlin basics

---

## 2.1. BASIC ELEMENTS: FUNCTIONS AND VARIABLES

### 2.1.1. Hello, world!

### 2.1.2. Functions

> ✅ Ex_2_1.kt (2.1.2)

* Note that in Kotlin, `if` is an expression with a result value. It’s similar to a ternary operator in Java: 
`(a > b) ? a : b`.

* In Kotlin, `if` is an expression, not a statement. The difference between a statement and an expression is that an 
expression has a value, which can be used as part of another expression, whereas a statement is always a top-level 
element in its enclosing block and doesn’t have its own value. 

* In Java, all control structures are statements. In Kotlin, most control structures, except for the loops 
(`for`, `do`, and `do`/`while`) are expressions.

* On the other hand, assignments are expressions in Java and become statements in Kotlin. This helps avoid confusion 
between comparisons and assignments, which is a common source of mistakes.

* You can simplify the previous function even further. Because its body consists of a single expression, you can use 
that expression as the entire body of the function, removing the curly braces and the `return` statement.

```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
```

* If a function is written with its body in curly braces, we say that this function has a **block body**. If it returns 
an expression directly, it has an **expression body**.

### 2.1.3. Variables

* Just as with expression-body functions, if you don’t specify the type, the compiler analyzes the initializer 
expression and uses its type as the variable type.

* There are two keywords to declare a variable:
  * `val` (from **value**) — Immutable reference. A variable declared with `val` can’t be reassigned after it’s 
  initialized. It corresponds to a final variable in Java.
  * `var` (from **variable**) — Mutable reference. The value of such a variable can be changed. This declaration 
  corresponds to a regular (non-final) Java variable.
  
* Using immutable references, immutable objects, and functions without side effects makes your code closer to the 
functional style.

* Note that, even though a `val` reference is itself immutable and can’t be changed, the object that it points to may 
be mutable.

```kotlin
val languages = arrayListOf("Java") // Declares an immutable reference
languages.add("Kotlin") // Mutates the object pointed to by the reference
```

### 2.1.4. Easier string formatting: string templates

* Kotlin allows you to refer to local variables in string literals by putting the `$` character in front of the 
variable name.

* The compiled code creates a `StringBuilder` and appends the constant parts and variable values to it.

* If you need to include the `$` character in a string, you escape it: `println("\$x")`.

---

## 2.2. CLASSES AND PROPERTIES

```kotlin
class Person(val name: String)
```

* Classes of the type containing only data but no code are often called **value objects**, and many languages offer a 
concise syntax for declaring them.

### 2.2.1. Properties

* The idea of a class is to encapsulate data and code that works on that data into a single entity.

* In Kotlin, properties are a first-class language feature, which entirely replaces fields and accessor methods. You 
declare a property in a class the same way you declare a variable: with `val` and `var` keywords. A property declared 
as `val` is read-only, whereas a `var` property is mutable and can be changed.

> ✅ Ex_2_2.kt (2.2.1)

* The concise declaration of the `Person` class in `Ex_2_2.kt` hides the same underlying implementation as the original 
Java code: it’s a class with private fields that is initialized in the constructor and can be accessed through the 
corresponding getter.

### 2.2.2. Custom accessors

> ✅ Ex_2_2.kt (2.2.2)

### 2.2.3. Kotlin source code layout: directories and packages

* Every Kotlin file can have a `package` statement at the beginning, and all declarations (classes, functions, and 
properties) defined in the file will be placed in that package. Declarations defined in other files can be used 
directly if they’re in the same package; they need to be imported if they’re in a different package.

* Kotlin doesn’t make a distinction between importing classes and functions, and it allows you to import any kind of 
declaration using the `import` keyword. You can import the top-level function by name.

* In Kotlin, you can put multiple classes in the same file and choose any name for that file. Kotlin also doesn’t 
impose any restrictions on the layout of source files on disk; you can use any directory structure to organize your 
files.

* In most cases, however, it’s still a good practice to follow Java’s directory layout and to organize source files 
into directories according to the package structure. Sticking to that structure is especially important in projects 
where Kotlin is mixed with Java, because doing so lets you migrate the code gradually without introducing any 
surprises. But you shouldn’t hesitate to pull multiple classes into the same file, especially if the classes are small 
(and in Kotlin, they often are).

---

## 2.3. REPRESENTING AND HANDLING CHOICES: ENUMS AND WHEN

### 2.3.1. Declaring enum classes

* In Kotlin, enum is a so-called **soft keyword**: it has a special meaning when it comes before `class`, but you can 
use it as a regular name in other places. On the other hand, `class` is still a keyword, and you’ll continue to declare 
variables named `clazz` or `aClass`.

> ✅ Ex_2_3.kt (2.3.1)

### 2.3.2. Using “when” to deal with enum classes

* Like `if`, `when` is an expression that returns a value, so you can write a function with an expression body, 
returning the `when` expression directly.

* Unlike in Java, you don’t need to write `break` statements in each branch (a missing `break` is often a cause for 
bugs in Java code). If a match is successful, only the corresponding branch is executed.

### 2.3.3. Using “when” with arbitrary objects

* Unlike Java's `switch`, which requires you to use constants (enum constants, strings, or number literals) as branch 
conditions, `when` allows any objects.

> ✅ Ex_2_3.kt (2.3.3)

* The Kotlin standard library contains a function `setOf` that creates a `Set` containing the objects specified as its 
arguments. A **set** is a collection for which the order of items doesn’t matter; two sets are equal if they contain 
the same items.

### 2.3.4. Using “when” without an argument

> ✅ Ex_2_3.kt (2.3.4)

* If no argument is supplied for the `when` expression, the branch condition is any Boolean expression. The 
`mixOptimized` function does the same thing as `mix` did earlier. Its advantage is that it doesn’t create any extra 
objects, but the cost is that it’s harder to read.

### 2.3.5. Smart casts: combining type checks and casts

> ✅ Ex_2_3.kt (2.3.5)

* If you check the variable for a certain type, you don’t need to cast it afterward; you can use it as having the type 
you checked for. In effect, the compiler performs the cast for you, and we call it a **smart cast**.

* When you’re using a smart cast with a property of a class, the property has to be a `val` and it can’t have a custom 
accessor. Otherwise, it would not be possible to verify that every access to the property would return the same value.

* An explicit cast to the specific type is expressed via the `as` keyword:

```kotlin
val n = e as Num
```

### 2.3.6. Refactoring: replacing “if” with “when

> ✅ Ex_2_3.kt (2.3.6)

### 2.3.7. Blocks as branches of “if” and “when

* The rule “the last expression in a block is the result” holds in all cases where a block can be used and a result is 
expected.

---

## 2.4. ITERATING OVER THINGS: “WHILE” AND “FOR” LOOPS

### 2.4.1. The “while” loop

* Kotlin has `while` and `do-while` loops, and their syntax doesn’t differ from the corresponding loops in Java.

### 2.4.2. Iterating over numbers: ranges and progressions

* Note that ranges in Kotlin are **closed** or **inclusive**, meaning the second value is always a part of the range.

> ✅ Ex_2_4.kt (2.4.2)

### 2.4.3. Iterating over maps

> ✅ Ex_2_4.kt (2.4.3)

* The `..` syntax to create a range works not only for numbers, but also for characters. Here you use it to iterate 
over all characters from `A` up to and including `F`.

### 2.4.4. Using “in” to check collection and range membership

> ✅ Ex_2_4.kt (2.4.4)

## 2.5. EXCEPTIONS IN KOTLIN

* Unlike in Java, in Kotlin the `throw` construct is an expression and can be used as a part of other expressions.

### 2.5.1. “try”, “catch”, and “finally

* The biggest difference from Java is that the `throws` clause isn’t present in the code: if you wrote this function in 
Java, you’d explicitly write `throws IOException` after the function declaration. You’d need to do this because 
`IOException` is a **checked exception**.

* Just like many other modern JVM languages, Kotlin doesn’t differentiate between checked and unchecked exceptions. You 
don’t specify the exceptions thrown by a function, and you may or may not handle any exceptions. This design decision 
is based on the practice of using checked exceptions in Java. Experience has shown that the Java rules often require a 
lot of meaningless code to rethrow or ignore exceptions, and the rules don’t consistently protect you from the errors 
that can happen.

> ✅ Ex_2_5.kt (2.5.1)

### 2.5.2. “try” as an expression

* The `try` keyword in Kotlin, just like `if` and `when`, introduces an expression, and you can assign its value to a 
variable. Unlike with `if`, you always need to enclose the statement body in curly braces. Just as in other statements, 
if the body contains multiple expressions, the value of the `try` expression as a whole is the value of the last 
expression.

## 2.6. SUMMARY

* The `fun` keyword is used to declare a function. The `val` and `var` keywords declare read-only and mutable 
variables, respectively. 
* String templates help you avoid noisy string concatenation. Prefix a variable name with `$` or surround an expression 
with `${ }` to have its value injected into the string. 
* Value-object classes are expressed in a concise way in Kotlin. 
* The familiar `if` is now an expression with a return value. 
* The `when` expression is analogous to `switch` in Java but is more powerful. 
* You don’t have to cast a variable explicitly after checking that it has a certain type: the compiler casts it for you 
automatically using a smart cast. 
* The `for`, `while`, and `do-while` loops are similar to their counterparts in Java, but the `for` loop is now more 
convenient, especially when you need to iterate over a map or a collection with an index. 
* The concise syntax `1..5` creates a range. Ranges and progressions allow Kotlin to use a uniform syntax and set of 
abstractions in `for` loops and also work with the `in` and `!in` operators that check whether a value belongs to a 
range. 
* Exception handling in Kotlin is very similar to that in Java, except that Kotlin doesn’t require you to declare the 
exceptions that can be thrown by a function.
package com.example.checkresultreproducer

import androidx.annotation.CheckResult

class Example {
    @CheckResult
    fun regular(): Int = 1

    @CheckResult
    inline fun inlined(crossinline block: () -> Int) = block()

    @CheckResult
    inline fun <reified T : Any> reified(crossinline block: () -> T): String {
        val t = block()
        return t::class.java.simpleName
    }

    // usage example
    fun call() {
        val a = Example()
        a.regular() // warns
        a.inlined { 1 } // warns
        a.reified { 1 } // no warning
    }
}

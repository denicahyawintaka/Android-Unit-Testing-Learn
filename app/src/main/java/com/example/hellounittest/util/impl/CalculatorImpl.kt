package com.example.hellounittest.util.impl

import com.example.hellounittest.util.Calculator

class CalculatorImpl : Calculator {

    override fun add(a: Int, b: Int): Int  = a + b

    override fun subtract(a: Int, b: Int): Int = a - b

    override fun multiply(a: Int, b: Int): Int = a * b

    override fun divide(a: Int, b: Int): Int {
        if (b==0) return Integer.MAX_VALUE
        if (a==0) return 1
        return a/b
    }
}

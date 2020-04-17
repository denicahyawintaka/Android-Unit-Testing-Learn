package com.example.hellounittest.util.impl

import com.example.hellounittest.util.Calculator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorImplTest {


    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = CalculatorImpl()
    }

    @Test
    fun `add should return correct value when success`() {
        // declare expected output values
        val expextedResult = 8

        // test object execution
        val result = calculator.add(5 , 3)

        // assertion
        assert(expextedResult == result)
        assertEquals(expextedResult, result)
    }

    @Test
    fun `divide should return correct when divider is not zero` () {
        val expectedResult = 4

        val result = calculator.divide(8, 2)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `divide should return MAX INteger when divider is zero` () {
        val expectedResult = Integer.MAX_VALUE

        val result = calculator.divide(8, 0)

        assertEquals(expectedResult, result)
    }

}

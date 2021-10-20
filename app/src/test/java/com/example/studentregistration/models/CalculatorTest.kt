package com.example.studentregistration.models

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CalculatorTest {
    lateinit var calculator:Calculator

    @Before
    fun setUp(){
        calculator = Calculator()
    }

    @Test
    fun testAddition() {
        var result = calculator.addition(4,7)
        assert(result == 11)
    }

    @Test
    fun testSubtraction() {
      val result = calculator.subtraction(9,2)
        assert(result == 7)
    }

    @Test
    fun testAdult(){
        assertEquals("adult",calculator.returnSomething(21))
    }

    @Test
    fun testChild(){
        assertEquals("Minor",calculator.returnSomething(10))
    }
}
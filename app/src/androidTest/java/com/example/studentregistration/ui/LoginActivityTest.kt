package com.example.studentregistration.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.studentregistration.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.reflect.typeOf

@RunWith(JUnit4::class)
class LoginActivityTest{
    @get:Rule

    var loginActivityRule:ActivityScenarioRule<LoginActivity>
    = ActivityScenarioRule(LoginActivity::class.java)


    @Test
    fun testFormValidation(){
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText("Email Required")).check(matches(isDisplayed()))
        onView(withText("Password Required")).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressBarIsVissible(){
        onView(withId(R.id.etLoginEmail)).perform(typeText("selman@gmail.com"))
        onView(withId(R.id.etLoginEmail)).perform(typeText("selman123"))
        onView(withId(R.id.btnLogin)).perform(click())
//        onView(withId(R.id.progressBar).check(matches(isDisplayed()))

    }

    @Test
    fun testCWrongCredentials(){
        onView(withId(R.id.etLoginEmail)).perform(typeText("selman@gmail.com"))
        onView(withId(R.id.etLoginEmail)).perform(typeText("selman123"))
        onView(withId(R.id.btnLogin)).perform(click())
//      onView(withId(R.id.etEror)).check(matches(isDisplayed()))
    }
}
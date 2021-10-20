package com.example.studentregistration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.MockKAnnotations.init
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

open class BaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()         //ensures it runs on the main thread

    @Before
    open fun setUp(){
        MockKAnnotations.init(this)
    }
}
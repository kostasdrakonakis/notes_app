package com.kostasdrakonakis.notes.common

import com.kostasdrakonakis.notes.di.serviceModule
import com.kostasdrakonakis.notes.di.testManagersModule
import com.kostasdrakonakis.notes.di.testNetworkModule
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@RunWith(JUnit4::class)
open class BaseUnitTest : KoinTest {

    @Before
    open fun setUp() {
        startKoin {
            modules(
                arrayListOf(testNetworkModule, testManagersModule, serviceModule)
            )
        }
    }

    @After
    open fun tearDown() {
        stopKoin()
    }
}

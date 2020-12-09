package com.kostasdrakonakis.notes.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.kostasdrakonakis.notes.RxTestSchedulerRule
import com.kostasdrakonakis.notes.managers.note.NoteManager
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseViewModelUnitTest: BaseUnitTest() {
    @get:Rule
    val instantRule = InstantTaskExecutorRule()
    @get:Rule
    val testSchedulerRule = RxTestSchedulerRule()
    @Mock
    lateinit var noteManager: NoteManager
    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var lifecycle: Lifecycle

    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
    }
}
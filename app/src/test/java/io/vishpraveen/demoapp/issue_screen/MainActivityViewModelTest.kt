package io.vishpraveen.demoapp.issue_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkObject
import io.vishpraveen.demoapp.model.IssueDetailModel
import io.vishpraveen.demoapp.repository.GithubIssueDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class MainActivityViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var repository: GithubIssueDataSource

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        Dispatchers.setMain(dispatcher)
        mainActivityViewModel = MainActivityViewModel(repository = repository)
    }

    @Test
    fun getIssuesTest() {
        val issues = listOf(
            IssueDetailModel(iid = 1),
            IssueDetailModel(iid = 2),
        )
        coEvery { repository.getIssues() } returns flow { emit(issues) }
        mainActivityViewModel.getIssues()
        coVerify { repository.getIssues() }
        println("Issues: ${mainActivityViewModel.issues.value}")
        assertEquals(issues.size, mainActivityViewModel.issues.value?.size)
    }

    @After
    fun tearDown() {
        unmockkObject(repository)
        Dispatchers.resetMain()
    }
}
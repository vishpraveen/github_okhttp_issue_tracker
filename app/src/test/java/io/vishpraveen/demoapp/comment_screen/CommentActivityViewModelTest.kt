package io.vishpraveen.demoapp.comment_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkObject
import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.repository.GithubIssueCommentDataSource
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

class CommentActivityViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var repository: GithubIssueCommentDataSource
    private lateinit var commentActivityViewModel: CommentActivityViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        Dispatchers.setMain(dispatcher)
        commentActivityViewModel = CommentActivityViewModel(repository = repository)
    }

    @Test
    fun getCommentsTest() {
        val comments = listOf(
            IssueCommentsModel(),
            IssueCommentsModel(),
        )
        coEvery { repository.getComments(COMMENT_ID) } returns flow { emit(comments) }
        commentActivityViewModel.getComments(COMMENT_ID)
        coVerify { repository.getComments(COMMENT_ID) }
        assertEquals(comments.size, commentActivityViewModel.comments.value?.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        unmockkObject(repository)
        Dispatchers.resetMain()
    }

    private companion object {
        const val COMMENT_ID = "123"
    }
}
package io.vishpraveen.demoapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkObject
import io.vishpraveen.demoapp.model.IssueDetailModel
import io.vishpraveen.demoapp.repository.local_db.DemoAppDatabase
import io.vishpraveen.demoapp.repository.local_db.IssueDetailDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response
import retrofit2.Retrofit

@OptIn(ExperimentalCoroutinesApi::class)
class GithubIssueRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @MockK
    private lateinit var retrofit: Retrofit
    @MockK
    private lateinit var db: DemoAppDatabase
    @MockK
    private lateinit var client: GithubIssueClient
    @MockK
    private lateinit var issueDetailDao : IssueDetailDao

    private lateinit var githubIssueRepository: GithubIssueRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
        Dispatchers.setMain(dispatcher)
        every { retrofit.create(GithubIssueClient::class.java) } returns client
        every { db.issueDetailDao() } returns issueDetailDao

        githubIssueRepository = GithubIssueRepository(
            retrofit = retrofit,
            db = db
        )
    }

    @Test
    fun `getIssuesTest returns list`() = runTest{
        val issues = listOf(
            IssueDetailModel(iid = 1),
            IssueDetailModel(iid = 2),
        )
        coEvery { issueDetailDao.getAllIssues() } returns flow { emit(issues) }
        githubIssueRepository.getIssues().collect {
            assertEquals(issues, it)
        }
    }

    @After
    fun tearDown() {
        unmockkObject(retrofit, db)
        Dispatchers.resetMain()
    }
}
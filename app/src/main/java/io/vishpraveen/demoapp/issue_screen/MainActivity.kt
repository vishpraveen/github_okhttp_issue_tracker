package io.vishpraveen.demoapp.issue_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.vishpraveen.demoapp.RecyclerViewClickListener
import io.vishpraveen.demoapp.comment_screen.CommentActivity
import io.vishpraveen.demoapp.databinding.ActivityMainBinding
import io.vishpraveen.demoapp.model.IssueDetailModel
import io.vishpraveen.demoapp.util.Constants.COMMENT_ID
import io.vishpraveen.demoapp.util.ExtractCommentId

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewClickListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val listener = this
    private val issues: MutableList<IssueDetailModel> = mutableListOf()
    private val issueAdapter = IssueAdapter(issues, listener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setObserver()
    }

    private fun setUpRecyclerView() {
        with(binding.rvIssues) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = issueAdapter
        }
    }

    private fun setObserver() {
        with(viewModel) {

            loader.observe(this@MainActivity) {
                binding.progressCircular.visibility = if (it) View.VISIBLE else View.GONE
            }

            issues.observe(this@MainActivity) {
                this@MainActivity.issues.clear()
                this@MainActivity.issues.addAll(it)
                issueAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(position: Int) {
        issues[position].commentsUrl?.let {
            startActivity(Intent(this@MainActivity, CommentActivity::class.java).apply {
                putExtra(COMMENT_ID, ExtractCommentId.getCommentId(it))
            })
        }
    }
}
package io.vishpraveen.demoapp.comment_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.vishpraveen.demoapp.databinding.ActivityCommentBinding
import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.util.Constants.COMMENT_ID

@AndroidEntryPoint
class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding
    private val viewModel: CommentActivityViewModel by viewModels()
    private val comments: MutableList<IssueCommentsModel> = mutableListOf()
    private val issueAdapter = CommentAdapter(comments)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra(COMMENT_ID)?.let {
            viewModel.getComments(it)
        }
        setUpRecyclerView()
        setObserver()
    }

    private fun setUpRecyclerView() {
        with(binding.rvComments) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = issueAdapter
        }
    }

    private fun setObserver() {
        with(viewModel) {
            loader.observe(this@CommentActivity) {
                binding.progressCircular.visibility = if (it) View.VISIBLE else View.GONE
            }
            comments.observe(this@CommentActivity) {
                this@CommentActivity.comments.clear()
                this@CommentActivity.comments.addAll(it)
                issueAdapter.notifyDataSetChanged()
            }
        }
    }

}
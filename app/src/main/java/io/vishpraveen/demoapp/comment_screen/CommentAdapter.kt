package io.vishpraveen.demoapp.comment_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.vishpraveen.demoapp.databinding.AdapterIssueBinding
import io.vishpraveen.demoapp.model.IssueCommentsModel
import io.vishpraveen.demoapp.util.DateUtil

class CommentAdapter(
    private val comments: MutableList<IssueCommentsModel>
): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterIssueBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class CommentViewHolder(private val binding: AdapterIssueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IssueCommentsModel?) {
            with(binding) {
                Glide.with(ivAvatar).load(item?.user?.avatar_url).into(ivAvatar)
                tvUserName.text = item?.user?.login
                tvIssueDescription.text = item?.body
                tvIssueUpdateDate.text = DateUtil.formatDate(item?.updatedAt)
            }
        }
    }

}

package io.vishpraveen.demoapp.issue_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.vishpraveen.demoapp.RecyclerViewClickListener
import io.vishpraveen.demoapp.databinding.AdapterIssueBinding
import io.vishpraveen.demoapp.model.IssueDetailModel
import io.vishpraveen.demoapp.util.DateUtil

class IssueAdapter(
    private val issues: MutableList<IssueDetailModel>,
    private val listener: RecyclerViewClickListener
): RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterIssueBinding.inflate(inflater, parent, false)
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.onBind(issues[position], position)
    }

    override fun getItemCount(): Int {
        return issues.size
    }

    inner class IssueViewHolder(private val binding: AdapterIssueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: IssueDetailModel?, position: Int) {
            binding.root.setOnClickListener {
                listener.onClick(position)
            }
            with(binding) {
                Glide.with(ivAvatar).load(item?.user?.avatar_url).into(ivAvatar)
                tvUserName.text = item?.user?.login
                tvIssueTitle.text = item?.title
                tvIssueDescription.text = item?.body
                tvIssueUpdateDate.text = DateUtil.formatDate(item?.updatedAt)
            }
        }
    }

}

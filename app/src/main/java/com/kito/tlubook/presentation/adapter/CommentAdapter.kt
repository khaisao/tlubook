package com.kito.tlubook.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kito.tlubook.core.MODE_TIME
import com.kito.tlubook.core.getDurationBreakdown
import com.kito.tlubook.databinding.ItemCommentBinding
import com.kito.tlubook.domain.model.Comment
import com.kito.tlubook.presentation.adapter.diffutil.CommentDiffCallBack


class CommentAdapter(
) : ListAdapter<Comment,CommentAdapter.ViewHolder>(CommentDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Comment) {
            with(binding){
                tvContent.text=item.content
                tvTimeCreate.text = binding.root.context.getDurationBreakdown(System.currentTimeMillis()-item.createAt,MODE_TIME.LONG)
            }
        }
    }


}
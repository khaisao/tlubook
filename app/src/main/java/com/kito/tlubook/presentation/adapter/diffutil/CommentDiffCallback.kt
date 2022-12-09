package com.kito.tlubook.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.kito.tlubook.domain.model.Comment


class CommentDiffCallBack : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}
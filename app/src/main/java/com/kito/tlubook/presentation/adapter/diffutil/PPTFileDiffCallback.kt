package com.kito.tlubook.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.kito.tlubook.domain.model.Post

class PostDiffCallBack:DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return  oldItem == newItem
    }
}
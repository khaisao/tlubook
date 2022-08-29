package com.kito.tlubook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kito.tlubook.data.model.Comment
import com.kito.tlubook.data.model.Post
import com.kito.tlubook.databinding.ItemCommentBinding
import com.kito.tlubook.util.gone
import com.kito.tlubook.util.isVisible
import com.kito.tlubook.util.visible


class CommentAdapter(
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    var listComment = arrayListOf<Comment>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listComment[position])
    }


    override fun getItemCount(): Int {
        return listComment.size
    }

    inner class ViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Comment) {
            with(binding){
            }
        }
    }


}
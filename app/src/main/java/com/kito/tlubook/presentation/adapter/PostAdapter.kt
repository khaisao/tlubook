package com.kito.tlubook.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kito.tlubook.databinding.ItemPostBinding
import com.kito.tlubook.core.gone
import com.kito.tlubook.core.isVisible
import com.kito.tlubook.core.visible
import com.kito.tlubook.domain.model.Post
import com.kito.tlubook.presentation.adapter.diffutil.PostDiffCallBack


class PostAdapter(
    private val onCommentClick: (Post) -> Unit
) : ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            with(binding){
                tvTitle.text = item.caption
                Glide.with(binding.root.context).load(item.linkImage).into(ivMainPost)
                llLike.setOnClickListener {
                    if(ivLike.isVisible()){
                        ivLike.gone()
                        ivUnlike.visible()
                    }
                    else{
                        ivLike.visible()
                        ivUnlike.gone ()
                    }
                }
                llComment.setOnClickListener {
                    onCommentClick(item)
                }
            }
        }
    }


}
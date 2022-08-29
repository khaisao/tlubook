package com.kito.tlubook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kito.tlubook.data.model.Post
import com.kito.tlubook.databinding.ItemPostBinding
import com.kito.tlubook.util.gone
import com.kito.tlubook.util.isVisible
import com.kito.tlubook.util.visible


class PostAdapter(
    private val onCommentClick: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var listPost = arrayListOf<Post>()
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
        holder.bind(listPost[position])
    }


    override fun getItemCount(): Int {
        return listPost.size
    }

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            with(binding){
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
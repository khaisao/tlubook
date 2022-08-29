package com.kito.tlubook.ui.activity.home.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kito.tlubook.R
import com.kito.tlubook.data.model.Comment
import com.kito.tlubook.data.model.Post
import com.kito.tlubook.databinding.FragmentDetailPostBinding
import com.kito.tlubook.ui.adapter.CommentAdapter
import com.kito.tlubook.ui.base.BaseBindingFragment


class DetailPostFragment : BaseBindingFragment<FragmentDetailPostBinding>() {
    override fun getLayoutId(): Int =R.layout.fragment_detail_post
    private val adapter:CommentAdapter =CommentAdapter()

    override fun addEvent() {
        super.addEvent()
        val abc = arrayListOf<Comment>()
        for (i in 1..50) {
            val comment = Comment()
            abc.add(comment)
        }
        with(binding){
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            rv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = adapter
            adapter.listComment = abc
            adapter.notifyDataSetChanged()
        }
    }

}
package com.kito.tlubook.ui.activity.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentLoginCreateNameBinding
import com.kito.tlubook.databinding.FragmentLoginFinishBinding
import com.kito.tlubook.ui.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFinishFragment : BaseBindingFragment<FragmentLoginFinishBinding>() {
    override fun getLayoutId(): Int =R.layout.fragment_login_finish
    override fun addEvent() {
        super.addEvent()
        with(binding){
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}
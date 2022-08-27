package com.kito.tlubook.ui.activity.login.fragment

import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentWelcomeTluBookBinding
import com.kito.tlubook.ui.base.BaseBindingFragment

class LoginWelcomeFragment : BaseBindingFragment<FragmentWelcomeTluBookBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_welcome_tlu_book

    override fun addEvent() {
        super.addEvent()
        with(binding) {
            tvNext.setOnClickListener {
                findNavController().navigate(LoginWelcomeFragmentDirections.actionLoginWelcomeFragmentToLoginCreateNameFragment())
            }
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvAlreadyHaveAccount.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }


}
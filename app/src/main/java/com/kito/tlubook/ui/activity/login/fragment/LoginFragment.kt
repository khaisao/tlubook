package com.kito.tlubook.ui.activity.login.fragment

import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentLoginBinding
import com.kito.tlubook.ui.base.BaseBindingFragment

class LoginFragment : BaseBindingFragment<FragmentLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun addEvent() {
        super.addEvent()
        with(binding){
            tvCreateAccount.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLoginWelcomeFragment())
            }
        }
    }

    override fun addOnCreateView() {
        super.addOnCreateView()

    }

}
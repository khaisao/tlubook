package com.kito.tlubook.presentation.login.fragment

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.FragmentLoginBinding
import com.kito.tlubook.presentation.home.MainActivity
import com.kito.tlubook.presentation.login.LoginViewModel
import com.kito.tlubook.core.base.BaseBindingFragment
import com.kito.tlubook.core.UiState
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class LoginFragment : BaseBindingFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun addEvent() {
        super.addEvent()
        with(binding){
            tvCreateAccount.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLoginWelcomeFragment())
            }
            tvLogin.setOnClickListener {
                viewModel.login(
                    email = edtEmail.text.toString(),
                    password = edtPassword.text.toString()
                )
            }
        }
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.login.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    Toasty.info(requireContext(),"Loading",Toast.LENGTH_SHORT).show()
                }
                is UiState.Failure -> {
                    Toasty.error(requireContext(),"Error",Toast.LENGTH_SHORT).show()

                }
                is UiState.Success -> {
                    Toasty.success(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getSession { user ->
            if (user != null){
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

}
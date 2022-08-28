package com.kito.tlubook.ui.activity.login.fragment

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.data.model.User
import com.kito.tlubook.databinding.FragmentLoginFinishBinding
import com.kito.tlubook.ui.activity.login.LoginViewModel
import com.kito.tlubook.ui.base.BaseBindingFragment
import com.kito.tlubook.util.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFinishFragment : BaseBindingFragment<FragmentLoginFinishBinding>() {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun getLayoutId(): Int = R.layout.fragment_login_finish
    override fun addEvent() {
        super.addEvent()
        with(binding) {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvSignUp.setOnClickListener {
                val user = User(
                    id = "",
                    userName = "asasg",
                    email = viewModel.email,
                    password = viewModel.password
                )
                viewModel.register(
                    email = user.email, password = viewModel.password, user = user
                )
            }
        }
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.register.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is UiState.Failure -> {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()

                }
                is UiState.Success -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

}
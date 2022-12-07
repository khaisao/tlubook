package com.kito.tlubook.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kito.tlubook.domain.repository.AuthRepository
import com.kito.tlubook.data.model.User
import com.kito.tlubook.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
//    @Inject
//    lateinit var db: FirebaseFirestore
//
//    @Inject
//    lateinit var storage: StorageReference
//
//    @Inject
//    lateinit var currentUser: MutableLiveData<User>

    var email = ""
    var password = ""
    private val _login = MutableLiveData<UiState<String>>()
    val login: LiveData<UiState<String>>
        get() = _login

    private val _forgotPassword = MutableLiveData<UiState<String>>()
    val forgotPassword: LiveData<UiState<String>>
        get() = _forgotPassword
    private val _register = MutableLiveData<UiState<String>>()
    val register: LiveData<UiState<String>>
        get() = _register

    fun register(
        email: String,
        password: String,
        user: User
    ) {
        _register.value = UiState.Loading
        authRepository.registerUser(
            email = email,
            password = password,
            user = user
        ) { _register.value = it }
    }

    fun login(
        email: String,
        password: String
    ) {
        _login.value = UiState.Loading
        authRepository.loginUser(
            email,
            password
        ) {
            _login.value = it
        }
    }

    fun forgotPassword(email: String) {
        _forgotPassword.value = UiState.Loading
        authRepository.forgotPassword(email) {
            _forgotPassword.value = it
        }
    }

    fun logout(result: () -> Unit) {
        authRepository.logout(result)
    }

    fun getSession(result: (User?) -> Unit) {
        authRepository.getSession(result)
    }

}
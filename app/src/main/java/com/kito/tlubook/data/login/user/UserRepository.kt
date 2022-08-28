package com.kito.tlubook.data.login.user

import android.app.Activity
import android.content.Context
import com.google.firebase.auth.PhoneAuthProvider
import com.kito.tlubook.data.model.User
import com.kito.tlubook.util.UiState

interface UserRepository {
    fun sendOtp(numberPhone: String, activity: Activity, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks, result: (UiState<Nothing>) -> Unit)
    fun verifyOTP(verifyId:String, codeOTP:String,result: (UiState<Boolean>) -> Unit)
}
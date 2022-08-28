package com.kito.tlubook.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var userName:String="",
    var phoneNumber:String="",
    var passWord:String=""
):Parcelable

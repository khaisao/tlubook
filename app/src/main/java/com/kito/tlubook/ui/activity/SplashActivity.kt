package com.kito.tlubook.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.kito.tlubook.R
import com.kito.tlubook.databinding.ActivitySplashBinding
import com.kito.tlubook.ui.activity.login.LoginActivity
import com.kito.tlubook.ui.base.BaseBindingActivity
import com.kito.tlubook.util.setStatusBarColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int =R.layout.activity_splash
    override fun setUpViews() {
        super.setUpViews()
        lifecycleScope.launch {
            delay(500)
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            finish()
        }
    }

}
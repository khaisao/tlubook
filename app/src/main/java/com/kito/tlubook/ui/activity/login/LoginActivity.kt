package com.kito.tlubook.ui.activity.login

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.kito.tlubook.R
import com.kito.tlubook.databinding.ActivityLoginBinding
import com.kito.tlubook.ui.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseBindingActivity<ActivityLoginBinding>() {
    override fun getLayoutId(): Int =R.layout.activity_login
    private lateinit var navController: NavController
    override fun setUpViews() {
        super.setUpViews()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_login) as NavHostFragment
        navController = navHostFragment.findNavController()
    }

}
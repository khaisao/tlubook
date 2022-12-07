package com.kito.tlubook.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kito.tlubook.core.setStatusBarColor

abstract class BaseBindingActivity<T: ViewDataBinding>: AppCompatActivity() {
    abstract fun getLayoutId(): Int

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        setStatusBarColor()
        addEvent()
        addObservers()
        setUpViews()
        addData()
    }

    open fun addEvent() {}

    open fun addObservers() {}

    /**
     * Setup the initialize state for views, like set adapter for recycle view
     */
    open fun setUpViews() {}

    /**
     * Init data if need, like call api
     */
    open fun addData() {}
}
package com.codewithk10.learnanything.ui.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.codewithk10.learnanything.R

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val TAG = "BaseActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigationColor()
        setContentView(setLayout())
        init()
    }

    @LayoutRes
    abstract fun setLayout(): Int
    abstract fun init()

    fun log(message: String) {
        Log.d(TAG, message)
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun setNavigationColor(@ColorRes navColor: Int = R.color.colorWhite) {
        window.navigationBarColor = ResourcesCompat.getColor(resources, navColor, null)
    }

}
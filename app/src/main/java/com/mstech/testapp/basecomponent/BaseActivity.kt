package com.mstech.testapp.basecomponent

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mstech.testapp.utils.getProgressDialog

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (inflator: LayoutInflater) -> VB
) : AppCompatActivity() {

    private var _binding: VB? = null
    val binding: VB get() = _binding as VB


    private val mProgressDialog: AlertDialog? by lazy {
       getProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)

        if (_binding == null) {
            throw IllegalArgumentException("Binding cannot be null")
        }

        setContentView(binding.root)
        initViews()
    }

    abstract fun initViews()

    open fun showProgressBar() {
        mProgressDialog?.let {
            it.show()
        }
    }

    open fun hideProgressBar() {
        mProgressDialog?.hide()
    }

}
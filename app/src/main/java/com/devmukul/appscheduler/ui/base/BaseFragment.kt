package com.devmukul.appscheduler.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<BINDING: ViewBinding, VIEWMODEL: ViewModel> : Fragment(){

    protected lateinit var binding: BINDING private set
    protected lateinit var viewModel: VIEWMODEL private set


    private val type = (javaClass.genericSuperclass as ParameterizedType)
    private val classVB = type.actualTypeArguments[0] as Class<BINDING>
    private val classVM = type.actualTypeArguments[1] as Class<VIEWMODEL>

    private val inflateMethod = classVB.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = inflateMethod.invoke(null, inflater, container, false) as BINDING
        viewModel = createViewModelLazy(classVM.kotlin, { viewModelStore }).value

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
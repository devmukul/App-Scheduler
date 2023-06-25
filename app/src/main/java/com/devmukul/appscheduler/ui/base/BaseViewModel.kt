package com.devmukul.appscheduler.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel() : ViewModel() {

    companion object {
        val TAG = BaseViewModel::class.java
    }
}
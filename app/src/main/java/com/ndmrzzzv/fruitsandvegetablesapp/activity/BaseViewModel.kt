package com.ndmrzzzv.fruitsandvegetablesapp.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

open class BaseViewModel : ViewModel() {

    val scopeWithExceptionHandler =
        viewModelScope + CoroutineExceptionHandler { context, throwable ->
            Log.e("Coroutine Exception Handler", throwable.message.toString(), throwable)
        }

}
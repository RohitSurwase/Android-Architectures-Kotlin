package com.rohitss.aac

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Created by Rohit Surwase on 31/08/18.
 */

class MainActivityViewModelFactory(private val appsRepository: AppsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = MainActivityViewModel(appsRepository) as T
}
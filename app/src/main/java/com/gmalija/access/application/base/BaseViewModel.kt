package com.gmalija.access.application.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel()
//    , CoroutineScope
{

//    // Coroutine's background job
//    val job = Job()
//
//    // Define default thread for Coroutine as Main and add job
//    override val coroutineContext: CoroutineContext = job + Dispatchers.Main
//
//    override fun onCleared() {
//        super.onCleared()
//        // Clear our job when the linked activity is destroyed to avoid memory leaks
//        job.cancel()
//    }

}
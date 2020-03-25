package com.gmalija.access.application.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmalija.access.application.base.BaseViewModel
import com.gmalija.core.domain.useCase.SplashUseCase
import com.gmalija.core.domain.utils.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel
    @Inject constructor(private val splashUseCase: SplashUseCase) : BaseViewModel() {

    private val _isCurrentUser = SingleLiveEvent<Boolean>()
    val isCurrentUser : LiveData<Boolean>
        get() = _isCurrentUser

    init {
        viewModelScope.launch {
            delay(1500)
            val currentUser = splashUseCase.currentUser()
            _isCurrentUser.value = currentUser != null
        }
    }

}
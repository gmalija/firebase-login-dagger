package com.gmalija.access.application.home

import com.gmalija.access.application.base.BaseViewModel
import com.gmalija.core.domain.useCase.HomeUseCase
import javax.inject.Inject

class HomeViewModel
    @Inject constructor(private val homeUseCase: HomeUseCase) : BaseViewModel() {

    init {
        homeUseCase.signOut()
    }

}
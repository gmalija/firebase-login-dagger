package com.gmalija.core.infraestructure.di

import android.content.Context
import com.gmalija.core.domain.repository.LoginRepository
import com.gmalija.core.domain.repository.LoginRepositoryImpl
import com.gmalija.core.domain.useCase.HomeUseCase
import com.gmalija.core.domain.useCase.SignInUseCase
import com.gmalija.core.domain.useCase.SplashUseCase
import com.gmalija.core.infraestructure.firebase.Firebase
import com.gmalija.core.infraestructure.firebase.FirebaseImpl
import com.gmalija.core.infraestructure.google.Google
import com.gmalija.core.infraestructure.google.GoogleImpl
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class CoreModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): Firebase {
        return FirebaseImpl(FirebaseAuth.getInstance())
    }

    @Singleton
    @Provides
    fun provideGoogleAuth(context: Context): Google {
        return GoogleImpl(AuthUI.getInstance(), context)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(firebase: Firebase, google: Google): LoginRepository {
        return LoginRepositoryImpl(firebase, google)
    }

    @Singleton
    @Provides
    fun provideSignInUseCase(loginRepository: LoginRepository): SignInUseCase {
        return SignInUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun provideSplashUseCase(loginRepository: LoginRepository): SplashUseCase {
        return SplashUseCase(loginRepository)
    }

    @Singleton
    @Provides
    fun provideHomeUseCase(loginRepository: LoginRepository): HomeUseCase {
        return HomeUseCase(loginRepository)
    }

}
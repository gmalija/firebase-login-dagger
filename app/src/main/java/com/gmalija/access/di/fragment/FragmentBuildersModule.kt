package com.gmalija.access.di.fragment

import com.gmalija.access.application.home.HomeFragment
import com.gmalija.access.application.signIn.LoginFragment
import com.gmalija.access.application.signIn.RegisterFragment
import com.gmalija.access.application.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}
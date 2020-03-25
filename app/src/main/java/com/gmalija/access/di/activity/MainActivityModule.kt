package com.gmalija.access.di.activity

import com.gmalija.access.application.MainActivity
import com.gmalija.access.di.fragment.FragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
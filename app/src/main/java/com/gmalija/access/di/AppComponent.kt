package com.gmalija.access.di

import android.app.Application
import com.gmalija.access.Bootstrap
import com.gmalija.access.di.activity.MainActivityModule
import com.gmalija.access.di.viewmodel.ViewModelModule
import com.gmalija.core.infraestructure.di.CoreModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        CoreModule::class,
        ViewModelModule::class,
        MainActivityModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(bootstrap: Bootstrap)
}
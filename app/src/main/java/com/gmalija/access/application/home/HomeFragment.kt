package com.gmalija.access.application.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentHomeBinding
import com.gmalija.core.infraestructure.di.Injectable
import javax.inject.Inject

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: HomeViewModel by viewModels{
        viewModelFactory
    }
    override fun getLayoutResId() = R.layout.fragment_home

    override fun init(view: View) { }

}
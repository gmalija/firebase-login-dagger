package com.gmalija.access.application.splash

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentSplashBinding
import com.gmalija.core.infraestructure.di.Injectable
import javax.inject.Inject

class SplashFragment: BaseFragment<FragmentSplashBinding, SplashViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: SplashViewModel by viewModels {
        viewModelFactory
    }

    override fun getLayoutResId() = R.layout.fragment_splash

    override fun init(view: View) {
        viewModel.isCurrentUser.observe(this, Observer {
            if(it) {
                goToHome()
            } else {
                goToSignIn()
            }
        })
    }

    private fun goToSignIn() {
        val directions = SplashFragmentDirections.actionSplashFragmentToSignInNavigation()
        findNavController().navigate(directions)
    }

    private fun goToHome() {
        val directions = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

}
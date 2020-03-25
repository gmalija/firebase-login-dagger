package com.gmalija.access.application.signIn

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentRegisterBinding
import com.gmalija.core.domain.entity.LoginUser
import com.gmalija.core.domain.entity.Result
import com.gmalija.core.domain.entity.Status
import com.gmalija.core.infraestructure.di.Injectable
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class RegisterFragment: BaseFragment<FragmentRegisterBinding, SignInViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: SignInViewModel by viewModels {
        viewModelFactory
    }

    override fun getLayoutResId() = R.layout.fragment_register

    override fun init(view: View) {
        viewModel.user.observe(this, Observer { render(it, view) })
    }

    private fun render(result: Result<LoginUser>, view: View) {

        when(result.status) {
            Status.SUCCESS -> {
                hideProgress()
                Snackbar.make(view, "Welcome ${result.data?.email}", Snackbar.LENGTH_LONG).show()
                goToHome()
            }
            Status.LOADING -> {
                showProgress()
            }
            else -> {
                hideProgress()
                Snackbar.make(view, result.message ?: "Error", Snackbar.LENGTH_LONG).show()
            }
        }
        hideKeyboard()

    }

    private fun goToHome() {
        val directions = RegisterFragmentDirections.actionGlobalHomeFragment()
        findNavController().navigate(directions)
    }

}
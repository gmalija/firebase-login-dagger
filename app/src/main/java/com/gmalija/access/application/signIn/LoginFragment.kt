package com.gmalija.access.application.signIn

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentLoginBinding
import com.gmalija.core.domain.entity.LoginUser
import com.gmalija.core.domain.entity.RegisterCase
import com.gmalija.core.domain.entity.Result
import com.gmalija.core.domain.entity.Status
import com.gmalija.core.infraestructure.di.Injectable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import javax.inject.Inject

class LoginFragment: BaseFragment<FragmentLoginBinding, SignInViewModel>(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: SignInViewModel by viewModels{
        viewModelFactory
    }

    override fun getLayoutResId() = R.layout.fragment_login
    private val RC_SIGN_IN = 1000

    override fun init(view: View) {
        viewModel.user.observe(this, Observer { renderUiInputs(it, view) })
        viewModel.registerCase.observe(this, Observer { onClickUiInputs(it) })
    }

    private fun renderUiInputs(result: Result<LoginUser>, view: View) {

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

    private fun onClickUiInputs(registerCase: RegisterCase) {
        when(registerCase) {
            RegisterCase.REGISTER_EMAIL -> goToRegister()
            RegisterCase.REGISTER_GOOGLE -> onLaunchRegisterGoogle()
            RegisterCase.UNKNOWN -> Timber.i("On click error: Unknown")
        }
    }

    private fun goToRegister() {
        val directions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(directions)
    }

    private fun goToHome() {
        val directions = LoginFragmentDirections.actionGlobalHomeFragment()
        findNavController().navigate(directions)
    }

    private fun onLaunchRegisterGoogle() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }

        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account?.let { viewModel.onRegisterGoogle(it) }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Timber.e("Google sign in failed: ${e.message}")
            }
        }

    }

}
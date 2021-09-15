package br.com.hunterbay.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.hunterbay.core.extensions.createDialog
import br.com.hunterbay.data.model.Login
import br.com.hunterbay.databinding.ActivityLoginBinding
import br.com.hunterbay.presentation.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindingListeners()
        observerListeners()
    }

    private fun observerListeners() {
        viewModel.stateLogin.observe(this) {
            when(it) {
                LoginViewModel.State.Loading -> {
                    binding.pgLogin.visibility = View.VISIBLE
                    binding.btnLogin.text = ""
                }
                is LoginViewModel.State.Error -> {
                    binding.pgLogin.visibility = View.GONE
                    binding.btnLogin.text = "ENTRAR"
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is LoginViewModel.State.Logged -> {
                    binding.pgLogin.visibility = View.GONE
                    binding.btnLogin.text = "ENTRAR"
                    createDialog {
                        setMessage(it.body.body()?.name)
                    }.show()
                }
            }
        }
    }

    private fun bindingListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(Login(
                email = binding.tieEmail.text.toString(),
                password = binding.tiePassword.text.toString()
            ))
        }
        binding.tvCreateAccount.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
        binding.forgetPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }
}
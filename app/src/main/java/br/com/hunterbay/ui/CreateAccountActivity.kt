package br.com.hunterbay.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.hunterbay.core.extensions.createDialog
import br.com.hunterbay.core.extensions.text
import br.com.hunterbay.data.model.TextInput
import br.com.hunterbay.data.model.UserCreateAccount
import br.com.hunterbay.databinding.ActivityCreateAccountBinding
import br.com.hunterbay.presentation.CreateAccountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCreateAccountBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<CreateAccountViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindingListeners()
        observerListeners()
    }

    private fun errorTextInputEdit(tils: ArrayList<TextInput>) {
        var count = 0
        for(til in tils) {
            if(til.til.text.isEmpty()) {
                til.til.error = "Preencha o campo ${til.campo}"
                count++
            } else {
                til.til.error = null
            }
        }
        if(count > 0) throw Exception("Preencha os campos assinalados")
    }

    private fun validationCamp() {
        val ties = arrayListOf<TextInput>()
        ties.add(TextInput(binding.tilName, "nome"))
        ties.add(TextInput(binding.tilLastName, "Sobrenome"))
        ties.add(TextInput(binding.tilEmail, "E-mail"))
        ties.add(TextInput(binding.tilPassword, "Senha"))
        ties.add(TextInput(binding.tilConfirmPassword, "Confirmação de senha"))
        errorTextInputEdit(ties)
    }

    private fun bindingListeners() {
        binding.btnCreateAccount.setOnClickListener {
            try {
                validationCamp()
                viewModel.createAccount(UserCreateAccount(
                    name = binding.tieNome.text.toString(),
                    lastName = binding.tieLastNome.text.toString(),
                    email = binding.tieEmail.text.toString(),
                    password = binding.tiePassword.text.toString(),
                    confirmPassword = binding.tieConfirmPassword.text.toString()
                ))
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observerListeners() {
        viewModel.statusUser.observe(this) {
            when(it) {
                CreateAccountViewModel.Status.Loading -> {
                    binding.btnCreateAccount.text = ""
                    binding.pbCreateAccount.visibility = View.VISIBLE
                }
                is CreateAccountViewModel.Status.Created -> {
                    binding.pbCreateAccount.visibility = View.GONE
                    binding.btnCreateAccount.text = "CRIAR CONTA"
                    if(it.response.isSuccessful) {
                        createDialog {
                            setMessage(it.response.errorBody()?.charStream()?.readText())
                        }.show()
                    }
                }
                is CreateAccountViewModel.Status.ErrorAccount -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    binding.pbCreateAccount.visibility = View.GONE
                    binding.btnCreateAccount.text = "CRIAR CONTA"
                }
            }
        }
    }
}
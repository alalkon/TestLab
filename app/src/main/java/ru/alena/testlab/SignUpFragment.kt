package ru.alena.testlab

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.alena.testlab.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up, container, false
        )

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.name.text.isEmpty() || !viewModel.isNameSurnameValid(binding.name.text.toString())) {
                    binding.name.error = "Field shouldn't be blank. Exp: Ivan"
                }
            }
        })

        binding.surname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.surname.text.isEmpty() || !viewModel.isNameSurnameValid(binding.surname.text.toString())) {
                    binding.surname.error = "Field shouldn't be blank. Exp: Ivanov"
                }
            }
        })

        binding.birthday.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.birthday.text.isEmpty() || !viewModel.isDateValid(binding.birthday.text.toString())) {
                    binding.birthday.error = "Field shouldn't be blank DD.MM.YYYY"
                }
            }
        })

        binding.emailSignUp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.emailSignUp.text.isEmpty() || !viewModel.isEmailValid(binding.emailSignUp.text.toString())) {
                    binding.emailSignUp.error = "Field shouldn't be blank and asd12@asd.com"
                }
            }
        })

        binding.passwordSignUp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.passwordSignUp.text.isEmpty() || !viewModel.isPasswordValid(binding.passwordSignUp.text.toString())) {
                    binding.passwordSignUp.error = "Field shouldn't be blank and password"
                }
            }
        })

        binding.passwordCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.passwordCheck.text.isEmpty() || binding.passwordCheck.text.toString()
                    != binding.passwordSignUp.text.toString()) {
                    binding.passwordCheck.error = "Passwords don't match"
                }
            }
        })

        binding.goSignUpButton.setOnClickListener { view: View ->
            if (viewModel.isNameSurnameValid(binding.name.text.toString())
                && viewModel.isNameSurnameValid(binding.surname.text.toString())
                && viewModel.isDateValid(binding.birthday.text.toString())
                && viewModel.isEmailValid(binding.emailSignUp.text.toString())
                && viewModel.isPasswordValid(binding.passwordSignUp.text.toString())
                && binding.passwordCheck.text.toString() == binding.passwordSignUp.text.toString()
            ) {
                view.findNavController().navigate(R.id.action_signUpFragment_to_pageFragment)
            }
        }
        return binding.root
    }
}
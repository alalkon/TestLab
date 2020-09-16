package ru.alena.testlab

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.alena.testlab.databinding.FragmentSignUpBinding
import java.util.regex.Pattern

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up, container, false
        )


        binding.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.name.text.isEmpty() || !isNameSurnameValid(binding.name.text.toString())) {
                    binding.name.error = "Field shouldn't be blank. Exp: Ivan"
                }
            }
        })

        binding.surname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.surname.text.isEmpty() || !isNameSurnameValid(binding.surname.text.toString())) {
                    binding.surname.error = "Field shouldn't be blank. Exp: Ivanov"
                }
            }
        })

        binding.birthday.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.birthday.text.isEmpty() || !isDateValid(binding.birthday.text.toString())) {
                    binding.birthday.error = "Field shouldn't be blank DD.MM.YYYY"
                }
            }
        })

        binding.emailSignUp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.emailSignUp.text.isEmpty() || !isEmailValid(binding.emailSignUp.text.toString())) {
                    binding.emailSignUp.error = "Field shouldn't be blank and asd12@asd.com"
                }
            }
        })

        binding.passwordSignUp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.passwordSignUp.text.isEmpty() || !isPasswordValid(binding.passwordSignUp.text.toString())) {
                    binding.passwordSignUp.error = "Field shouldn't be blank and password"
                }
            }
        })

        binding.passwordCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.passwordCheck.text.isEmpty() || binding.passwordCheck.text.toString()
                    != binding.passwordSignUp.text.toString()
                ) {
                    binding.passwordCheck.error = "Passwords don't match"
                }
            }
        })

        binding.goSignUpButton.setOnClickListener { view: View ->
            if (isNameSurnameValid(binding.name.text.toString())
                && isNameSurnameValid(binding.surname.text.toString())
                && isDateValid(binding.birthday.text.toString())
                && isEmailValid(binding.emailSignUp.text.toString())
                && isPasswordValid(binding.passwordSignUp.text.toString())
                && binding.passwordCheck.text.toString() == binding.passwordSignUp.text.toString()
            ) {
                view.findNavController().navigate(R.id.action_signUpFragment_to_pageFragment,
                    bundleOf("name" to binding.name.text.toString()))
            }
        }
        return binding.root
    }



    fun isNameSurnameValid(name: String): Boolean {
        val Name = Pattern.compile("[A-Z][a-zA-Z]" + "[^#&<>\\\"~;\$^%{}?]{1,20}" + "$")
        return Name.matcher(name).matches()
    }

    fun isDateValid(date: String): Boolean {
        val birthday =
            Pattern.compile("^" + "(1[0-2]|0[1-9]).(3[01]|[12][0-9]|0[1-9]).[0-9]{4}" + "$")
        return birthday.matcher(date).matches()
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[!.,<>@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 8 characters
                    "$"
        )
        return passwordPattern.matcher(password).matches()
    }
}
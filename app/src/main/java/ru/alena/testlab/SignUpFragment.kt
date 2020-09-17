package ru.alena.testlab

import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.*
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
                    binding.name.error = "Field shouldn't be blank. Exmpl: Ivan"
                }
            }
        })

        binding.surname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.surname.text.isEmpty() || !isNameSurnameValid(binding.surname.text.toString())) {
                    binding.surname.error = "Field shouldn't be blank. Exmpl: Ivanov"
                }
            }
        })

       binding.birthday.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.birthday.text.isEmpty()) {
                    binding.birthday.error = "Pick date"
                }
            }
        })

        binding.emailSignUp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.emailSignUp.text.isEmpty() || !isEmailValid(binding.emailSignUp.text.toString())) {
                    binding.emailSignUp.error = "Field shouldn't be blank. Exmpl: alalkon@gmail.com"
                }
            }
        })

        binding.passwordSignUp.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.passwordSignUp.text.isEmpty() || !isPasswordValid(binding.passwordSignUp.text.toString())) {
                    binding.passwordSignUp.error = "Password should contain at least 8 characters including upper and lower case, " +
                            "digits and special characters"
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
                    binding.passwordCheck.error = "Password doesn't match"
                }
            }
        })

        binding.goSignUpButton.setOnClickListener { view: View ->
            if (isNameSurnameValid(binding.name.text.toString())
                && isNameSurnameValid(binding.surname.text.toString())
                && (binding.birthday.text.isNotEmpty())
                && isEmailValid(binding.emailSignUp.text.toString())
                && isPasswordValid(binding.passwordSignUp.text.toString())
                && binding.passwordCheck.text.toString() == binding.passwordSignUp.text.toString()
            ) {
                view.findNavController().navigate(R.id.action_signUpFragment_to_pageFragment,
                    bundleOf("name" to binding.name.text.toString()))
            }
        }

        //DATE_PICKER

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.birthday.text = sdf.format(cal.time)

        }

        binding.dateButton.setOnClickListener {
            DatePickerDialog(requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        return binding.root
    }


    fun isNameSurnameValid(name: String): Boolean {
        val Name = Pattern.compile("[A-Z][a-zA-Z]" + "[^#&<>\\\"~;\$^%{}?]{1,20}" + "$")
        return Name.matcher(name).matches()
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
                    ".{6,}" +               //at least 6 characters
                    "$"
        )
        return passwordPattern.matcher(password).matches()
    }
}
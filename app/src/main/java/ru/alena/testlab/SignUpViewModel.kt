package ru.alena.testlab

import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SignUpViewModel: ViewModel() {

    fun isNameSurnameValid(name: String): Boolean{
        val Name = Pattern.compile("[A-Z][a-zA-Z]" + "[^#&<>\\\"~;\$^%{}?]{1,20}" + "$")
        return Name.matcher(name).matches()
    }

    fun isDateValid(date: String): Boolean {
        val birthday = Pattern.compile("^" + "(1[0-2]|0[1-9]).(3[01]|[12][0-9]|0[1-9]).[0-9]{4}"+ "$")
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
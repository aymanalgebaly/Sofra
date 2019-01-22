package com.example.android.sofraa.helper;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.android.sofraa.R;


/**
 * Created by sas on 08/04/2018.
 */

public class Validation {
    public static String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Context context;

    public Validation(Context context) {
        this.context = context;
    }


    public void setEmailValidation(TextInputEditText EmailEditText, TextInputLayout textInputLayout) {
        String email = EmailEditText.getText().toString();


        if (TextUtils.isEmpty(email)) {
            textInputLayout.setError(context.getResources().getString(R.string.EnterEmail));
            textInputLayout.requestFocus();
            return;
        }
        if (!email.matches(emailPattern)) {
            textInputLayout.setError(context.getResources().getString(R.string.invalidEmail));
            textInputLayout.requestFocus();
            return;
        }

    }


    // this method validate  password
    public void setPasswordValidation(TextInputEditText passwordEditText, TextInputLayout textInputLayout) {
        String password = passwordEditText.getText().toString();

        if (password.length() < 6) {
            textInputLayout.setError(context.getResources().getString(R.string.invalidPass));
            textInputLayout.requestFocus();
            return;

        } else if (TextUtils.isEmpty(password)) {
            textInputLayout.setError(context.getResources().getString(R.string.EnterPass));
            textInputLayout.requestFocus();
            return;

        }

    }


    // this method validate confirmation  password you have to put password editText field and confirm password edit text field
    public void setConfirmPassword(TextInputEditText passwordEditText, TextInputEditText confirmPasswordEditText, TextInputLayout textInputLayout) {
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();


        if (TextUtils.isEmpty(confirmPassword)) {
            textInputLayout.setError(context.getResources().getString(R.string.EnterPass));
            textInputLayout.requestFocus();
            return;

        } else if (!password.equals(confirmPassword)) {
            textInputLayout.setError(context.getResources().getString(R.string.passMatchat));
            textInputLayout.requestFocus();
            return;
        }


    }


    // this method validate  phone
    public void setPhoneValidation(TextInputEditText phoneEditText, TextInputLayout textInputLayout) {
        String phone = phoneEditText.getText().toString();

        if (phone.length() < 6) {
            textInputLayout.setError(context.getResources().getString(R.string.invalidPhone));
            textInputLayout.requestFocus();
            return;
        } else if (TextUtils.isEmpty(phone)) {
            textInputLayout.setError(context.getResources().getString(R.string.EnterNumber));
            textInputLayout.requestFocus();
            return;
        }

    }

    // this method validate  user name
    public void setUserNameValidation(TextInputEditText userNameEditText, TextInputLayout textInputLayout) {
        String userName = userNameEditText.getText().toString();

        if (userName.length() < 4) {
            textInputLayout.setError(context.getResources().getString(R.string.invalidName));
            textInputLayout.requestFocus();
            return;
        } else if (TextUtils.isEmpty(userName)) {
            textInputLayout.setError(context.getResources().getString(R.string.EnterNamge));
            textInputLayout.requestFocus();
            return;
        }


    }


    //  this method to validate any editText for not null
    public boolean setEmptyValidation(EditText EditText) {

        String text = EditText.getText().toString();

        if (TextUtils.isEmpty(text)) {
            EditText.setError(context.getResources().getString(R.string.empity));
            EditText.requestFocus();
            return false;
        } else {

            return true;
        }


    }

}

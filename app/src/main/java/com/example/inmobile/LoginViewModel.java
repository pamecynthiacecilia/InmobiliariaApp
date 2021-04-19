package com.example.inmobile;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {
    Context context;
    private MutableLiveData<String> error;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void autenticar(String mail, String password) {
        String mailValido = "sonia@mail.com";
        String passwordValido = "password";
        if (mail.equals(mailValido) && password.equals(passwordValido)) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        } else {
            error.setValue("Usuario y/o password incorrecto/s !!!!");
        }
    }
}



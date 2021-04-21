package com.example.inmobile;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

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

        ApiClient apiClient= ApiClient.getApi();
        Propietario propietario = apiClient.login(mail, password);

        if (propietario != null) {
            Intent intent = new Intent(context, MainActivity.class);
            //para llamar una activity desde el viewmodel
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            error.setValue("Usuario y/o password incorrecto/s !!!!");
        }
    }
}


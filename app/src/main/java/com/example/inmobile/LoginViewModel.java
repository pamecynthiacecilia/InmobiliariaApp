package com.example.inmobile;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


        Call<String>respuestaToken= ApiClient.getMyApiClient().login(mail,password);
        respuestaToken.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {
                    Log.d("token", response.body());
                    SharedPreferences sp = context.getSharedPreferences("token.dat", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer " + response.body());
                    editor.commit();
                    usuarioLogueado();
                }
                else{
                    error.setValue("Usuario y/o Contraseña Incorrectos!");
                    }
                }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                    Log.d("token", "algo falla "+ t.getMessage());
            }
        });

    }

    public void usuarioLogueado(){
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}


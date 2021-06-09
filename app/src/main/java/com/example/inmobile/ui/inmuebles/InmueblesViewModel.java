package com.example.inmobile.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;
import com.google.android.gms.common.AccountPicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> inmueblesMutable;
    private Context context;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<List<Inmueble>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;

    }

    public void mostrarInmuebles() {

        Call<List<Inmueble>>inmueblesPropietario= ApiClient.getMyApiClient().inmueblesDelPropietario(ApiClient.obtenerToken(context));
        inmueblesPropietario.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    inmueblesMutable.postValue(response.body());
                }
                else Toast.makeText(context, "Sin respuesta", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
             Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }



}

package com.example.inmobile.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> inmuebleMutable;

    private Context context;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();

    }

    public LiveData<Inmueble> getInmueble() {
        if (inmuebleMutable == null) {
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }


    public void cargarInmueble(int id){
        Call<Inmueble> inmuebleSeleccionado= ApiClient.getMyApiClient().obtenerPorId(id,ApiClient.obtenerToken(context));
        inmuebleSeleccionado.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble>response) {
                if(response.isSuccessful()){
                    inmuebleMutable.postValue(response.body());
                }
                else Toast.makeText(context, "Sin respuesta", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void actualizarDisponible(Inmueble inmueble){
    Call<Inmueble> estadoInmueble= ApiClient.getMyApiClient().modificarDisponible(ApiClient.obtenerToken(context),inmueble);

    estadoInmueble.enqueue(new Callback<Inmueble>() {
        @Override
        public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
            if(response.isSuccessful()){
                inmuebleMutable.setValue(response.body());
            }
            else{
                Toast.makeText(context, "Sin respuesta", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<Inmueble> call, Throwable t) {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
        }
    });

    }

    }








package com.example.inmobile.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;
import com.google.android.gms.common.AccountPicker;

import java.util.ArrayList;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;
    private Context context;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;

    }

    public void mostrarInmuebles() {

        ApiClient apiClient= ApiClient.getApi();
        ArrayList<Inmueble> inmuebles = apiClient.obtnerPropiedades();
        this.inmueblesMutable.setValue(inmuebles);

    }



}

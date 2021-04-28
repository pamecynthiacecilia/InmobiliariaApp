package com.example.inmobile.ui.contratos;

import android.app.Application;
import android.content.Context;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;


public class ContratosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;
    private Context context;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    //Acá traemos los inmuebles que tienen contratos vigentes en la ApiClient
    public void cargarInmueblesConContrato() {

        ApiClient apiClient= ApiClient.getApi();
        ArrayList<Inmueble> inmuebles= apiClient.obtenerPropiedadesAlquiladas();
        this.inmueblesMutable.setValue(inmuebles);

    }

}

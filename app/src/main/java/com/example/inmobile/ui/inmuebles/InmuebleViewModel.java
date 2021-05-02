package com.example.inmobile.ui.inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;

import java.util.ArrayList;

public class InmuebleViewModel extends ViewModel {

    private MutableLiveData<Inmueble> inmuebleMutable;

    public InmuebleViewModel() {
        super();
    }

    public LiveData<Inmueble> getInmueble() {
        if (inmuebleMutable == null) {
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }

    public void cargarInmueble(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        inmuebleMutable.setValue(inmueble);
    }


    public void actualizarDatosInmueble(Inmueble inmueble){
        ApiClient apiClient = ApiClient.getApi();
        apiClient.actualizarInmueble(inmueble);
        this.inmuebleMutable.setValue(inmueble);
    }

    }








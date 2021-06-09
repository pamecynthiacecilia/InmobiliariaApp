package com.example.inmobile.ui.inquilinos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;

import java.util.ArrayList;

public class InquilinosViewModel extends ViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    //Acá buscamos en la ApiClient y nos trae los inmuebles que tienen un inquilino
    public void cargarInmueblesConInquilino() {
      /*  ApiClient apiClient= ApiClient.getApi();
        ArrayList<Inmueble> inmuebles = apiClient.obtenerPropiedadesAlquiladas();
        this.inmueblesMutable.setValue(inmuebles); */
    }

}
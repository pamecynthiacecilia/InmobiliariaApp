package com.example.inmobile.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;

import java.util.ArrayList;

public class PagosViewModel  extends AndroidViewModel{

    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;
    private Context context;


    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
}

    //Acá hacemos una consulta a la ApiClient para traer los inmuebles con un contrato vigente y por sus pagos

    public void cargarInmueblesConPagos() {

        ApiClient apiClient= ApiClient.getApi();
        ArrayList<Inmueble> inmuebles= apiClient.obtenerPropiedadesAlquiladas();
        this.inmueblesMutable.setValue(inmuebles);


    }

}

package com.example.inmobile.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Pago;
import com.example.inmobile.request.ApiClient;

import java.util.ArrayList;

public class PagoViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pago>> pagosMutable;

    public LiveData<ArrayList<Pago>> getPagos () {
        if (pagosMutable == null) {
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    }

    //Aca traemos un inmueble de la Api que tenga un contrato vigente y sus pagos
    public void cargarPagos(Bundle bundle) {

        Inmueble inmueble = (Inmueble)  bundle.getSerializable("inmueble");
        ApiClient apiClient= ApiClient.getApi();
        Contrato contratoVer =apiClient.obtenerContratoVigente(inmueble);
        ArrayList<Pago> pagos = apiClient.obtenerPagos(contratoVer);
        this.pagosMutable.setValue(pagos);

    }

}
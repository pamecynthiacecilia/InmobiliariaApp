package com.example.inmobile.ui.pagos;

import android.os.Bundle;

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

    public void cargarPagos(Bundle bundle) {
        Contrato contrato = (Contrato) bundle.get("contrato");
        ApiClient apiClient= ApiClient.getApi();
        ArrayList<Pago> pagos = apiClient.obtenerPagos(contrato);
        this.pagosMutable.setValue(pagos);

    }

    }

package com.example.inmobile.ui.contratos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;

public class ContratoViewModel extends ViewModel {

    private MutableLiveData<Contrato> contratoMutable;

    public LiveData<Contrato> getContrato() {
        if (contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;

    }

    //aca recibimos un inmueble y se busca el contrato vigente
    public void cargarContrato(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient apiClient= ApiClient.getApi();
        Contrato contrato= apiClient.obtenerContratoVigente(inmueble);
        this.contratoMutable.setValue(contrato);

    }

}

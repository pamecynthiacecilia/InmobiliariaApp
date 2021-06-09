package com.example.inmobile.ui.inquilinos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Inquilino;
import com.example.inmobile.request.ApiClient;

public class InquilinoViewModel extends ViewModel {

    private MutableLiveData<Inquilino> inquilinoMutable;

    public InquilinoViewModel() {
        super();
    }

    public LiveData<Inquilino> getInquilino() {
        if (inquilinoMutable == null) {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }

    ////Acá recibimos un inmueble  y buscamos en la ApiClient el contrato vigente de ese inmueble y su inquilino
    public void cargarInquilino(Bundle bundle) {

      /*  Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient apiClient= ApiClient.getApi();
        Inquilino inquilino = apiClient.obtenerInquilino(inmueble);
        this.inquilinoMutable.setValue(inquilino);

*/

    }


}

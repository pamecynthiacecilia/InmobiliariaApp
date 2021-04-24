package com.example.inmobile.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Inmueble;

public class InmuebleViewModel extends ViewModel {

    private MutableLiveData<Inmueble> inmueble;

    public InmuebleViewModel() {
        super();
    }
    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

}

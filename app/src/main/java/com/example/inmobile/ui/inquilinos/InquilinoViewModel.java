package com.example.inmobile.ui.inquilinos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Inquilino;
import com.example.inmobile.request.ApiClient;

public class InquilinoViewModel extends ViewModel {

    private MutableLiveData<Inquilino> inquilino;

    public InquilinoViewModel() {
        super();
    }
    public LiveData<Inquilino> getInquilino() {
        if (inquilino == null) {
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }



}

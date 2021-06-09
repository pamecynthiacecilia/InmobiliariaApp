package com.example.inmobile.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContratosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Contrato>> inmueblesMutable;
    private Context context;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<List<Contrato>> getInmueblesMutable() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }


    // inmuebles con contratos vigentes
    public void mostrarInmueblesConContrato() {
      //  Toast.makeText(context, "En Carga de Inmueble " , Toast.LENGTH_LONG).show();
        Call<List<Contrato>>inmueblesPropietario= ApiClient.getMyApiClient().inmueblesConContrato(ApiClient.obtenerToken(context));
        inmueblesPropietario.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    inmueblesMutable.postValue(response.body());
                }
                else Toast.makeText(context, "Sin respuesta", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });


    }

}

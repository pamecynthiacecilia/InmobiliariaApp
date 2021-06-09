package com.example.inmobile.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {


    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> mensajeMutable;

    Context context;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietarioMutable() {
        if (propietarioMutable == null) {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getMensajeMutable() {
        if (mensajeMutable == null) {
            mensajeMutable = new MutableLiveData<>();
        }
        return mensajeMutable;
    }

    //usuario Logueado
    public void obtenerDatos() {

        Call<Propietario> propietarioActual = ApiClient.getMyApiClient().usuarioActual(ApiClient.obtenerToken(context));
        propietarioActual.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    propietarioMutable.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                mensajeMutable.setValue("Hubo una Falla y el Usuario no puede ser mostrado");
            }
        });


    }

    public void editarPropietario(Propietario propietario) {
        Log.d("salida", "en editar view model");

        Call<Propietario> propietarioAeditar = ApiClient.getMyApiClient().editar(ApiClient.obtenerToken(context), propietario);

        propietarioAeditar.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {

                        propietarioMutable.setValue(response.body());
                        mensajeMutable.postValue("Sus datos fueron editados exitosamente!");

                       //
                    } else {
                        mensajeMutable.postValue("no se pudo editar");
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
           /* apiClient.actualizarPerfil(propietario);
            propietarioMutable.setValue(propietario);
            mensajeMutable.setValue("Sus datos fueron editados exitosamente!");*/

    }


}
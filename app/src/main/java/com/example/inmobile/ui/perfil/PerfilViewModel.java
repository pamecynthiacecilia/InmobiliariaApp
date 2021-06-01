package com.example.inmobile.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
    ApiClient apiClient= ApiClient.getApi();
    Context context;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
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

    //este metodo nos trae al usuario Logueado
    public void obtenerDatos() {

     Call<Propietario>propietarioActual =ApiClient.getMyApiClient().usuarioActual(ApiClient.obtenerToken(context));
        propietarioActual.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                mensajeMutable.setValue("Hubo una Falla y el Usuario puede ser mostrado");
            }
        });


    }
    public void editar(int id,Long dni ,String nombre, String apellido, String email,  String password, String tel){

        Propietario propietario = new Propietario(id, dni, nombre, apellido, email, password, tel);


        if( (dni != null) && (nombre.length()>0) && (nombre != null) && (apellido != null) && (apellido.length()>0) &&
                ( email != null) && ( email.length() >0 ) && (password != null) &&
                (password.length() > 0) && (tel != null ) && (tel.length()>0))
        {
            apiClient.actualizarPerfil(propietario);
            propietarioMutable.setValue(propietario);
            mensajeMutable.setValue("Sus datos fueron editados exitosamente!");

        }
        else
        {
            mensajeMutable.setValue("Controle que ningún campo este vacio");
        }
    }

}

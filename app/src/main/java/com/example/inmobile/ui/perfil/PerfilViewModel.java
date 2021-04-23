package com.example.inmobile.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<Propietario>propietarioMutable;

    public LiveData<Propietario> getPropietarioMutable(){

        if(propietarioMutable==null){
            propietarioMutable=new MutableLiveData<>();
        }

        return propietarioMutable;
    }

    //este metodo nos trae al usuario Logueado
    public void obtenerDatos(){

        ApiClient apiClient= ApiClient.getApi();
        Propietario p = apiClient.obtenerUsuarioActual();
        // paso el usuario al mutable
        propietarioMutable.setValue(p);
    }

    public void editar(int id,Long dni ,String nombre, String apellido, String email,  String password, String tel){

        ApiClient apiClient= ApiClient.getApi();
        Propietario propietario = new Propietario( id, dni,nombre, apellido, email,password,tel);
        apiClient.actualizarPerfil(propietario);
        propietarioMutable.setValue(propietario);
    }







}




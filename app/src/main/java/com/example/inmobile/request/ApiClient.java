package com.example.inmobile.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.inmobile.modelo.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public class ApiClient {
    private ArrayList<Propietario> propietarios=new ArrayList<>();
    private ArrayList<Inquilino> inquilinos=new ArrayList<>();
    private ArrayList<Inmueble> inmuebles=new ArrayList<>();
    private ArrayList<Contrato> contratos=new ArrayList<>();
    private ArrayList<Pago> pagos=new ArrayList<>();
    private static Propietario usuarioActual=null;
    private static ApiClient api=null;

    private ApiClient(){
        //Nos conectamos a nuestra "Base de Datos"
        cargaDatos();
    }
    //Método para crear una instancia de ApiClient
    public static ApiClient getApi(){
        if (api==null){
            api=new ApiClient();
        }
        return api;

        }




    //Servicios
//Lista de inmuebles con contratos vigentes del Propietario logueado
    public ArrayList<Inmueble> obtenerPropiedadesAlquiladas(){
        ArrayList<Inmueble> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.getinmuebleContrato().getpropietarioInmueble().equals(usuarioActual)){
                temp.add(contrato.getinmuebleContrato());
            }
        }
        return temp;
    }


//Dado un inmueble retorna el contrato activo de dicho inmueble

    public Contrato obtenerContratoVigente(Inmueble inmueble){

        for(Contrato contrato:contratos){
            if(contrato.getinmuebleContrato().equals(inmueble)){
                return contrato;
            }
        }
        return null;
    }

    //Dado un inmueble, retorna el inquilino del ultimo contrato activo de ese inmueble.
    public Inquilino obtenerInquilino(Inmueble inmueble){
        for(Contrato contrato:contratos){
            if(contrato.getinmuebleContrato().equals(inmueble)){
                return contrato.getinquilinoContrato();
            }
        }
        return null;
    }
    //Dado un Contrato, retorna los pagos de dicho contrato
    public ArrayList<Pago> obtenerPagos(Contrato contratoVer){
        ArrayList<Pago> temp=new ArrayList<>();
        for(Contrato contrato:contratos){
            if(contrato.equals(contratoVer)){
                for(Pago pago:pagos){
                    if(pago.getContrato().equals(contrato)){
                        temp.add(pago);
                    }
                }
            }
            break;
        }
        return temp;
    }



    private void cargaDatos() {


        //Inquilinos
        Inquilino mario = new Inquilino(100, "253406910", "Mario", "Luna", "Aiello sup.", "luna@mail.com", "2664253411", "Lucero Roberto", "2664851422");
        inquilinos.add(mario);

    }
        //Inmuebles
      /*  "http://www.secsanluis.com.ar/servicios/salon1.jpg");
        "http://www.secsanluis.com.ar/servicios/casa1.jpg");
        "http://www.secsanluis.com.ar/servicios/casa2.jpg");
        "http://www.secsanluis.com.ar/servicios/departamento1.jpg");
        "http://www.secsanluis.com.ar/servicios/casa3.jpg");



        //Contratos
        Contrato uno=new Contrato(701, "05/01/2020","05/01/2021",17000,mario,otraCasa);
        contratos.add(uno);
        //Pagos
        pagos.add(new Pago(900,1,uno,17000,"10/02/2020"));
        pagos.add(new Pago(901,2,uno,17000,"10/03/2020"));
        pagos.add(new Pago(902,3,uno,17000,"10/04/2020"));
    }*/

//////////////////////////////////////////////////////////////////////////////////////////
        //consumo Api ip http://192.168.0.4:45455/

   private static final String PATH= "http://192.168.0.4:45455/api/";

    private static MyApiInterface myApiInterface;

    // genera un Objeto que implementa la interface
    public static MyApiInterface getMyApiClient() {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        myApiInterface= retrofit.create(MyApiInterface.class);

        Log.d("salida", retrofit.baseUrl().toString());
        return myApiInterface;
    }


    public interface MyApiInterface{

        @FormUrlEncoded
        @POST("propietarios/login")
        Call<String>login(@Field("usuario")String usuario, @Field("clave") String clave);

        @PUT("propietarios/editar")
        Call<Propietario>editar(@Header("Authorization") String token,@Body Propietario propietario);

        @GET("propietarios/usuarioActual")
        Call<Propietario>usuarioActual(@Header("Authorization")String token);

        @GET("inmuebles/inmueblesDelPropietario")
        Call<List<Inmueble>>inmueblesDelPropietario(@Header("Authorization")String token);

        @GET("inmuebles/obtenerPorId/{id}")
        Call<Inmueble>obtenerPorId(@Path("id") int id, @Header("Authorization")String token);

        @PUT("inmuebles/modificarDisponible")
        Call<Inmueble>modificarDisponible(@Header("Authorization") String token,@Body Inmueble inmueble);

        @GET("contratos/inmueblesConContrato")
        Call<List<Contrato>>inmueblesConContrato(@Header("Authorization")String token);

        @GET("contratos/obtenerPorId/{id}")
        Call<Contrato>obtenerContratoPorId(@Path("id") int id, @Header("Authorization")String token);

    }

    public static String obtenerToken(Context context){
        String token;
        SharedPreferences sp= context.getSharedPreferences("token.dat", 0);
        token= sp.getString("token", "NO SE PUEDO CONECTAR");
        return token;

    }
}

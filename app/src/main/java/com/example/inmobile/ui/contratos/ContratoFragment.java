package com.example.inmobile.ui.contratos;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobile.R;
import com.example.inmobile.modelo.Contrato;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private TextView tvCodigoContrato;
    private TextView tvFechaInicio;
    private TextView tvFechaFin;
    private TextView tvMontoAlquiler;
    private TextView tvInquilino;
    private TextView tvInmueble;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contrato_fragment, container, false);
        context = root.getContext();
        inicializar(root);

        if(getArguments() != null ) {

            int id = getArguments().getInt("id");
            Log.d("idInmueble", "" + id);
            contratoViewModel.cargarContrato(id);

        }
        return root;
    }

    private void inicializar(View view) {
        tvCodigoContrato = view.findViewById(R.id.tvCodigoContrato);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvFechaFin = view.findViewById(R.id.tvFechaFin);
        tvMontoAlquiler = view.findViewById(R.id.tvMontoAqluiler);
        tvInquilino = view.findViewById(R.id.tvInquilino);
        tvInmueble = view.findViewById(R.id.tvInmueble);
        contratoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);
        contratoViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {


            @Override
            public void onChanged(Contrato contrato) {

                DateFormat formatoSalida= new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                DateFormat formatoEntrada= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

                String fechaInicioRecibida= contrato.getFechaInicio();
                String fechaFinRecibida= contrato.getFechaFin();

                Date inicio= null;
                Date fin=null;
                try {
                    inicio= formatoEntrada.parse(fechaInicioRecibida);
                    fin=formatoEntrada.parse(fechaFinRecibida);
                }
               catch (ParseException e){
                    e.printStackTrace();
               }

                String fechaInicio= formatoSalida.format(inicio);
                String fechafin= formatoSalida.format(fin);

                tvCodigoContrato.setText(contrato.getid() + "");
                tvFechaInicio.setText(fechaInicio);
                tvFechaFin.setText(fechafin);
                tvMontoAlquiler.setText("$" + contrato.getimporte());
                tvInquilino.setText(contrato.getinquilinoContrato().getNombre() + " " + contrato.getinquilinoContrato().getApellido());
                tvInmueble.setText("Domicilio:  " + contrato.getinmuebleContrato().getDireccion());
               /* DateTimeFormatter fecha= DateTimeFormatter.ofPattern("MM dd yyyy");
                LocalDateTime date= null;
               try {
                    date = LocalDateTime.parse(contrato.getFechaFin(),fecha);
                }
*/
            }

    });

}


}

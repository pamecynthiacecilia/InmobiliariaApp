package com.example.inmobile.ui.perfil;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText etId,etDNI,etNombre,etApellido,etEmail,etContraseña,etTelefono;
    private Button btEditar,btGuardar;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);

        View vistaPerfil= inflater.inflate(R.layout.perfil_fragment, container, false);
        inicializar(vistaPerfil);

        //observer del mutable
        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {

            @Override
            public void onChanged(Propietario propietario) {

                etId.setText(" "+propietario.getId());
                etDNI.setText(propietario.getDni().toString());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etTelefono.setText(propietario.getTelefono());
                etContraseña.setText(propietario.getContraseña());

            }
        });
        perfilViewModel.obtenerDatos();
        return vistaPerfil;
    }




    private void inicializar(View vistaPerfil) {
        etId = vistaPerfil.findViewById(R.id.etId);
        etDNI = vistaPerfil.findViewById(R.id.tvDNI);
        etNombre = vistaPerfil.findViewById(R.id.tvNombre);
        etApellido = vistaPerfil.findViewById(R.id.tvApellido);
        etEmail = vistaPerfil.findViewById(R.id.tvEmail);
        etContraseña = vistaPerfil.findViewById(R.id.etContraseña);
        etTelefono = vistaPerfil.findViewById(R.id.tvTelefono);
        btEditar = vistaPerfil.findViewById(R.id.btEditar);
        btGuardar= vistaPerfil.findViewById(R.id.btGuardar);

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btEditar.setVisibility(View.INVISIBLE);
                btGuardar.setVisibility(View.VISIBLE);
                etId.setEnabled(true);
                etDNI.setEnabled(true);
                etNombre.setEnabled(true);
                etApellido.setEnabled(true);
                etEmail.setEnabled(true);
                etContraseña.setEnabled(true);
                etTelefono.setEnabled(true);

            }
        });
                btGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btGuardar.setVisibility(View.INVISIBLE);
                        btEditar.setVisibility(View.VISIBLE);
                        perfilViewModel.editar(etId.getText().length(), Long.valueOf(etDNI.getText().toString()),etNombre.getText().toString(),etApellido.getText().toString(),
                                etEmail.getText().toString(),etContraseña.getText().toString(),etTelefono.getText().toString());

               view.getContext();
                    }
                });








    }




}







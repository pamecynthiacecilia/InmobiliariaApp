package com.example.inmobile.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText etId;
    private EditText etDNI;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etEmail;
    private EditText etContraseña;
    private EditText etTelefono;
    private Button btEditar;
    private Button btGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.perfil_fragment, container, false);
        inicializar(root);
        return root;
    }

    private void inicializar(View view) {
        etId = view.findViewById(R.id.etId);
        etDNI = view.findViewById(R.id.tvDNI);
        etNombre = view.findViewById(R.id.tvNombre);
        etApellido = view.findViewById(R.id.tvApellido);
        etEmail = view.findViewById(R.id.tvEmail);
        etContraseña = view.findViewById(R.id.etContraseña);
        etTelefono = view.findViewById(R.id.tvTelefono);
        btEditar = view.findViewById(R.id.btEditar);
        btGuardar = view.findViewById(R.id.btGuardar);
        perfilViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {

            }
        });

    }
}


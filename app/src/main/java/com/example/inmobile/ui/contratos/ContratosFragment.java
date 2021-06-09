package com.example.inmobile.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobile.R;
import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.ui.inmuebles.InmuebleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContratosFragment extends Fragment {

    private RecyclerView rvInmuebles;
    private ContratosViewModel contratosViewModel;
    private InmuebleConContratoAdapter adapter;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.contratos_fragment, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View view) {

        rvInmuebles = view.findViewById(R.id.rvInmuebles);
        contratosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication()).create(ContratosViewModel.class);

        contratosViewModel.getInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                Log.d("salida ", "en contrato activity");
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);
                rvInmuebles.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleConContratoAdapter(context, contratos, getLayoutInflater());
                rvInmuebles.setAdapter(adapter);
            }
            });

        contratosViewModel.mostrarInmueblesConContrato();
    }
}

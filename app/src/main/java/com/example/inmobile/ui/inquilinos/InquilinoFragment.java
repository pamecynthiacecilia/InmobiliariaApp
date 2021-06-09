package com.example.inmobile.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.inmobile.R;
import com.example.inmobile.modelo.Inquilino;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel inquilinoViewModel;
    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvDNI;
    private TextView tvCodigo;
    private TextView tvEmail;
    private TextView tvTelefono;
    private TextView tvGarante;
    private TextView tvTelefonoGarante;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);
        inicializar(root);
        return root;
}

    public void inicializar(View view) {
        tvNombre = view.findViewById(R.id.tvNombre);
        tvApellido = view.findViewById(R.id.tvApellido);
        tvCodigo = view.findViewById(R.id.tvCodigo);
        tvDNI = view.findViewById(R.id.tvDNI);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvTelefono = view.findViewById(R.id.tvTelefono);
        tvGarante = view.findViewById(R.id.tvGarante);
        tvTelefonoGarante = view.findViewById(R.id.tvTelefonoGarante);

        inquilinoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);
        inquilinoViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvNombre.setText(inquilino.getNombre());
                tvApellido.setText(inquilino.getApellido());
                tvCodigo.setText(inquilino.getid() + "");
                tvDNI.setText(inquilino.getdni()+ "");
                tvEmail.setText(inquilino.getEmail());
                tvTelefono.setText(inquilino.gettel());
                tvGarante.setText(inquilino.getNombreGarante());
                tvTelefonoGarante.setText(inquilino.gettelGarante());

        }
    });
        inquilinoViewModel.cargarInquilino(getArguments());



    }

}

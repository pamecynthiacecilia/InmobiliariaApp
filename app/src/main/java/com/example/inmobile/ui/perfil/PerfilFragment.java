package com.example.inmobile.ui.perfil;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText etId,etDNI,etNombre,etApellido,etEmail,etContraseña,etTelefono;
    private Button btEditar,btGuardar;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View vistaPerfil= inflater.inflate(R.layout.perfil_fragment, container, false);

        inicializar(vistaPerfil);

        //observer mutable Propietario
        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {

            @Override
            public void onChanged(Propietario propietario) {

                etId.setText(" "+propietario.getId());
                etDNI.setText(propietario.getDni());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etTelefono.setText(propietario.getTel());
                etContraseña.setText(propietario.getClave());

            }
        });

        perfilViewModel.getMensajeMutable().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {

                AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);
                dialogo.setTitle("Atención");
                dialogo.setMessage(mensaje);
                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });dialogo.show();
            }
        });


        perfilViewModel.obtenerDatos();
        return vistaPerfil;
    }


    private void inicializar(View vistaPerfil) {
        perfilViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);

        etId = vistaPerfil.findViewById(R.id.etId);
        etDNI = vistaPerfil.findViewById(R.id.tvDNI);
        etNombre = vistaPerfil.findViewById(R.id.tvNombre);
        etApellido = vistaPerfil.findViewById(R.id.tvApellido);
        etEmail = vistaPerfil.findViewById(R.id.tvEmail);
        etContraseña = vistaPerfil.findViewById(R.id.etContraseña);
        etTelefono = vistaPerfil.findViewById(R.id.tvTelefono);
        btEditar = vistaPerfil.findViewById(R.id.btEditar);
        btGuardar = vistaPerfil.findViewById(R.id.btGuardar);

        //editar habilita la edicion
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("editar", "en boton editar Activity ");
                btEditar.setVisibility(View.INVISIBLE);
                btGuardar.setVisibility(View.VISIBLE);
                habilitarEditex();

            }
        });

        //guarda cambios y deshabilita la edicion
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("editar", "en editar Activity instancia: ");
               // int id= Integer.parseInt(etId.getText().toString());
                String dni= etDNI.getText().toString();
                String nombre= etNombre.getText().toString();
                String apellido= etApellido.getText().toString();
                String email= etEmail.getText().toString();
                String clave= "";//etContraseña.getText().toString();
                String tel= etTelefono.getText().toString();

                Log.d("editar", "Nombre" + nombre);
                Log.d("editar", "Apellido " + apellido);


                Propietario propietario= new Propietario( dni, nombre, apellido, email, clave, tel);


                Log.d("editar", "en editar Activity: " + propietario.getNombre());


              perfilViewModel.editarPropietario(propietario);



                btEditar.setVisibility(View.VISIBLE);
                btGuardar.setVisibility(View.INVISIBLE);
                deshabilitarEditex();
            }
        });


    }

    private void habilitarEditex(){
        etDNI.setEnabled(true);
        etNombre.setEnabled(true);
        etApellido.setEnabled(true);
        etEmail.setEnabled(true);
        etContraseña.setEnabled(true);
        etTelefono.setEnabled(true);

    }

    private void deshabilitarEditex() {
        etDNI.setEnabled(false);
        etNombre.setEnabled(false);
        etApellido.setEnabled(false);
        etEmail.setEnabled(false);
        etContraseña.setEnabled(false);
        etTelefono.setEnabled(false);
    }

    }






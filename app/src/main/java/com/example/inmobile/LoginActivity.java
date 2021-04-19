package com.example.inmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobile.request.ApiClient;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etContraseña;
    private Button btLogin;

    private LoginViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
    }

    private void inicializar() {
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        etUsuario = findViewById(R.id.etUsuario);
        etContraseña = findViewById(R.id.etContraseña);
        btLogin = findViewById(R.id.btLogin);
        Log.d("Salida", btLogin.toString());
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);

            }
        });
    }
    public void login(View view) {
        ApiClient apiClient= ApiClient.getApi();
        apiClient.login("sonia@mail.com", "123");
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        String mail = etUsuario.getText().toString();
        String password = etContraseña.getText().toString();
        viewModel.autenticar(mail, password);

    }

}

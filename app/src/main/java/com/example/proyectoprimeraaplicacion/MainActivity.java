package com.example.proyectoprimeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarSesion(View view){
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        EditText usuario = (EditText) findViewById(R.id.textUsuario);
        EditText contrasena = (EditText) findViewById(R.id.textContrasena);

        if(preferencias.getString("usuario","").equalsIgnoreCase(usuario.getText().toString()) && preferencias.getString("contrasena","").equalsIgnoreCase(contrasena.getText().toString())){
            Intent i = new Intent(this, PantallaPrincipal.class);
            startActivity(i);
        }
        else{
            Toast notificacion = Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
            notificacion.show();
        }
    }
}
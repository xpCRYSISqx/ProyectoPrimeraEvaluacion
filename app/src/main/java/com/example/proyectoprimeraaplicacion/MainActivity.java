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
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        if(preferencias.getString("usuario","nada").equals("nada")){
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("usuario", "admin");
            editor.putString("contrasena", "admin");
            editor.commit();
        }
    }

    public void iniciarSesion(View view){
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        EditText usuario = (EditText) findViewById(R.id.textUsuario);
        EditText contrasena = (EditText) findViewById(R.id.textContrasena);

        if(preferencias.getString("usuario","").equals(usuario.getText().toString()) && preferencias.getString("contrasena","").equals(contrasena.getText().toString())){
            Intent i = new Intent(this, ListaTareasPendientes.class);
            startActivity(i);
        }
        else{
            Toast notificacion = Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
            notificacion.show();
        }
    }
}
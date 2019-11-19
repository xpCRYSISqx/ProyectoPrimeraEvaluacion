package com.example.proyectoprimeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NuevaTarea extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        Spinner prioridad = (Spinner) findViewById(R.id.prioridad);
        String []opciones = {"URGENTE", "Alta", "Media", "Baja"};
        ArrayAdapter<String> adapter =  new ArrayAdapter <String> (this, R.layout.textview_estilo, opciones);
        prioridad.setAdapter(adapter);
    }

    public void cancelarNuevaTarea(View view){
        Intent i = new Intent(this, ListaTareasPendientes.class);
        startActivity(i);
    }
}

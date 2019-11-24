package com.example.proyectoprimeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetallesTarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tarea);
        Bundle bundle = getIntent().getExtras();

        TextView textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        TextView textViewPriorU = (TextView) findViewById(R.id.textViewPriorU);
        TextView textViewDesc = (TextView) findViewById(R.id.textViewDesc);
        TextView textViewFecha = (TextView) findViewById(R.id.textViewFecha);
        TextView textViewCoste = (TextView) findViewById(R.id.textViewCoste);

        textViewNombre.setText(bundle.getString("nombre"));
        textViewPriorU.setText("Prioridad " + bundle.getString("prioridad"));
        textViewDesc.setText(bundle.getString("descripcion"));
        textViewFecha.setText("Realizar el " + bundle.getString("fecha"));
        textViewCoste.setText("Con un coste de: " + bundle.getString("coste") + "€");
    }

    public void detallesVolver(View view){
        Intent i = new Intent(this, ListaTareasPendientes.class);
        startActivity(i);
    }
}

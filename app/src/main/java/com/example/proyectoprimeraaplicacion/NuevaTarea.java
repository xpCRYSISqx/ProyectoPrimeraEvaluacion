package com.example.proyectoprimeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class NuevaTarea extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        Spinner prioridad = (Spinner) findViewById(R.id.prioridad);
        String []opciones = {"URGENTE", "Alta", "Media", "Baja"};
        ArrayAdapter<String> adapter =  new ArrayAdapter <String> (this, R.layout.textview_estilo, opciones);
        prioridad.setAdapter(adapter);

        agregarListener();
    }

    public void cancelarNuevaTarea(View view){
        Intent i = new Intent(this, ListaTareasPendientes.class);
        startActivity(i);
    }

    public void crearNuevaTarea(View view){
        BaseDatos tareas = new BaseDatos(this, "tareas", null, 1);
        SQLiteDatabase bd = tareas.getWritableDatabase();
        EditText textNombre = (EditText) findViewById(R.id.textNombre);
        EditText textDescripcion = (EditText) findViewById(R.id.textDescripcion);
        EditText textFecha = (EditText) findViewById(R.id.textFecha);
        Spinner spinPrior = (Spinner) findViewById(R.id.prioridad);
        EditText textCoste = (EditText) findViewById(R.id.textCoste);

        String nombre = textNombre.getText().toString();
        String descripcion = textDescripcion.getText().toString();
        String fecha = textFecha.getText().toString();
        String prioridad = spinPrior.getSelectedItem().toString();
        String coste = textCoste.getText().toString();

        if(nombre.equals("")){
            Toast notificacion = Toast.makeText(this, "La tarea no tiene nombre", Toast.LENGTH_LONG);
            notificacion.show();
        }else if(descripcion.equals("")){
            Toast notificacion = Toast.makeText(this, "La tarea no tiene descripcion", Toast.LENGTH_LONG);
            notificacion.show();
        } else if(fecha.equals("")){
            Toast notificacion = Toast.makeText(this, "La tarea no tiene fecha", Toast.LENGTH_LONG);
            notificacion.show();
        } else if(coste.equals("")) {
            Toast notificacion = Toast.makeText(this, "La tarea no tiene un coste", Toast.LENGTH_LONG);
            notificacion.show();
        } else {
            ContentValues tarea = new ContentValues();
            tarea.put("nombre", nombre);
            tarea.put("descripcion", descripcion);
            tarea.put("fecha", fecha);
            tarea.put("coste", coste);
            tarea.put("prioridad", prioridad);
            tarea.put("hecha", false);
            Intent i = new Intent(this, ListaTareasPendientes.class);
            startActivity(i);
        }
    }

    public void agregarListener() {
        EditText fecha = (EditText) findViewById(R.id.textFecha);
        fecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textFecha:
                mostrarDatePickerDialog();
                break;
        }
    }

    private void mostrarDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "/" + (month+1) + "/" + year;
                EditText fecha = (EditText) findViewById(R.id.textFecha);
                fecha.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

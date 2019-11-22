package com.example.proyectoprimeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListaTareasHechas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas_hechas);

        cargarTareas();
    }

    public void nuevaTareaH(View view){
        Intent i = new Intent(this, NuevaTarea.class);
        startActivity(i);
    }

    public void cargarTareas(){
        ArrayList<Tarea> listaTareas = new ArrayList<>();
        BaseDatos tareas = new BaseDatos(this, "tareas", null, 1);
        SQLiteDatabase bd = tareas.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from tareas where hechas = false", null);

        if(fila.moveToFirst()){

        }
    }
}
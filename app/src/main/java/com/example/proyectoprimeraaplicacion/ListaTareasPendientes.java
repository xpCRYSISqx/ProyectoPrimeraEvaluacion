package com.example.proyectoprimeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaTareasPendientes extends AppCompatActivity {

    ArrayList<Tarea> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas_pendientes);

        cargarTareas();
    }

    public void nuevaTareaP(View view){
        Intent i = new Intent(this, NuevaTarea.class);
        startActivity(i);
    }

    public void irListaTareasHechas(View view){
        Intent i = new Intent(this, ListaTareasHechas.class);
        startActivity(i);
    }

    public void cargarTareas(){
        String nombre, descripcion, fecha, coste, prioridad, hecha;
        listaTareas = new ArrayList<>();
        ListView lista = (ListView) findViewById(R.id.listaTareasP);

        BaseDatos tareas = new BaseDatos(this, "tarea", null, 1);
        SQLiteDatabase bd = tareas.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from tareas", null);

        if(fila.getCount() <= 0) {
            fila.close();
            bd.close();
        }
        else{
            if (fila.moveToFirst()) {
                nombre = fila.getString(0);
                descripcion = fila.getString(1);
                fecha = fila.getString(2);
                coste = fila.getString(3);
                prioridad = fila.getString(4);
                hecha = fila.getString(5);
                Tarea tarea = new Tarea(nombre, descripcion, fecha, coste, prioridad, hecha);
                listaTareas.add(tarea);
            }
        }
        fila.close();
        bd.close();

        AdaptadorTarea adaptador = new AdaptadorTarea(this);
        lista.setAdapter(adaptador);
    }

    class AdaptadorTarea extends ArrayAdapter<Tarea> {
        AppCompatActivity appCompatActivity;

        AdaptadorTarea(AppCompatActivity context) {
            super(context, R.layout.estilo_listas, listaTareas);  appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.estilo_listas, null);

            TextView listaNombre = (TextView) item.findViewById(R.id.listaNombre);
            TextView listaPrioridad = (TextView) item.findViewById(R.id.listaPrioridad);

            listaNombre.setText(listaTareas.get(position).getNombre());
            listaPrioridad.setText(listaTareas.get(position).getDescripcion());

            return(item);
        }
    }
}

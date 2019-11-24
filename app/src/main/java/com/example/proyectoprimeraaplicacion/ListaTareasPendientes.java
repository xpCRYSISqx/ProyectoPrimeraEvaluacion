package com.example.proyectoprimeraaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaTareasPendientes extends AppCompatActivity {

    ArrayList<Tarea> listaTareas;
    AdaptadorTarea adaptador;
    AdapterView.AdapterContextMenuInfo info;
    BaseDatos tareas;

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

        tareas = new BaseDatos(this, "tarea", null, 1);
        SQLiteDatabase bd = tareas.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from tareas where hecha = 'n'", null);

        if(fila.getCount() <= 0) {
            fila.close();
            bd.close();
        }
        else{
            if (fila.moveToFirst()) {
                do {
                    nombre = fila.getString(0);
                    descripcion = fila.getString(1);
                    fecha = fila.getString(2);
                    coste = fila.getString(3);
                    prioridad = fila.getString(4);
                    hecha = fila.getString(5);
                    Tarea tarea = new Tarea(nombre, descripcion, fecha, coste, prioridad, hecha);
                    listaTareas.add(tarea);
                } while(fila.moveToNext());
            }
        }
        fila.close();
        bd.close();

        adaptador = new AdaptadorTarea(this);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

        }});
        registerForContextMenu(lista);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.acerca:
                Intent i = new Intent(this, AcercaDe.class);
                startActivity(i);
                break;
            case R.id.cambiarContra:

            case R.id.crearNuevaTarea:
                Intent i2 = new Intent(this, NuevaTarea.class);
                startActivity(i2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_tareas, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.modificar:
                Intent i = new Intent(this, NuevaTarea.class);
                startActivity(i);
                return true;
            case R.id.borrar:
                tareas = new BaseDatos(this, "tarea", null, 1);

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaTareasPendientes.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿Elimina esta tarea?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        SQLiteDatabase bd = tareas.getWritableDatabase();
                        bd.delete("tareas", "nombre = ?", new String[]{listaTareas.get(info.position).getNombre()});
                        listaTareas.remove(info.position);
                        adaptador.notifyDataSetChanged();
                        bd.close();
                    }});
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }});
                dialogo1.show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}

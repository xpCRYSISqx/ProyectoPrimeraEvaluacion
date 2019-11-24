package com.example.proyectoprimeraaplicacion;

public class Tarea {
    String nombre;
    String descripcion;
    String fehca;
    String prioridad;
    String coste;
    String hecha;

    public Tarea(String nombre, String descripcion, String fehca, String prioridad, String coste, String hecha){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fehca = fehca;
        this.prioridad = prioridad;
        this.coste = coste;
        this.hecha = hecha;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFehca() {
        return fehca;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getCoste() {
        return coste;
    }

    public String isHecha() {
        return hecha;
    }
}
package com.bolivarsoft.model;

import java.util.List;

public class MetadataMenu {
    public String titulo;
    public List<Opcion> opciones;
    public Configuracion configuracion;

    public MetadataMenu(String titulo, List<Opcion> opciones, Configuracion configuracion) {
        this.titulo = titulo;
        this.opciones = opciones;
        this.configuracion = configuracion;
    }

    public MetadataMenu() {
    }
}

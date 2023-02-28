package com.gestion_inventario.modelo;

import java.awt.Image;

public class Producto {

    private String id;
    private String nombre;
    private String imagen_link;
    private String categoria;
    private Image imagen;
    private int cantidad;
    private int cantidad_minima;
    private boolean seleccionado;
    private double precio;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image img) {
        this.imagen = img;
    }

    public String getImagenLink() {
        return imagen_link;
    }

    public void setImagenLink(String url){
        this.imagen_link = url; 
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    
    public int getCantidadMinima(){
        return cantidad_minima;
    }
    
    public void setCantidadMinima(int cantidad_minima){
        this.cantidad_minima = cantidad_minima;
    }
    
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean estaSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

}

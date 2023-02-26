/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion_inventario.vista;


import java.awt.Image;

/**
 *
 * @author Leo
 */
public class Producto {
    private String id;
    private String nombre;
    private Image imagen;
    private boolean seleccionado;
    private double precio;
    private String imagen_link;

    public Producto(String id,String nombre,double precio,Image imagen,String imagen_link) {
        
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.imagen_link = imagen_link;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Image getImagen() {
        return imagen;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public String getImagenLink(){
        return imagen_link;
    }
    
      public boolean estaSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

}

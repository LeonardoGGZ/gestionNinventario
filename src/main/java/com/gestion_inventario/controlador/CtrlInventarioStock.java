package com.gestion_inventario.controlador;

import com.gestion_inventario.modelo.ConsultasInventario;
import com.gestion_inventario.modelo.ConsultasProducto;
import com.gestion_inventario.modelo.ConsultasRegistros;
import com.gestion_inventario.modelo.Producto;
import com.gestion_inventario.vista.AgregarStockUI;
import com.gestion_inventario.vista.ConsultarProductosUI;
import com.gestion_inventario.vista.MostrarAnimacionDeCarga;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

public class CtrlInventarioStock implements ActionListener {

    private Producto producto;
    private ConsultasProducto consultasProducto;
    private ConsultasInventario consultasInventario;
    private ConsultasRegistros consultasRegistros;
    private AgregarStockUI agregarStockFrame;
    private ConsultarProductosUI productosFrame;
    private MostrarAnimacionDeCarga dialog_animacion;

    //Agregar y actualizar
    public CtrlInventarioStock(Producto producto, ConsultasInventario consultasInventario, ConsultasProducto consultasProducto, ConsultasRegistros consultasRegistros, AgregarStockUI agregarStockFrame) {
        this.producto = producto;
        this.consultasInventario = consultasInventario;
        this.consultasProducto = consultasProducto;
        this.consultasRegistros = consultasRegistros;
        this.agregarStockFrame = agregarStockFrame;
        // this.productosFrame = productosFrame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == agregarStockFrame.addButton) {

            // producto = productosFrame.listaProductos.getModel().getElementAt(productosFrame.listaProductos.getSelectedIndex());
            // Aquí es donde implementarías la lógica para añadir el producto al stock
            // utilizando los valores de los campos de texto.
            // Conectarse a la base de datos
            producto.setCantidad(Integer.parseInt(agregarStockFrame.cantidadField.getText()));
            System.out.println("Cantidad producto " + producto.getCantidad());

            //Verifica si hay un registro existente para el producto en la tabla inventario, Si no la hay la crea
            boolean existeEnInventario = consultasInventario.existeEnInventario(producto);

            if (existeEnInventario) {
                consultasRegistros.insertarRegistro(producto);
                System.out.println("Insertando nuevo registro");
                agregarStockFrame.dispose();
            }

            if (!existeEnInventario) {
                System.out.println("No exise en inventario");
                int cant_minima = Integer.parseInt(agregarStockFrame.cantidad_minima_field.getText());
                int cant = Integer.parseInt(agregarStockFrame.cantidadField.getText());

                if (cant < cant_minima) {
                       agregarStockFrame.cantidadField.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    producto.setCantidadMinima(cant_minima);
                    producto.setPrecio(Double.parseDouble(agregarStockFrame.precioField.getText()));

                    consultasRegistros.insertarRegistro(producto);
                    consultasInventario.insertarEnInventario(producto);
                    System.out.println("Insertando nuevo registro");
                    agregarStockFrame.dispose();
                }
            }   
        }
    }

    public void iniciar() {

        if (!consultasInventario.existeEnInventario(producto)) {

            agregarStockFrame.prepararGUI(false);
        } else {
            agregarStockFrame.prepararGUI(true);
        }
        
        this.agregarStockFrame.addButton.addActionListener(this);
        agregarStockFrame.setVisible(true);
    }

}

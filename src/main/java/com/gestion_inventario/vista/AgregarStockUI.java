package com.gestion_inventario.vista;

import com.gestion_inventario.modelo.Producto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AgregarStockUI extends JDialog {

    public JTextField nombreField;
    public JTextField idProductoField;
    public JTextField precioField;
    public JTextField cantidadField;
    public JTextField cantidad_minima_field;
    public JButton addButton;
    public JPanel panel_producto;
    public Producto producto;
    private JFrame parent;

    //El controlador CtrlInventarioStock controla la vista de esta interfaz
    public AgregarStockUI(JFrame parent, Producto producto) {

        super(parent, true);
        this.parent = parent;
        setPreferredSize(new Dimension(400, 300));
        this.producto = producto;
    }

    public void prepararGUI(boolean existeEnInventario) {

        if (!existeEnInventario) {
            panel_producto = new JPanel(new GridLayout(6, 2));
        } else {
            panel_producto = new JPanel(new GridLayout(4, 2));
        }

        panel_producto.add(new JLabel("Nombre:"));
        nombreField = new JTextField(producto.getNombre());
        nombreField.setEditable(false);
        panel_producto.add(nombreField);

        panel_producto.add(new JLabel("ID Producto:"));
        idProductoField = new JTextField(producto.getId());
        idProductoField.setEditable(false);
        panel_producto.add(idProductoField);

        panel_producto.add(new JLabel("Cantidad:"));
        cantidadField = new JTextField("30");
        panel_producto.add(cantidadField);

        if (!existeEnInventario) {

            panel_producto.add(new JLabel("Precio:"));
            precioField = new JTextField(String.valueOf(producto.getPrecio()));

            panel_producto.add(precioField);

            panel_producto.add(new JLabel("Cantidad Minima:"));
            cantidad_minima_field = new JTextField("25");
            panel_producto.add(cantidad_minima_field);

        }

        addButton = new JButton("Añadir a Stock");

        panel_producto.add(addButton);

        add(panel_producto, BorderLayout.CENTER);

        
        pack();
        setLocationRelativeTo(parent);
    }

}

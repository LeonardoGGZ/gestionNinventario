package com.gestion_inventario.controlador;

import com.gestion_inventario.modelo.ConsultasInventario;
import com.gestion_inventario.modelo.ConsultasProducto;
import com.gestion_inventario.modelo.ConsultasRegistros;
import com.gestion_inventario.modelo.Producto;
import com.gestion_inventario.vista.ConsultarProductosUI;
import com.gestion_inventario.vista.MostrarAnimacionDeCarga;
import com.gestion_inventario.vista.AgregarStockUI;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class CtrlProducto implements ActionListener {

    private Producto producto;
    private ConsultasProducto consultasProducto;
    private ConsultarProductosUI frame;
    private MostrarAnimacionDeCarga dialog_animacion;

    //Visualizacion
    public CtrlProducto(Producto producto, ConsultasProducto consultasProducto, ConsultarProductosUI frame) {

        this.producto = producto;
        this.consultasProducto = consultasProducto;
        this.frame = frame;
        this.frame.botonBuscar.addActionListener(this);

        //Establece el oyente para que al seleccionar un producto se muestre la imagen a la derecha en el panel imagen
        this.frame.listaProductos.addListSelectionListener(e -> mostrarImagenProducto(this.frame.listaProductos.getSelectedValue()));

        //Se crea un oyente para que al hacer doble click sobre un elemento de la lista se habra un nuevo frame para la carga de datos
        this.frame.listaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verifica si se hizo doble clic
                    int index = frame.listaProductos.locationToIndex(e.getPoint()); // Obtiene el índice del elemento
                    Producto producto = frame.listaProductos.getModel().getElementAt(index);
                    //modeloLista.getElementAt(index); // Obtiene el producto seleccionado

                    
                    AgregarStockUI inputFrame = new AgregarStockUI(frame, producto);
                    ConsultasInventario consultasInventario = new ConsultasInventario();
                    ConsultasRegistros consultasRegistros = new ConsultasRegistros();
                            
                    CtrlInventarioStock controCtrlInventarioStock = new CtrlInventarioStock(producto, consultasInventario, consultasProducto, consultasRegistros, inputFrame);
                    
                    controCtrlInventarioStock.iniciar();
                    
                    
                    
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frame.botonBuscar) {

            System.out.println(frame.campoBusqueda.getText());

            java.util.List<Producto> arrayProductos;

            System.out.println(frame.listaProductos.getSize());

            if (!frame.campoBusqueda.getText().isEmpty()) {

                Thread mostrar_animacion = new Thread() {
                    @Override
                    public void run() {
                        dialog_animacion = new MostrarAnimacionDeCarga(frame);
                        dialog_animacion.setLocationRelativeTo(frame);
                        dialog_animacion.pack();
                        dialog_animacion.setVisible(true);
                    }
                };

                mostrar_animacion.start();

                frame.botonBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                arrayProductos = consultasProducto.buscarPorCaracteres(frame.campoBusqueda.getText());

                //frame.listaProductos = consultasProducto.buscarPorCaracteres(frame.campoBusqueda.getText());
                DefaultListModel<Producto> modeloLista = (DefaultListModel<Producto>) frame.listaProductos.getModel();

                modeloLista.clear();

                for (Producto producto : arrayProductos) {
                    //System.out.print("Añadiendo a modelo: "+ producto.getNombre());
                    modeloLista.addElement(producto);
                }
                

                //nuevoModelo.addElement(frame.listaProductos.getModel().getElementAt(1));
               // System.out.println(frame.listaProductos.getModel().getSize());
              //  frame.listaProductos.setModel(modeloLista);

                // frame.scrollListaProductos.add(frame.listaProductos);
                // Crear un nuevo modelo de lista y agregar los resultados de la búsqueda
                //  DefaultListModel<Producto> nuevoModelo = new DefaultListModel<>();
                //  frame.listaProductos.setModel(nuevoModelo);
                frame.botonBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                dialog_animacion.dispose();

                dialog_animacion = null;

                frame.imagenProducto.setIcon(null);
            }

        }
             
  
    }

    private void mostrarImagenProducto(Producto producto) {
        if (producto != null && producto.getImagen() != null) {
            frame.imagenProducto.setIcon(new ImageIcon(producto.getImagen()));
        } else {
            frame.imagenProducto.setIcon(null);
        }
    }
}

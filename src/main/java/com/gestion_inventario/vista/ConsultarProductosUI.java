package com.gestion_inventario.vista;

import com.gestion_inventario.modelo.Producto;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;

public class ConsultarProductosUI extends JFrame {

    public JTextField campoBusqueda;
    public JButton botonBuscar;
    public JList<Producto> listaProductos;
    public JLabel imagenProducto;
    public JFrame frame;
    private JDialog dialog_animacion;
    public JScrollPane scrollListaProductos;

    public ConsultarProductosUI() {
        super("Añadir productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        crearInterfaz();
        pack();
        setLocationRelativeTo(null);
        frame = this;
    }

    private void crearInterfaz() {

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }
        });

        campoBusqueda = new JTextField(20);

        campoBusqueda.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            // Aquí va la acción que deseas ejecutar al presionar el botón
                            // Puede tardar un tiempo en ejecutarse
                            botonBuscar.doClick();
                            return null;
                        }

                        @Override
                        protected void done() {
                            setCursor(null);
                        }
                    };

                    worker.execute();

                }
            }

        });

        botonBuscar = new JButton("Buscar");
        botonBuscar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botonBuscar.doClick();
                }
            }
        });

        
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(botonBuscar);

        JButton botonAgregar = new JButton("Agregar");

        botonAgregar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botonAgregar.doClick();
                }
            }
        });

        
        JFrame frame = this;

        
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              /*  List<Producto> productosSeleccionados = listaProductos.getSelectedValuesList();
                for (Producto producto : productosSeleccionados) {
                    System.out.println("Nombre del producto: " + producto.getNombre());
                    System.out.println("Id del producto: " + producto.getId());

                    if (producto.estaSeleccionado()) {

                    } else {
                        producto.setSeleccionado(true);
                    }
                }*/
            }
        });

        panelBusqueda.add(botonAgregar);

        //  listaProductos = new JList<>(new DefaultListModel<>());
        listaProductos = new JList<>(new DefaultListModel<>());

        listaProductos.setCellRenderer(new ProductoListCellRenderer());

        scrollListaProductos = new JScrollPane(listaProductos);
        scrollListaProductos.setPreferredSize(new Dimension(400, 200));
        scrollListaProductos.setBorder(BorderFactory.createTitledBorder("Resultados de la búsqueda"));

        
        //////////////
        imagenProducto = new JLabel();
        imagenProducto.setHorizontalAlignment(JLabel.CENTER);
        imagenProducto.setVerticalAlignment(JLabel.CENTER);

        JPanel panelImagenProducto = new JPanel(new BorderLayout());
        panelImagenProducto.add(imagenProducto, BorderLayout.CENTER);
        panelImagenProducto.setPreferredSize(new Dimension(200, 100));
        panelImagenProducto.setBorder(BorderFactory.createTitledBorder("Imagen del producto"));

        ////////////
        ////////////////////////
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(scrollListaProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelImagenProducto, BorderLayout.EAST);

        add(panelPrincipal);

        /////////////////////////
    }



    private class ProductoListCellRenderer extends JLabel implements ListCellRenderer<Producto> {

        public ProductoListCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(JLabel.LEFT);
            setVerticalAlignment(JLabel.CENTER);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends Producto> list, Producto value, int index, boolean isSelected, boolean cellHasFocus) {
            String texto = value.getNombre();

            setText(texto);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setIcon(null);
            if (value.getImagen() != null) {
                setIcon(new ImageIcon(value.getImagen()));
            }

            return this;
        }
    }

}
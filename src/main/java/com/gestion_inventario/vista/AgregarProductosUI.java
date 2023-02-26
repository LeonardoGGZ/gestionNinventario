/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion_inventario.vista;

/**
 *
 * @author Leo
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
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
import javax.swing.SwingUtilities;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;

public class AgregarProductosUI extends JFrame {

    private JTextField campoBusqueda;
    private JButton botonBuscar;
    private JList<Producto> listaProductos;
    private JLabel imagenProducto;
    private JFrame frame;
    private JDialog dialog_animacion;

    public AgregarProductosUI() {
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

        /* JPanel glassPane;
        // Crear el panel de cristal
        glassPane = new JPanel(new BorderLayout());
        JLabel loadingLabel = new JLabel("Cargando...", SwingConstants.CENTER);
        glassPane.add(loadingLabel, BorderLayout.CENTER);
        glassPane.setBackground(new Color(0, 0, 0, 100));
        glassPane.setOpaque(false);
      
        setGlassPane(glassPane);
          glassPane.setVisible(true);
        glassPane.setEnabled(true);*/
        campoBusqueda = new JTextField(20);

        campoBusqueda.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // No se usa
            }

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

            @Override
            public void keyReleased(KeyEvent e) {
                // No se usa
            }
        });

        botonBuscar = new JButton("Buscar");
        botonBuscar.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // No se usa
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    botonBuscar.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No se usa
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (!campoBusqueda.getText().isEmpty()) {

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

                        botonBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        buscarProductos(campoBusqueda.getText());
                        botonBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                        dialog_animacion.dispose();

                        dialog_animacion = null;

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(botonBuscar);

        JButton botonAgregar = new JButton("Agregar");

        botonAgregar.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // No se usa
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    botonAgregar.doClick();

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No se usa
            }
        });

        JFrame frame = this;

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                List<Producto> productosSeleccionados = listaProductos.getSelectedValuesList();
                for (Producto producto : productosSeleccionados) {
                    System.out.println("Nombre del producto: " + producto.getNombre());
                    System.out.println("Id del producto: " + producto.getId());

                    if (producto.estaSeleccionado()) {

                    } else {
                        producto.setSeleccionado(true);
                    }
                }
            }
        });

        panelBusqueda.add(botonAgregar);

        listaProductos = new JList<>(new DefaultListModel<>());
        listaProductos.setCellRenderer(new ProductoListCellRenderer());
        listaProductos.addListSelectionListener(e -> mostrarImagenProducto(listaProductos.getSelectedValue()));

        listaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verifica si se hizo doble clic
                    int index = listaProductos.locationToIndex(e.getPoint()); // Obtiene el índice del elemento
                    Producto producto = listaProductos.getModel().getElementAt(index);
                    //modeloLista.getElementAt(index); // Obtiene el producto seleccionado

                    ProductDialog inputFrame = new ProductDialog(frame, producto.getNombre(), producto.getId(), Double.toString(producto.getPrecio()), "50", producto.getImagenLink());

                }
            }
        });

        JScrollPane scrollListaProductos = new JScrollPane(listaProductos);
        scrollListaProductos.setPreferredSize(new Dimension(400, 200));
        scrollListaProductos.setBorder(BorderFactory.createTitledBorder("Resultados de la búsqueda"));

        imagenProducto = new JLabel();
        imagenProducto.setHorizontalAlignment(JLabel.CENTER);
        imagenProducto.setVerticalAlignment(JLabel.CENTER);

        JPanel panelImagenProducto = new JPanel(new BorderLayout());
        panelImagenProducto.add(imagenProducto, BorderLayout.CENTER);
        panelImagenProducto.setPreferredSize(new Dimension(200, 100));
        panelImagenProducto.setBorder(BorderFactory.createTitledBorder("Imagen del producto"));

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
        panelPrincipal.add(scrollListaProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelImagenProducto, BorderLayout.EAST);

        add(panelPrincipal);
    }

    private void abrirNuevoFrame(Producto producto) {
        JFrame frame = new JFrame("Detalles del producto");
        JLabel labelNombre = new JLabel("Nombre: " + producto.getNombre());
        JLabel labelPrecio = new JLabel("Precio: " + producto.getId());
        // Agrega los componentes al frame
        frame.getContentPane().setLayout(new GridLayout(2, 1));
        frame.getContentPane().add(labelNombre);
        frame.getContentPane().add(labelPrecio);
        frame.pack();
        frame.setVisible(true);
    }

    private void buscarProductos(String textoBusqueda) throws IOException {

        List<Producto> productos = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:GestionDeInventario.db")) {

            String query = "SELECT id_producto, nombre, imagen, precio FROM productos WHERE nombre LIKE ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, textoBusqueda + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id_producto");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                Image imagen = null;
                String imagen_link = resultSet.getString("imagen");

                if ((resultSet.getString("imagen") != null)) {
                    System.out.println(resultSet.getString("imagen"));
                }

                if (resultSet.getString("imagen") != null && (ImageIO.read(new URL(imagen_link)) != null)) {
                    try {
                        imagen = redimensionarImagen(ImageIO.read(new URL(imagen_link)), 64, 64);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (imagen != null) {
                    Producto producto = new Producto(id, nombre, precio, imagen, imagen_link);
                    productos.add(producto);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        DefaultListModel<Producto> modeloLista = (DefaultListModel<Producto>) listaProductos.getModel();
        modeloLista.clear();
        for (Producto producto : productos) {
            modeloLista.addElement(producto);
        }
        imagenProducto.setIcon(null);
    }

    private Image redimensionarImagen(Image imagenOriginal, int ancho, int alto) {
        if (imagenOriginal != null) {
            return imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        }
        System.out.println("se colo una imagen");
        return imagenOriginal;
    }

    private void mostrarImagenProducto(Producto producto) {
        if (producto != null && producto.getImagen() != null) {
            imagenProducto.setIcon(new ImageIcon(producto.getImagen()));
        } else {
            imagenProducto.setIcon(null);
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AgregarProductosUI().setVisible(true);
            }
        });
    }
}

class ProductDialog extends JDialog {

    private JTextField nombreField;
    private JTextField idProductoField;
    private JTextField precioField;
    private JTextField cantidadField;
    private JTextField cantidad_minima_field;

    public ProductDialog(JFrame parent, String nombre, String id_producto, String precio, String cantidad, String imagen) {

        super(parent, true);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(400, 300));

        JPanel panel;
        if (!existeEnInventario(id_producto)) {
            panel = new JPanel(new GridLayout(6, 2));
        } else {
            panel = new JPanel(new GridLayout(4, 2));
        }

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(nombre);
        nombreField.setEditable(false);
        panel.add(nombreField);

        panel.add(new JLabel("ID Producto:"));
        idProductoField = new JTextField(id_producto);
        idProductoField.setEditable(false);
        panel.add(idProductoField);

        if (!existeEnInventario(id_producto)) {
            panel.add(new JLabel("Precio:"));
            precioField = new JTextField(precio);
            panel.add(precioField);
        }

        panel.add(new JLabel("Cantidad:"));
        cantidadField = new JTextField(cantidad);
        panel.add(cantidadField);

        if (!existeEnInventario(id_producto)) {

            panel.add(new JLabel("Cantidad Minima:"));
            cantidad_minima_field = new JTextField("50");
            panel.add(cantidad_minima_field);
        }

        JButton addButton = new JButton("Añadir a Stock");
        addButton.addActionListener(e -> {

            // Aquí es donde implementarías la lógica para añadir el producto al stock
            // utilizando los valores de los campos de texto.
            try {

                // Conectarse a la base de datos
                Connection conn = DriverManager.getConnection("jdbc:sqlite:GestionDeInventario.db");

                // Crear una sentencia SQL
                String sql = "INSERT INTO registros (fecha, tipo_operacion, id_producto, cantidad) VALUES (?, ?, ?, ?)";

                // Preparar la sentencia para la ejecución
                PreparedStatement pstmt = conn.prepareStatement(sql);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(new Date(System.currentTimeMillis()));
                pstmt.setString(1, formattedDate);

                pstmt.setString(2, "compra");
                pstmt.setString(3, id_producto);
                pstmt.setInt(4, Integer.parseInt(cantidadField.getText()));

                // Ejecutar la sentencia
                pstmt.executeUpdate();

                //Verifica si hay un registro existente para el producto en la tabla inventario
                if (!existeEnInventario(id_producto)) {
                    //Si no la hay la crea

                    // Sentencia preparada para insertar en la tabla inventario
                    sql = "INSERT INTO inventario (id_producto, cantidad_en_stock, stock_minimo, precio_unitario, ultima_fecha_ingreso, imagen) VALUES (?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id_producto); // valor para id_producto
                    pstmt.setInt(2, Integer.parseInt(cantidadField.getText())); // valor para cantidad en stock
                    pstmt.setInt(3, Integer.parseInt(cantidad_minima_field.getText())); // valor para cantidad minima
                    pstmt.setDouble(4, Double.parseDouble(precioField.getText()));

                    formattedDate = dateFormat.format(new Date(System.currentTimeMillis()));
                    pstmt.setString(5, formattedDate);

                    pstmt.setString(6, imagen);
                    // Ejecutar la sentencia
                    pstmt.executeUpdate();

                    System.out.println("Registro insertado en la tabla inventario");

                }

// Cerrar la conexión a la base de datos
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            dispose();
        });
        panel.add(addButton);

        add(panel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

   
public boolean existeEnInventario(String idProducto) {

        boolean existeProducto = false;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establecer conexión con la base de datos
            conexion = DriverManager.getConnection("jdbc:sqlite:GestionDeInventario.db");

            // Preparar consulta SQL con un PreparedStatement
            ps = conexion.prepareStatement("SELECT COUNT(*) FROM inventario WHERE id_producto = ?");
            ps.setString(1, idProducto);

            // Ejecutar consulta y obtener resultado
            rs = ps.executeQuery();

            // Verificar si hay algún registro en la tabla con el id_producto especificado
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    existeProducto = true;
                    System.out.println("existe");
                } else {
                    System.out.println("no existe");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existeProducto;
    }


}



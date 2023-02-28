package com.gestion_inventario.modelo;

import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ConsultasProducto extends Conexion {

    public boolean buscar(Producto producto) {

        PreparedStatement ps = null;
        Connection conn = getConexion();
        String sql = "SELECT * FROM productos WHERE id_producto=?";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, producto.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                producto.setId(rs.getString("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(Double.parseDouble(rs.getString("precio")));
                producto.setCategoria(rs.getString("categoria"));
                producto.setImagenLink(rs.getString("imagen"));
                
                conn.close();
                
                return true;
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Producto> buscarPorCaracteres(String chars_inicio) {

        // Create a list model to hold Producto objects
        //DefaultListModel<Producto> modeloProductos = new DefaultListModel<>();
        List<Producto> listaProductos = new ArrayList<>();

        Connection conn = getConexion();

        try {

            String query = "SELECT id_producto, nombre, imagen, precio FROM productos WHERE nombre LIKE ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, chars_inicio + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Producto producto = new Producto();
                String id = resultSet.getString("id_producto");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                Image imagen = null;
                String imagen_link = resultSet.getString("imagen");

                if ((resultSet.getString("imagen") != null)) {
                    System.out.println(resultSet.getString("imagen"));
                }

                //Se listan solo productos con imagen
                if (resultSet.getString("imagen") != null) {

                    try {
                        imagen = ImageIO.read(new URL(imagen_link));

                        if (imagen != null) {

                            imagen = redimensionarImagen(imagen, 64, 64);
                            
                        }
                    } catch (Exception e) {
                        System.out.println("Imagen inexistente error");
                    }

                }

                if (imagen != null) {
                    producto.setId(id);
                    producto.setNombre(nombre);
                    System.out.println(nombre);
                    producto.setPrecio(precio);
                    producto.setImagen(imagen);
                    producto.setImagenLink(imagen_link);

                    listaProductos.add(producto);

                } else {

                }

            }

            conn.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
         try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        // imagenProducto.setIcon(null);
        if (listaProductos == null) {
            System.out.println("Error lista productos null");
        }

        return listaProductos;
    }

    private Image redimensionarImagen(Image imagenOriginal, int ancho, int alto) {
        if (imagenOriginal != null) {
            return imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        }
        System.out.println("se colo una imagen");
        return imagenOriginal;
    }

}

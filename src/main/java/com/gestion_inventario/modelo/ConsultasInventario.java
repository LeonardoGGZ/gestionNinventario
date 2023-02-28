package com.gestion_inventario.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class ConsultasInventario extends Conexion {

    public boolean existeEnInventario(Producto producto) {

        boolean existeProducto = false;
        Connection conn = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            // Preparar consulta SQL con un PreparedStatement
            ps = conn.prepareStatement("SELECT COUNT(*) FROM inventario WHERE id_producto = ?");
            ps.setString(1, producto.getId());

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return existeProducto;
    }

    public boolean insertarEnInventario(Producto producto) {

        // Sentencia preparada para insertar en la tabla inventario
        String sql;
        PreparedStatement pstmt;
        Connection conn = getConexion();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(new Date(System.currentTimeMillis()));

        try {
            sql = "INSERT INTO inventario (id_producto, cantidad_en_stock, stock_minimo, precio_unitario, ultima_fecha_ingreso, imagen) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, producto.getId()); // valor para id_producto
            System.out.println("Cantidad en insertarInventario: " + producto.getCantidad());
            pstmt.setInt(2, producto.getCantidad()); // valor para cantidad en stock
            pstmt.setInt(3, producto.getCantidadMinima()); // valor para cantidad minima
            pstmt.setDouble(4, producto.getPrecio());
            pstmt.setString(5, formattedDate);
            pstmt.setString(6, producto.getImagenLink());
            // Ejecutar la sentencia
            pstmt.executeUpdate();

            System.out.println("Registro insertado en la tabla inventario");
            
            
            conn.close();
            
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
         try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        

        return false;
    }

    //la actualizacion de la ultima fecha de ingreso al insertar un nuevo registro queda a cargo de un trigger
    
    //queda implementar metodo para actualizar datos del inventario
}

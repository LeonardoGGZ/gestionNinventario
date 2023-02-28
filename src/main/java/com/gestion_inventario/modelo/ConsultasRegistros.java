package com.gestion_inventario.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class ConsultasRegistros extends Conexion {

    public boolean insertarRegistro(Producto producto) {

        // Crear una sentencia SQL
        Connection conn = getConexion();

        try {

            String sql = "INSERT INTO registros (fecha, tipo_operacion, id_producto, cantidad) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia para la ejecución
            PreparedStatement pstmt = conn.prepareStatement(sql);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date(System.currentTimeMillis()));
            pstmt.setString(1, formattedDate);

            pstmt.setString(2, "compra");
            pstmt.setString(3, producto.getId());
            pstmt.setInt(4, producto.getCantidad());

            // Ejecutar la sentencia
            pstmt.executeUpdate();

            
            conn.close();
            
            return true;

        } catch (Exception e) {
            e.printStackTrace();

        }

        
         try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return false;
    }
    
    
}

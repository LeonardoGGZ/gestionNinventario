
package com.gestion_inventario.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection con = null;

    public Connection getConexion() {
        String url = "jdbc:sqlite:GestionDeInventario.db";

        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return con;
    }

}

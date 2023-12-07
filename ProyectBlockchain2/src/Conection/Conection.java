/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class Conection {
    private Connection conn;
    private Statement stm;
    
    public Conection(String pIP, String pListener, String pUsuario, String pClave){       
      try
      {
        Class.forName("oracle.jdbc.OracleDriver") ;
        this.conn = DriverManager.getConnection("jdbc:oracle:thin:@" +
                pIP +":1521:" + pListener, pUsuario, pClave);
        this.stm = this.conn.createStatement();
       }
       catch(Exception e) {
           e.printStackTrace();
       }
    }
   
    public Connection getConnection(){
       return this.conn;
    }
   
    public ArrayList<Object[]> consultar(String query){
        ArrayList<Object[]> aResultados = new ArrayList<>();

        try 
        {
            ResultSet rs = this.stm.executeQuery(query);
            int columnCount = ((ResultSetMetaData) rs.getMetaData()).getColumnCount();

            while (rs.next()) 
            {
                Object[] fila = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) 
                {
                    fila[i-1] = rs.getObject(i);
                }
                aResultados.add(fila);
            }
        }catch (Exception e) { }
        return aResultados;
    }

    public Object[] consultarUnicoResultado(String query) {
        Object[] fila = null;
        try {
            ResultSet rs = this.stm.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (rs.next()) {
                fila = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    fila[i-1] = rs.getObject(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fila;
    }
   
    public boolean insertar(String query, Object[] params) {
        try {
            PreparedStatement pStmt = this.conn.prepareStatement(query);
            if (params != null) {
                for (int i = 0; i < params.length; i++) 
                    pStmt.setObject(i + 1, params[i]);
                pStmt.executeUpdate();
                return true;  // inserción exitosa
            }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return false;  // hubo un error
            }
        return false;
    } 
   
    public String consultarHash(String sql, Object[] parametros) {
        String storedPasswordHash = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = this.conn.prepareStatement(sql);
            if (parametros != null) {
                for (int i = 0; i < parametros.length; i++) {
                    pStmt.setObject(i + 1, parametros[i]);
                }
            }
            rs = pStmt.executeQuery();
            if (rs.next()) {
                storedPasswordHash = rs.getString("contrasena_encriptada");
            }
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace();
        } finally {
            // Asegúrate de cerrar los recursos en el bloque finally para evitar fugas de recursos.
            try {
                if (rs != null) rs.close();
                if (pStmt != null) pStmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return storedPasswordHash;
    }
    public boolean usuarioExiste(String sql, Object[] parametros) {
        // Inicializar la variable que determinará si el usuario existe o no.
        boolean existe = false;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros de la consulta.
            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }
            // Ejecutar la consulta y procesar el resultado.
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Si hay un resultado, verificar el conteo.
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return existe;
    }
}

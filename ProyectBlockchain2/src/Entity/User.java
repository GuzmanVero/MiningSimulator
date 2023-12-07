/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;


import Conection.Conection;
import org.mindrot.jbcrypt.BCrypt;
public class User {
    
    private int idUser;
    private String name;
    private String password;
    private double monto;
    private boolean isGuardado = false;
    
    public User() {
       }
 
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public boolean isGuardado() {
        return isGuardado;
    }

    public void setGuardado(boolean isGuardado) {
        this.isGuardado = isGuardado;
    }

    public void insertar(Conection pCon) {
        String passwordcryp = BCrypt.hashpw(this.password, BCrypt.gensalt());

        String sql = "INSERT INTO usuarios(usuario_id, nombre, contrasena_encriptada) " +
                     "VALUES (usuario_id_seg.nextval,?, ?)";
        Object[] parametros = {this.name, passwordcryp};
        boolean exito = pCon.insertar(sql, parametros);
        if (exito) {
            this.setGuardado(true);
        }
    }  

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }   
}

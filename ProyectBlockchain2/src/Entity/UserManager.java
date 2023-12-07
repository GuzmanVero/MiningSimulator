/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marce
 */
public class UserManager {
    private Map<String, String> usuariosYContrasenas;
    private ArrayList<User> usuarios;
    
    public UserManager(){
        this.usuariosYContrasenas = new HashMap<>();
        this.usuarios = new ArrayList<>();
    }
    
    public void registrarUsuario(String usuario, String contrasena) throws UnsupportedEncodingException{
        String contrasenaEncriptada = encriptarContrasena(contrasena);
        usuariosYContrasenas.put(usuario, contrasenaEncriptada);
        User newUser = new User(usuario, contrasena);
        agregarUsuario(newUser);

    System.out.println("Usuario registrado con éxito");
    }   
    
    public boolean verificarCredenciales(String usuario, String contrasena) throws UnsupportedEncodingException{
        if(usuariosYContrasenas.containsKey(usuario)){
            String contrasenaAlmacenada = usuariosYContrasenas.get(usuario);
            String contrasenaIngresadaEncriptada = encriptarContrasena(contrasena);
            
            return contrasenaAlmacenada.equals(contrasenaIngresadaEncriptada);
        } else {return false;}
    }
    
    private String encriptarContrasena(String contrasena) throws UnsupportedEncodingException{
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes("UTF-8"));
            StringBuilder hexadecimalString = new StringBuilder();

            for (byte b : hash) {
                String hexadecimal = Integer.toHexString(0xff & b);
                if (hexadecimal.length() == 1) {
                    hexadecimalString.append('0');
                }
                hexadecimalString.append(hexadecimal);
            }
            return hexadecimalString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void agregarUsuario(User usuario){
        this.usuarios.add(usuario);
    }
    
    public boolean existeUsuario(String nombreUsuario){
        for(User user : this.usuarios){
            if (user.getName().equals(nombreUsuario)) {
                return true;
            }
        }
        return false;
    }
}

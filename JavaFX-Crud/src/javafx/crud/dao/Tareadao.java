//CREACION DE OPERACIONES LOGICAS PARA LA BD
/*
*Dentro de la clase Tareadao se realizan los metodos para 
*manipular la base de datos, con el uso igual de la clase
*Tarea la cual es la que obtiene o manda los datos, la clase
*es encargada de las operaciones logicas del crud (Eliminar,
*Agregar, Modificar o Mostrar) que son obtenidas o son 
*mandadas a la interfaz del usuario
*/
package javafx.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.crud.Modelo.Tarea;
import javafx.crud.conexion.ConexionMySql;


public class Tareadao {
    private ConexionMySql fabricadeconexion;
    
    public Tareadao(){
        this.fabricadeconexion = new ConexionMySql();
    }
    public boolean registrar(Tarea tarea){
        try {
            String SQL="INSERT INTO usuarios(nombre,telefono,email,peso,altura,especificacion)"+"values(?,?,?,?,?,?)";
            Connection connection = this.fabricadeconexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
           sentencia.setString(1,tarea.getNombre());
           sentencia.setString(2,tarea.getTelefono());
           sentencia.setString(3,tarea.getEmail());
           sentencia.setFloat(5,tarea.getAltura());
           sentencia.setFloat(4,tarea.getPeso());
           sentencia.setString(6,tarea.getEspecificacion());
           
           sentencia.executeUpdate();
           sentencia.close();
            return true;
        } catch (Exception e) {
         System.err.println("OCURRIO UN ERROR EN LA TAREA");
         System.err.println("Mensaje De Error"+e.getMessage());
         System.err.println("Detalle del Error:");
         e.printStackTrace();
         return false;
        }
        
    }
    
    public List<Tarea>listar(){
        
        List<Tarea> listaTareas = new ArrayList<>();
        
        try {         
           
           String SQL="SELECT * FROM usuarios;";
           
           Connection connection = this.fabricadeconexion.getConnection();
           
           PreparedStatement Sentencia = connection.prepareStatement(SQL);
           
           ResultSet data = Sentencia.executeQuery();
           
           while (data.next()){
               
               Tarea tarea = new Tarea();
               
               tarea.setId(data.getInt(1));
               tarea.setNombre(data.getString(2));
               tarea.setTelefono(data.getString(3));
               tarea.setEmail(data.getString(4));
               tarea.setPeso(Float.parseFloat(data.getString(5)));
               tarea.setAltura(Float.parseFloat(data.getString(6)));
               tarea.setEspecificacion(data.getString(7));
               
               listaTareas.add(tarea);
               
                       }
           data.close();
           Sentencia.close();
           
        } catch (Exception e) {
         System.err.println("OCURRION UN ERROR EN LA TAREA");
         System.err.println("Mensaje De Error"+e.getMessage());
         System.err.println("Detalle del Error:");
         e.printStackTrace();  
        }
         return listaTareas;
    }
    public boolean Editar(Tarea tarea){
        try {
            String SQL ="UPDATE usuarios SET nombre=?,telefono=?,email=?,peso=?,altura=?,especificacion=? WHERE idUsuario=?";
            Connection connection = this.fabricadeconexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
           sentencia.setString(1,tarea.getNombre());
           sentencia.setString(2,tarea.getTelefono());
           sentencia.setString(3,tarea.getEmail());
           sentencia.setFloat(5,tarea.getAltura());
           sentencia.setFloat(4,tarea.getPeso());
           sentencia.setString(6,tarea.getEspecificacion());
           
           sentencia.setInt(7, tarea.getId());
            
           sentencia.executeUpdate();
           
           sentencia.close();
           
           return true;
            
        } catch (Exception e) {
         System.err.println("OCURRION UN ERROR AL EDITAR LA TAREA TAREA");
         System.err.println("Mensaje De Error"+e.getMessage());
         System.err.println("Detalle del Error:");
         e.printStackTrace();
         return false;
        }
      
        
    }
    public boolean Eliminar(int id){
        try {
            String SQL ="DELETE FROM usuarios WHERE idUsuario=? ";
            Connection connection = this.fabricadeconexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            sentencia.setInt(1, id);
            sentencia.executeUpdate();
            sentencia.close();
            return true;
            
        } catch (Exception e) {
            System.err.println("OCURRION UN ERROR AL INTENTAR ELIMINAR EL REGISTRO");
         System.err.println("Mensaje De Error"+e.getMessage());
         System.err.println("Detalle del Error:");
         e.printStackTrace();
         return false;
        }
    }
}

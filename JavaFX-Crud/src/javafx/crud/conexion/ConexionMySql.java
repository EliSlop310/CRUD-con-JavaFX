//CONEXION A LA BASE DE DATOS EN MYSQL
/*
 * Dentro de la clase ConexionMySql se realiza la inicializacion
 * de las variables (con los datos de la BD) para proceder a conectarla
 * donde mencione una alerta de si se logro conectar o no
 */
package javafx.crud.conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class ConexionMySql {
    private Connection connection;
    private String usuario = "root";
    private String password="";
    private String servidor="localhost";
    private String puerto ="3306";
    private String nombreBD="crudangular";//Se toma la misma BD de la unidad anterior
    
    private String url="jdbc:mysql://"+servidor+":"+puerto+"/"+nombreBD+"?serverTimezone=UTC";

    private String driver="com.mysql.jdbc.Driver";
    
    public ConexionMySql() {
        try{
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url, usuario, password);
            if (connection !=null){
                System.out.println("CONEXION REALIZADA CORRECTA MENTE");
            }
            
    }catch(Exception e){
        System.err.println("OCURRION UN ERROR EN LA CONEXION");
         System.err.println("Mensaje De Error"+e.getMessage());
          System.err.println("Detalle del Error:");
          e.printStackTrace();
    }
    
    
}

    public Connection getConnection() {
        return connection;
    }
}

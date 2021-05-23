//EJECUCION DE LA INTERFAZ
/*
 * En la clase JavaFXCrud se extiende a Application
 * lo cual significa que sera la clase principal el 
 * cual ejecuta el programa y bueno dentro de esta ya
 * estarian las funcionalidades para el crud
 */
package javafx.crud;

import java.net.URI;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXCrud extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        URI uri = Paths.get("src/javafx/crud/vistas/FXMLTarea.fxml").toAbsolutePath().toUri();
        System.out.println("URI"+uri.toString());
        
        Parent root = FXMLLoader.load(uri.toURL());
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

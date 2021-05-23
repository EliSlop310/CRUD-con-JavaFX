//INICIALIZACION DE VARIABLES Y METODOS EN LA INTERFAZ
/*
 * Se da inicio a las variables que corresponde a la interfaz
 * ademas de que aqui se realizan diferentes metodos para que 
 * la interfaz mande, reciba o identifique los datos que se van
 * a manipular con el crud (Eliminar, Registrar, Mostrar y Editar)
 * Ademas de darles una identificacion a la tabla que muestra
 * los datos
 */
package javafx.crud.Controlador;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.crud.Modelo.Tarea;
import javafx.crud.dao.Tareadao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class FXMLTareaController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumero;
    
    @FXML
    private TextField textEmail;
    
    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtEspecificacion;
    
    @FXML
    private Button btnAgregar;
    
    @FXML
    private TableView<Tarea> tvTareas;
    
    private Tareadao Tareadao;
    
    private ContextMenu cmOpciones;
    
    private Tarea tareaSelect;
     
    public void initialize(URL url, ResourceBundle rb) {
       this.Tareadao = new Tareadao();
       CargarTareas();
       
       cmOpciones = new ContextMenu();
       
       MenuItem miEditar = new MenuItem("Editar");
       MenuItem miEliminar = new MenuItem("Eliminar");
       
       cmOpciones.getItems().addAll(miEditar,miEliminar);
       
       miEditar.setOnAction( new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event) {
               int index = tvTareas.getSelectionModel().getSelectedIndex();
               
               tareaSelect = tvTareas.getItems().get(index);
               System.out.println( tareaSelect );
               txtNombre.setText(tareaSelect.getNombre());
               txtNumero.setText(tareaSelect.getTelefono());
               textEmail.setText(tareaSelect.getEmail());
               txtPeso.setText(String.valueOf(tareaSelect.getPeso()));
               txtAltura.setText(String.valueOf(tareaSelect.getAltura()));
               txtEspecificacion.setText(tareaSelect.getEspecificacion());
               
               
           }
       } );
       
       miEliminar.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event) {
               int index = tvTareas.getSelectionModel().getSelectedIndex();
               Tarea tareaEliminar = tvTareas.getItems().get(index);
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMACION");
                alert.setHeaderText(null);
                alert.setContentText("¿ESTAS SEGURO QUE DESEAS ELIMINAR ESTE USUARIO : "+tareaEliminar.getNombre()+"?");
                alert.initStyle(StageStyle.UTILITY);
                 Optional<ButtonType> resul = alert.showAndWait();
                 if (resul.get()== ButtonType.OK){
                     boolean rsp = Tareadao.Eliminar(tareaEliminar.getId());
                        if (rsp){
                   Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                   alert2.setTitle("Exitoso");
                   alert2.setHeaderText(null);
                   alert2.setContentText("SE ELIMINO CORRECTAMENTE ");
                   alert2.initStyle(StageStyle.UTILITY);
                   alert2.showAndWait();
                   
                   CargarTareas();

                   tareaSelect = null;

               }else{
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                   alert2.setTitle("Fallo");
                   alert2.setHeaderText(null);
                   alert2.setContentText("NO SE PUDO EDITAR DE MANERA CORRECTA ");
                   alert2.initStyle(StageStyle.UTILITY);
                   alert2.showAndWait();   
               }
                 }
           }
           
       });
       
       tvTareas.setContextMenu(cmOpciones);
       
    }
     
@FXML
   private void btnAgregar(ActionEvent event) {
       
       if(tareaSelect==null){
                this.Tareadao = new Tareadao();

            Tarea tarea = new Tarea();
            tarea.setNombre(txtNombre.getText());
            tarea.setTelefono(txtNumero.getText());
            tarea.setEmail(textEmail.getText());
            tarea.setPeso(Float.parseFloat(txtPeso.getText()));
            tarea.setAltura(Float.parseFloat(txtAltura.getText()));
            tarea.setEspecificacion(txtEspecificacion.getText());

            System.out.println(tarea);

            boolean rsp = this.Tareadao.registrar(tarea);

            if (rsp){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exitoso");
                alert.setHeaderText(null);
                alert.setContentText("El Registro Ha sido Exitoso ");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();

                limpiarCampos();
                CargarTareas();
            }else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fallo");
                alert.setHeaderText(null);
                alert.setContentText("El Registro No Fue Completado De Manera Correcta ");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();   
            }
       }else{
             tareaSelect.setNombre(txtNombre.getText());
             tareaSelect.setTelefono(txtNumero.getText());
             tareaSelect.setEmail(textEmail.getText());
             tareaSelect.setPeso(Float.parseFloat(txtPeso.getText()));
             tareaSelect.setAltura(Float.parseFloat(txtAltura.getText()));
             tareaSelect.setEspecificacion(txtEspecificacion.getText());
             
             boolean rsp = this.Tareadao.Editar(tareaSelect);
              
             if (rsp){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exitoso");
                alert.setHeaderText(null);
                alert.setContentText("SEGUARDO CORRECTAMENTE ");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();

                limpiarCampos();
                
                CargarTareas();
                
                tareaSelect = null;
                
            }else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fallo");
                alert.setHeaderText(null);
                alert.setContentText("NO SE PUDO EDITAR DE MANERA CORRECTA ");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();   
            }
       }
       
       
        
        
    }
   private void limpiarCampos(){
       txtNombre.setText("");
       txtNumero.setText("");
       textEmail.setText("");
       txtPeso.setText("");
       txtAltura.setText("");
       txtEspecificacion.setText("");
   }
   
public void CargarTareas(){
    tvTareas.getItems().clear();
    tvTareas.getColumns().clear();
    
    List<Tarea> tareas = this.Tareadao.listar();
    
    ObservableList<Tarea> data = FXCollections.observableArrayList(tareas);
    
    TableColumn idcol = new TableColumn("id");
    idcol.setCellValueFactory(new PropertyValueFactory("id"));
    
    TableColumn nombrecol = new TableColumn("nombre");
    nombrecol.setCellValueFactory(new PropertyValueFactory("nombre"));
    
    TableColumn telefonocol = new TableColumn("telefono");
    telefonocol.setCellValueFactory(new PropertyValueFactory("telefono"));
    
    TableColumn Emailcol = new TableColumn("Email");
    Emailcol.setCellValueFactory(new PropertyValueFactory("Email"));
    
    TableColumn alturacol = new TableColumn("altura");
    alturacol.setCellValueFactory(new PropertyValueFactory("altura"));
    
    TableColumn pesocol = new TableColumn("peso");
    pesocol.setCellValueFactory(new PropertyValueFactory("peso"));
     
    TableColumn especificacioncol = new TableColumn("especificacion");
    especificacioncol.setCellValueFactory(new PropertyValueFactory("especificacion"));

    
    tvTareas.setItems(data);
    tvTareas.getColumns().addAll(idcol,nombrecol, telefonocol, Emailcol, alturacol, pesocol, especificacioncol);
    
}
   
    
}

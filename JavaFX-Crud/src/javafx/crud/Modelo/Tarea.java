//CREACION DE METODOS PARA OBTENER O MANDAR DATOS
/*
 * Los métodos get y set, son simples métodos que usamos en 
 * las clases para mostrar (get) o modificar (set) el valor
 *  de los elementos de la base de datos o caractetisticas
 *  de estos 
 */
package javafx.crud.Modelo;


public class Tarea {
    private int id ;
    private String nombre;
    private String telefono;
    private String Email;
    private float altura;
    private float peso;
    private String especificacion;

    public Tarea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    
}

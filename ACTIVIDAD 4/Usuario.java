import java.util.ArrayList;

public class Usuario {

    /* Atributos de la clase Usuario */
    private String nombre;
    private int id;


    /* Constructor */
    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    /* Métodos getters y setters */
    public String getNombre(){
        return nombre; 
    }
    public void setNombre(String nombre){
        this.nombre=nombre; 
    }
    public int getId(){
        return this.id;
    }
}

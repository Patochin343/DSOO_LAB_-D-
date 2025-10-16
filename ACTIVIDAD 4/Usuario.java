import java.util.ArrayList;

public class Usuario {

    /* Atributos de la clase Usuario */
    private String nombre;
    private ArrayList<Libro> librosPrestados;


    /* Constructor */
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.librosPrestados = new ArrayList<>();
    }

    /* Métodos getters y setters */
    public String getNombre(){
        return nombre; 
    }
    public void setNombre(String nombre){
        this.nombre=nombre; 
    }
    

    /* Métodos para tomar prestado*/
    public void tomarPrestado(Libro libro) {
        if (libro.isDisponible()) {
            libro.setDisponible(false);
            librosPrestados.add(libro);
            System.out.println(nombre + " ha tomado prestado: " + libro.getTitulo());
        } else {
            System.out.println("El libro '" + libro.getTitulo() + "' no está disponible.");
        }
    }


    /* Métodos para devolver libros */
    public void devolverLibro(Libro libro) {
        if (librosPrestados.contains(libro)) {
            libro.setDisponible(true);
            librosPrestados.remove(libro);
            System.out.println(nombre + " ha devuelto: " + libro.getTitulo());
        } else {
            System.out.println(nombre + " no tenía prestado este libro.");
        }
    }


    /* Métodos para mostrar libros prestados */
    public void mostrarLibrosPrestados() {
        System.out.println("\nLibros prestados por " + nombre + ":");
        if (librosPrestados.isEmpty()) {
            System.out.println("Ninguno.");
        } else {
            for (Libro libro : librosPrestados) {
                System.out.println("- " + libro.getTitulo());
            }
        }
    }
}

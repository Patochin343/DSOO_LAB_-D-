import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private ArrayList<Libro> librosPrestados;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.librosPrestados = new ArrayList<>();
    }
    public String getNombre() { return nombre; }
    public void tomarPrestado(Libro libro) {
        if (libro.estaDisponible()) {
            libro.setDisponible(false);
            librosPrestados.add(libro);
            System.out.println(nombre + " ha tomado prestado: " + libro.getTitulo());
        } else {
            System.out.println("El libro '" + libro.getTitulo() + "' no está disponible.");
        }
    }
    public void devolverLibro(Libro libro) {
        if (librosPrestados.contains(libro)) {
            libro.setDisponible(true);
            librosPrestados.remove(libro);
            System.out.println(nombre + " ha devuelto: " + libro.getTitulo());
        } else {
            System.out.println(nombre + " no tenía prestado este libro.");
        }
    }
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

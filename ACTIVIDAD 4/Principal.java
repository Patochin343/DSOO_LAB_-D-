import java.util.*;

public class Principal {
    public static void main(String[] args) {
        
        Biblioteca.crearBiblioteca("Biblioteca Central");
        Biblioteca biblioteca = Biblioteca.getBiblioteca();

        Iniciar();
    }

    public static void Iniciar() { 
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            MenuUsuario();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            OpcionUsuario(opcion);
        } while (opcion != 6);

        scanner.close();
    }

    public static void MenuUsuario(){
        System.out.println("1. Agregar libro");
        System.out.println("2. Mostrar libros");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Mostrar libros prestados");
        System.out.println("6. Salir");
    }

    public static void OpcionUsuario(int opcion){
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = Biblioteca.getBiblioteca();
        switch(opcion){
            case 1:
                System.out.print("Ingrese el título del libro: ");
                String titulo = scanner.nextLine();
                System.out.print("Ingrese el autor del libro: ");
                String autor = scanner.nextLine();
                System.out.print("Ingrese el ISBN del libro: ");
                String ISBN = scanner.nextLine();
                Libro libro = new Libro(ISBN, autor, titulo);
                libro.setDisponible(true);
                biblioteca.addLibro(libro);
                System.out.println("Libro agregado exitosamente.");
                break;
            case 2:
                biblioteca.mostrarLibros();
                break;
            case 3:
                System.out.print("Ingrese el nombre del usuario: ");
                String nombreUsuario = scanner.nextLine();
                Usuario usuario = new Usuario(nombreUsuario);
                System.out.print("Ingrese el ISBN del libro a prestar: ");
                String isbnPrestamo = scanner.nextLine();
                System.out.print("Ingrese la fecha de préstamo (dd/mm/yyyy): ");
                String fechaPrestamo = scanner.nextLine();
                String resultadoPrestamo = biblioteca.prestarA(usuario, isbnPrestamo, fechaPrestamo);
                System.out.println(resultadoPrestamo);
                break;
            case 4:
                System.out.print("Ingrese el nombre del usuario: ");
                String nombreDevolucion = scanner.nextLine();
                Usuario usuarioDevolucion = new Usuario(nombreDevolucion);
                System.out.print("Ingrese el ISBN del libro a devolver: ");
                String isbnDevolucion = scanner.nextLine();
                String resultadoDevolucion = biblioteca.devolverDe(usuarioDevolucion, isbnDevolucion);
                System.out.println(resultadoDevolucion);
                break;
            case 5:
                System.out.print("Ingrese el nombre del usuario: ");
                String nombreMostrar = scanner.nextLine();
                Usuario usuarioMostrar = new Usuario(nombreMostrar);
                usuarioMostrar.mostrarLibrosPrestados();
                break;
            case 6:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}

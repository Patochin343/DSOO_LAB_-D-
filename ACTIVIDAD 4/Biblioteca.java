import java.util.*;
public class Biblioteca{


	/* Atributos de la biblioteca */
	private ArrayList<Libro> libros;
	private ArrayList<Prestamo> prestamos;
	private static Biblioteca instancia;
	private String nombre;

	/* Constructor */
	public Biblioteca(String nombre){
		this.nombre = nombre;
		this.libros = new ArrayList<>();
		this.prestamos = new ArrayList<>();
	}


	/* Método para crear la biblioteca */
	public static void crearBiblioteca(String nombre){
		if(Biblioteca.getBiblioteca() == null)
		{
			Biblioteca.instancia = new Biblioteca(nombre);
		}
	}


	/* Método para obtener la instancia de la biblioteca */
	public static Biblioteca getBiblioteca(){
		return Biblioteca.instancia;
	}

	/* Método para mostrar los libros disponibles */
	public void mostrarLibros(){
		System.out.println("titulo\tautor\tISBN");
		for(Libro libro: this.libros)
		{
			System.out.println((libro.getTitulo()) +"\t"+(libro.getAutor())+"\t"+(libro.getISBN()));
		}
	}


	/* Método para buscar un libro por su ISBN */
	private Libro buscarPorISBN(String ISBN){
		for(Libro libro: this.libros)
		{
			if(libro.getISBN().equals(ISBN))
			{
				return libro;
			}
		}
		return null;
	}


	/* Método para prestar un libro a un usuario */
	public String prestarA(Usuario usuario, String ISBN, String fecha){
		Object[] disponible = this.verificarDisponibilidad(ISBN);
		if(disponible[0].equals("Disponible!")){
			Libro libro = (Libro)disponible[1];
			Prestamo prestamo = new Prestamo();
			prestamo.setUsuario(usuario);
			prestamo.setLibro(libro);
			prestamo.setFecha(fecha);
			this.prestamos.add(prestamo);
			libro.setDisponible(false);
			return "exito";
		}
		return disponible[0].toString();
	}


	/*Metodo para devolver un libro */
	public String devolverDe(Usuario usuario, String ISBN){
		Prestamo prestamo = null;
		for(Prestamo p: this.prestamos){
			if(p.getUsuario().getNombre().equals(usuario.getNombre()) && p.getLibro().getISBN().equals(ISBN)){
				prestamo = p;
				break;
			}
		}
		if(prestamo != null){
			this.prestamos.remove(prestamo);
			Libro libro = prestamo.getLibro();
			libro.setDisponible(true);
			return "Devolución exitosa.";
		}
		return "No se encontró el préstamo.";
	}

	/* Método para verificar la disponibilidad de un libro */
	private Object[] verificarDisponibilidad(String ISBN){
		Libro libro = this.buscarPorISBN(ISBN);
		if(libro == null)
			return new Object[] {"El libro no existe", null};
		if(!libro.isDisponible())
			return new Object[] {"El libro no esta disponible", null};
		return new Object[] {"Disponible!", libro};

	}


	/* Método para agregar un libro a la biblioteca */
	public void addLibro(Libro libro){
		this.libros.add(libro);
	}


	/* Métodos de acceso */
	public String getNombre(){
		return this.nombre;
	}
	public ArrayList<Libro> getLibros(){
		return this.libros;
	}
	public ArrayList<Prestamo> getPrestamos(){
		return this.prestamos;
	}


	/* Métodos de modificación */
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public void setLibros(ArrayList<Libro> libros){
		this.libros = libros;
	}
	public void setPrestamos(ArrayList<Prestamo> prestamos){
		this.prestamos = prestamos;
	}
}

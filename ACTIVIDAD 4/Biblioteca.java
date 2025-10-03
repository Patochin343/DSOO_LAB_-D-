import java.util.*;
public class Biblioteca{
	private ArrayList<Libro> libros;
	private ArrayList<Prestamo> prestamos;
	private static Biblioteca instancia;
	private nombre;

	private Biblioteca(String nombre){
		this.nombre = nombre;
		this.libros = new ArrayList<>();>>>>
	}
	public static crearBiblioteca(String nombre){
		if(Biblioteca.getBiblioteca() == null){
			Biblioteca.instancia = new Biblioteca(nombre);
		}
	}
	public static Biblioteca getBiblioteca(){
		return Biblioteca.instancia;
	}
	public void mostrarLibros(){
		System.out.println("titulo\tautor\tISBN");
		for(Libro libro: this.libros){
			System.out.println(libro.titulo +"\t"+libro.autor+"\t"+libro.ISBN);
		}
	}
	private Libro buscarPorISBN(String ISBN){
		for(Libro libro: this.libros){
			if(libro.getISBN().equals(ISBN){
				return libro;
			}
		}
		return null;
	}
	public String prestarA(Usuario usuario, String ISBN, String fecha){
		Object[] disponible = this.verificarDisponibilidad(ISBN);
		if(disponible[0].equals("Disponible!"){
			Libro = (Libro)disponible[1];
			Prestamo prestamo = new Prestamo();
			prestamo.setUsuario(usuario);
			prestamo.setLibro(libro);
			prestamo.setFecha(fecha);
			this,prestamos.add(prestamo);
			libro.setDisponible(false);
			return "exito";
		}
		return disponible[0];
	}
	private Object[] verificarDisponibilidad(String ISBN){
		Libro libro = this.buscarPorISBN(ISBN);
		if(libro == null)
			return new Object[] {"El libro no existe", null};
		if(!libro.isDisponible())
			return new Object[] {"El libro no esta disponible", null};
		return new Object[] {"Disponible!", libro};

	}
	public void addLibro(Libro libro){
		this.libros.add(libro);
	}


	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre({
		return this.nombre;
	}
	public void setLibros(ArrayList<Libro> libros){
		this.libros = libros;
	}
	public ArrayList<Libro> getLibros(){
		return this.libros;
	}
	public void setPrestamos(ArrayList<Prestamo> prestamos){
		this.prestamos = prestamos;
	}

	public ArrayList<Prestamo> getPrestamos(){
		return this.prestamos;
	}
}

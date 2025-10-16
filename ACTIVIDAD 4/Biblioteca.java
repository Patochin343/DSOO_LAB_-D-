import java.util.*;
public class Biblioteca{


	/* Atributos de la biblioteca */
	private ArrayList<Libro> libros;
	private ArrayList<Usuario> usuariosRegistrados;
	private ArrayList<Prestamo> prestamos;

	private static Biblioteca instancia;
	private String nombre;

	/* Constructor */
	private Biblioteca(String nombre){
		this.nombre = nombre;
		this.libros = new ArrayList<>();
		this.prestamos = new ArrayList<>();
		this.usuariosRegistrados = new ArrayList<>();
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
		System.out.println("titulo\tautor\tISBN\tDisponible");
		for(Libro libro: this.libros)
		{
			System.out.println((libro.getTitulo()) +"\t"+(libro.getAutor())+"\t"+(libro.getISBN()) + "\t" + (libro.isDisponible()));
		}
	}

	/*Metodo para mostrar usuarios que prestaron un libro */
	public void mostrarUsuariosRegistrados(){
		System.out.println("Nombre\tId");
		for(Usuario usuario: this.usuariosRegistrados)
		{
			System.out.println((usuario.getNombre()) +"\t"+(usuario.getId()));
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

	/* Método para buscar un usuario por su id */
	private Usuario buscarUsuarioPorId(int Id){
		for(Usuario usuario: this.usuariosRegistrados){
			if(usuario.getId() == Id)
				return usuario;
		}
		return null;
	}


	/* Método para prestar un libro a un usuario nuevo */
	public String prestarA(Usuario usuario, String ISBN, String fecha){
		Usuario usr = buscarUsuarioPorId(usuario.getId());
		if (usr == null) {
			registrarUsuario(usuario);
			return prestarA(usuario.getId(), ISBN, fecha);
		}
		else{
			return prestarA(usuario.getId(), ISBN, fecha);
		}
	}

	/* Método para prestar un libro a un usuario existente (sobrecargado) */
	public String prestarA(int IdUsuario, String ISBN, String fecha){
		Object[] disponible = verificarDisponibilidad(ISBN);
		if(disponible[0].equals("Disponible!")){
			Usuario usuario = buscarUsuarioPorId(IdUsuario);
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
	public String devolverDe(int idUsuario, String ISBN){
		Usuario usr = buscarUsuarioPorId(idUsuario);
		Libro libro = buscarPorISBN(ISBN);

		if(usr == null || libro == null){
			return "el usuario o el libro no existen";
		}
		else{
			for(Prestamo p: this.prestamos){
				boolean verificarIdUsuario = p.getUsuario().getId() == usr.getId();
				boolean verificarISBN = p.getLibro().getISBN() == libro.getISBN();
				if(verificarIdUsuario && verificarISBN){
					this.prestamos.remove(p);
					libro.setDisponible(true);
					eliminarUsuarioPorNumeroDePrestamos(idUsuario);
					return "Devolucion exitosa";
				}
				else{
					return "El usuario y el libro indicados no estan relacionados en un prestamo";
				}
			}
			return"sin prestamos";
		}
	}

	/*Metodo para mostrar libros prestados de un usuario */
	public void mostrarLibrosPrestadosDe(int idUsuario){
		System.out.println("titulo\tautor\tISBN");
		for(Prestamo p:this.prestamos){
			if(p.getUsuario().getId() == idUsuario){
				System.out.println((p.getLibro().getTitulo()) +"\t"+(p.getLibro().getAutor())+"\t"+(p.getLibro().getISBN()) + "\t" + (p.getLibro().isDisponible()));
			}
		}
	}

	/*Metodo para eliminar a un usuario si su numero de prestamos es 0 */
	private void eliminarUsuarioPorNumeroDePrestamos(int idUsuario){
		int prestamosNum = 0;
		for(Prestamo p: this.prestamos){
			if(p.getUsuario().getId()==idUsuario)
				prestamosNum++;
		}
		if(prestamosNum == 0){
			this.usuariosRegistrados.remove(buscarUsuarioPorId(idUsuario));
		}
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
	/* Metodo para agregar un usuario registrado */
	public void registrarUsuario(Usuario usuario){
		this.usuariosRegistrados.add(usuario);
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
	public ArrayList<Usuario> getUsuarios(){
		return this.usuariosRegistrados;
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
	public void setUsuarios(ArrayList<Usuario> usuarios){
		this.usuariosRegistrados = usuarios;
	}
}

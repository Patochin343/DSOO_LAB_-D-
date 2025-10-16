public class Prestamo{

	/* Atributos de la clase Prestamo */
	private Usuario usuario;
	private Libro libro;
	private String fechaPrestamo;


	/* Constructor */
	public Prestamo(){
		this.libro = null;
		this.usuario = null;
		this.fechaPrestamo = "";
	}

	
	/* Constructor sobrecargado */
	public Prestamo(Usuario usuario, Libro libro){
		this.libro = libro;
		this.usuario = usuario;
	}


	/* Metodos getters y setters */
	public Usuario getUsuario(){
		return this.usuario;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	public Libro getLibro(){
		return this.libro;
	}
	public void setLibro(Libro libro){
		this.libro = libro;
	}
	public String getFecha(){
		return this.fechaPrestamo;
	}
	public void setFecha(String fecha){
		this.fechaPrestamo = fecha;
	}
}

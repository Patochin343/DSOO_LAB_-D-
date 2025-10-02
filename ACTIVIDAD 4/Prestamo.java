public class Prestamo{
	private Usuario usuario;
	private Libro libro;
	private String fechaPrestamo;

	public Prestamo(Usuario usuario, Libro libro){
		this.libro = libro;
		this.usuario = usuario;
	}
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

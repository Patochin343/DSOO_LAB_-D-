public class Libro {
    private boolean disponible; 
    private String ISBN;
    private String autor; 
    private String titulo;

    public Libro(String ISBN, String autor, String titulo) {
        this.ISBN=ISBN;
        this.autor=autor;
        this.titulo=titulo; 
    }
    public boolean isDisponible(){
        return disponible;
    }
    public void setDisponible(boolean disponible){
        this.disponible=disponible;
    }
    public String getISBN(){
        return ISBN;
    }
    public void setISBN(String ISBN){
        this.ISBN=ISBN;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor=autor;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
}

public class Alumno{

    private int CUI;
    private String nombre;
    private String apellido;

    /*Constructor Sobrecargado para inicializar los datos */
    public Alumno(int CUI, String apellido, String nombre) {
        this.CUI = CUI;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    /*Metodos Acsesores */
    public int getCUI() {
        return CUI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    /*Metodos Mutadores */
    public void setCUI(int CUI) {
        this.CUI = CUI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /*Metodo de impresion o salida */
    
    @Override
    public String toString() {
        return apellido+", "+nombre+
            "\nCUI: "+CUI;
    }
    
}
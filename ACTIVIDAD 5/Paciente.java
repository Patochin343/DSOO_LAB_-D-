public class Paciente {

    /* Atributos del paciente */
    private String nombre;
    private int codigoPaciente;
    private int edad;
    private int codigoDocumento;

    /* Constructor */
    public Paciente(String nombre, int codigoPaciente, int edad, int codigoDocumento) {
        this.nombre = nombre;
        this.codigoPaciente = codigoPaciente;
        this.edad = edad;
        this.codigoDocumento = codigoDocumento;
    }

    /* Métodos getters y setters */
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCodigoPaciente() {
        return codigoPaciente;
    }
    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getCodigoDocumento() {
        return codigoDocumento;
    }
    public void setCodigoDocumento(int codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }
}

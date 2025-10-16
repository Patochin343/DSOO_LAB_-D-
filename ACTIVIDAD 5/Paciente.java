import java.util.*;
public class Paciente {
    private static ArrayList<Integer> codigosDocumento = new ArrayList<>();
    private static ArrayList<Integer> codigosId = new ArrayList<>();

    /* Atributos del paciente */
    private String nombre;
    private int codigoPaciente;
    private int edad;
    private int codigoDocumento;

    /* Constructor */
    private Paciente(String nombre, int codigoPaciente, int edad, int codigoDocumento) {
        this.nombre = nombre;
        this.codigoPaciente = codigoPaciente;
        this.edad = edad;
        this.codigoDocumento = codigoDocumento;
    }

    /* Constructor con verificacion*/
    public Paciente crearPaciente(String nombre, int codigoPaciente, int edad, int codigoDocumento){
        if(Paciente.codigosId.contains(codigoPaciente) || Paciente.codigosDocumento.contains(codigoDocumento))
            return null;
        else
            return new Paciente(nombre, codigoPaciente, edad, codigoDocumento);
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
        if(!Paciente.codigosId.contains(codigoPaciente)){
            Paciente.codigosId.remove(this.codigoPaciente);
            Paciente.codigosId.add(codigoPaciente);
        }
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
        if(!Paciente.codigosDocumento.contains(codigoDocumento)){
            Paciente.codigosDocumento.remove(this.codigoDocumento);
            Paciente.codigosDocumento.add(codigoDocumento);
        }
    }
}

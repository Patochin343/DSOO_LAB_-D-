import java.util.Date;
import java.util.List;

public class Titularidad {
    private Date fechaInicio;
    private String tipoTitular;
    private List<Relacion> relaciones;

    public Titularidad(Date fechaInicio, String tipoTitular){
        this.fechaInicio=fechaInicio;
        this.tipoTitular=tipoTitular; 
    }

    public void asignarTitular(Cliente cliente, Cuenta cuenta) {
        if (cliente.consultarCuenta()!=null) {
            throw new IllegalStateException("Este cliente ya tiene una cuenta!");
        }
        if (cuenta.getTitular()!=null){
            throw new IllegalStateException("Esta cuenta ya tiene un titular!");
        }
        cliente.agregarCuenta(cuenta);
        cuenta.setTitular(cliente);
        System.out.println("Titularidad asignada correctamente ("+tipoTitular+")");
    }

    public ArrayList<Cliente> obtenerTitulares(Cuenta cuenta) {
        ArrayList<Cliente> titulares=new ArrayList<>();
        if (cuenta.getTitular()!=null) {
            titulares.add(cuenta.getTitular());
        }
        return titulares; 
    }
}
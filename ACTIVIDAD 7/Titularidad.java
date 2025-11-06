import java.util.*;

public class Titularidad {
    private Date fechaInicio;
    private String tipoTitular;
    private Cliente cliente;
    private Cuenta cuenta;

    public Titularidad(Date fechaInicio, String tipoTitular) {
        this.fechaInicio = fechaInicio;
        this.tipoTitular = tipoTitular;
    }

    public void asignarTitular(Cliente cliente, Cuenta cuenta) {
        this.cliente = cliente;
        this.cuenta = cuenta;
        cliente.agregarCuenta(cuenta);
        System.out.println("Titularidad asignada correctamente (" + tipoTitular + ")");
    }

    // Getters
    public Cliente getCliente() { return cliente; }
    public Cuenta getCuenta() { return cuenta; }
    public Date getFechaInicio() { return fechaInicio; }
    public String getTipoTitular() { return tipoTitular; }
}
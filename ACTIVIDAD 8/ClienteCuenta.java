package Actividad6;

public class ClienteCuenta {
  private Cliente cliente;
  private Cuenta cuenta;
  private Persona creador;

  private ClienteCuenta(Persona creador,Cliente cliente, Cuenta cuenta) {
    this.cliente = cliente;
    this.cuenta = cuenta;
    this.creador=creador;
  }
  static ClienteCuenta crearClienteCuenta(Persona creador,Cliente cliente, Cuenta cuenta){
    ClienteCuenta clienteCuenta=new ClienteCuenta(creador,cliente,cuenta);
    cliente.getClienteCuentas().add(clienteCuenta);
    cuenta.getClienteCuentas().add(clienteCuenta);
    return clienteCuenta;
  }

  public Cuenta getCuenta() {
    return cuenta;
  }

  public Cliente getCliente() {
    return cliente;
  }
  public String mostrarClienteCuenta() {
    return cliente.getNombre()+" "+cliente.getApellido()+", registrad@ por "+creador.getNombre()+" "+creador.getApellido();
  }
}

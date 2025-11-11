package Actividad6;

import java.util.ArrayList;
import java.util.List;

public class Banco {
  private ArrayList<Empleado> empleados;
  private ArrayList<Cliente> clientes;
  private ArrayList<Administrador> administradores;
  private ArrayList<ClienteCuenta> clienteCuentas;
  private ArrayList<Cuenta> cuentas;
  private ArrayList<Transaccion> transacciones;

  public Banco() {
    empleados=new ArrayList<>();
    clientes=new ArrayList<>();
    administradores=new ArrayList<>();
    clienteCuentas=new ArrayList<>();
    cuentas=new ArrayList<>();
    transacciones=new ArrayList<>();
  }
  // Buscar la cuenta por número de cuenta, solo clientes
  public Cuenta buscarCuentaPorNumeroCuenta(ArrayList<ClienteCuenta> clienteCuentas, String numeroCuenta) {
    for (ClienteCuenta cc : clienteCuentas) {
      if (cc.getCuenta().getNumeroCuenta().equals(numeroCuenta)) {
        return cc.getCuenta();
      }
    }
    return null;
  }
  //  la cuenta del arr de cuentas, si existe la devolvemos si no retornamos null
  public Cuenta buscarCuentaPorNumeroCuenta2(ArrayList<Cuenta> cuentas, String numeroCuenta) {
    for (Cuenta cuenta : cuentas) {
      if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
        return cuenta;
      }
    }
    return null;
  }

  // Forma genérica de saber si existe una persona, si es así la devuelve, sino null
  private static <T extends Persona> T existePersonaPorDni(List<T> personas, String dni) {
    for (T persona : personas) {
      if (persona.getDni().equals(dni)) {
        return persona;
      }
    }
    return null;
  }
  // Forma de buscar persona por número de télefono
  private static <T extends Persona> T existePersonaPorNroTelefono(List<T> personas, String nroTelefono) {
    for (T persona : personas) {
      if (persona.getNroTelefono().equals(nroTelefono)) {
        return persona;
      }
    }
    return null;
  }
  // Forma de buscar persona por correo
  private static <T extends Persona> T existePersonaPorCorreo(List<T> personas, String correo) {
    for (T persona : personas) {
      if (persona.getCorreo().equals(correo)) {
        return persona;
      }
    }
    return null;
  }
  // Forma de buscar persona por dni nivel 3
  public Persona buscarPersonaPorDni3(String dni){
    Persona administradorBuscado= existePersonaPorDni(this.getAdministradores(),dni);
    if(administradorBuscado!=null){
      return administradorBuscado;
    }
    Persona empleadoBuscado= existePersonaPorDni(this.getEmpleados(),dni);
    if(empleadoBuscado!=null){
      return empleadoBuscado;
    }
    Persona clienteBuscado= existePersonaPorDni(this.getClientes(),dni);
    if(clienteBuscado!=null){
      return clienteBuscado;
    }
    return null;
  }
  // Forma de buscar persona por dni nivel 2
  public Persona buscarPersonaPorDni2(String dni){
    Persona empleadoBuscado= existePersonaPorDni(this.getEmpleados(),dni);
    if(empleadoBuscado!=null){
      return empleadoBuscado;
    }
    Persona clienteBuscado= existePersonaPorDni(this.getClientes(),dni);
    if(clienteBuscado!=null){
      return clienteBuscado;
    }
    return null;
  }
  // Forma de buscar cliente por dni
  public Cliente buscarClientePorDni1(String dni){
    Cliente clienteBuscado= existePersonaPorDni(this.getClientes(),dni);
    if(clienteBuscado!=null){
      return clienteBuscado;
    }
    return null;
  }
  // Forma de buscar persona por número de teléfono
  public Persona buscarPersonaPorNroTelefono(String nroTelefono){
    Persona administradorBuscado= existePersonaPorNroTelefono(this.getAdministradores(),nroTelefono);
    if(administradorBuscado!=null){
      return administradorBuscado;
    }
    Persona empleadoBuscado= existePersonaPorNroTelefono(this.getEmpleados(),nroTelefono);
    if(empleadoBuscado!=null){
      return empleadoBuscado;
    }
    Persona clienteBuscado= existePersonaPorNroTelefono(this.getClientes(),nroTelefono);
    if(clienteBuscado!=null){
      return clienteBuscado;
    }
    return null;
  }
  // Forma de buscar persona por correo
  public Persona buscarPersonaPorCorreo(String correo){
    Persona administradorBuscado= existePersonaPorCorreo(this.getAdministradores(),correo);
    if(administradorBuscado!=null){
      return administradorBuscado;
    }
    Persona empleadoBuscado= existePersonaPorCorreo(this.getEmpleados(),correo);
    if(empleadoBuscado!=null){
      return empleadoBuscado;
    }
    Persona clienteBuscado= existePersonaPorCorreo(this.getClientes(),correo);
    if(clienteBuscado!=null){
      return clienteBuscado;
    }
    return null;
  }

  public void validarNombre(String nombre) throws NombreInvalidoException {
    if(!nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$")){
      throw new NombreInvalidoException("Nombre inválido");
    }
  }

  public void validarApellido(String apellido) throws ApellidoInvalidoException{
    if(!apellido.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$")){
      throw new ApellidoInvalidoException("Apellido inválido");
    }
  }

  public void validarDni(String dni) throws DniInvalidoException, DniNoEncontradoException {
    if(!dni.matches("\\d{8}")){
      throw new DniInvalidoException("DNI inválido");
    }
    if(this.buscarPersonaPorDni3(dni)==null){
      throw new DniNoEncontradoException("DNI no encontrado");
    }
  }

  public void validarClienteDni(String dni) throws DniInvalidoException, DniNoEncontradoException {
    if(!dni.matches("\\d{8}")){
      throw new DniInvalidoException("DNI inválido");
    }
    if(this.buscarClientePorDni1(dni)==null){
      throw new DniNoEncontradoException("DNI no encontrado");
    }
  }

  public void ingresarDni(String dni) throws DniInvalidoException,DniYaRegistradoException{
    if(!dni.matches("\\d{8}")) {
      throw new DniInvalidoException("DNI inválido");
    }
    if(this.buscarPersonaPorDni3(dni)!=null){
      throw new DniYaRegistradoException("Ese DNI ya está registrado");
    }
  }

  public void validarDireccion(String direccion) throws DireccionInvalidaException {
    if(!direccion.matches("^[A-Za-z0-9ÁÉÍÓÚáéíóúÑñ ,.#/-]+$")){
      throw new DireccionInvalidaException("Dirección inválida");
    }
  }

  public void validarNroTelefono(String nroTelefono) throws NroTelefonoInvalidoException,NroTelefonoNoEncontradoException{
    if(!nroTelefono.matches("^9\\d{8}$")){
      throw new NroTelefonoInvalidoException("Número de télefono inválido");
    }
    if(this.buscarPersonaPorNroTelefono(nroTelefono)==null){
      throw new NroTelefonoNoEncontradoException("Número de télefono no encontrado");
    }
  }

  public void ingresarNroTelefono(String nroTelefono) throws NroTelefonoInvalidoException, NroTelefonoYaRegistradoException {
    if(!nroTelefono.matches("^9\\d{8}$")){
      throw new NroTelefonoInvalidoException("Número de télefono inválido");
    }
    if(this.buscarPersonaPorNroTelefono(nroTelefono)!=null){
      throw new NroTelefonoYaRegistradoException("Ese número de télefono ya está registrado");
    }
  }

  public void validarCorreo(String correo) throws CorreoInvalidoException,CorreoNoEncontradoException{
    if(!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$")){
      throw new CorreoInvalidoException("Correo inválido");
    }
    if(this.buscarPersonaPorCorreo(correo)==null){
      throw new CorreoNoEncontradoException("Correo no encontrado");
    }
  }

  public void ingresarCorreo(String correo) throws CorreoInvalidoException,CorreoYaRegistradoException{
    if(!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}(\\.[A-Za-z]{2,})?$")){
      throw new CorreoInvalidoException("Correo inválido");
    }
    if(this.buscarPersonaPorCorreo(correo)!=null){
      throw new CorreoYaRegistradoException("Ese correo ya está registrado");
    }
  }

  public void validarContrasena(String contrasena)throws ContrasenaInvalidaException{
    if(contrasena.length()<6){
      throw new ContrasenaInvalidaException("La constraseña debe tener al menos 6 dígitos");
    }
  }

  public void validarTipoCargo(String tipoCargo) throws TipoCargoInvalidoException {
    for(TipoCargo t:TipoCargo.values()){
      if( t.name().equalsIgnoreCase(tipoCargo)){
        return;
      }
    }
    throw new TipoCargoInvalidoException("Cargo inválido");
  }

  public void validarTipoCuenta(String tipoCuenta) throws TipoCuentaInvalidoException {
    for(TipoCuenta t:TipoCuenta.values()){
      if( t.name().equalsIgnoreCase(tipoCuenta)){
        return;
      }
    }
    throw new TipoCuentaInvalidoException("Tipo de cuenta inválida");
  }

  public void validarNroCuenta(String nroCuenta) throws NroDeCuentaInvalidoException, NroDeCuentaNoEncontradoException {
    if(!nroCuenta.matches("^\\d{6}$")){
      throw new NroDeCuentaInvalidoException("Número de cuenta inválido");
    }
    if(this.buscarCuentaPorNumeroCuenta(this.getClienteCuentas(),nroCuenta)==null){
      throw new NroDeCuentaNoEncontradoException("Número de cuenta no encontrado");
    }
  }

  public void validarNroCuentaDiferente(String nroCuenta,Cuenta cuenta) throws NroDeCuentaIgualesException {
    if(cuenta.getNumeroCuenta().equals(nroCuenta)){
      throw new NroDeCuentaIgualesException("No puedes transferir a tu propia cuenta");
    }
  }

  public void validarNroCuentaDeCliente(String nroCuenta,Cliente cliente) throws NroDeCuentaInvalidoException, NroDeCuentaNoEncontradoException {
    if(!nroCuenta.matches("^\\d{6}$")){
      throw new NroDeCuentaInvalidoException("Número de cuenta inválido");
    }
    if(this.buscarCuentaPorNumeroCuenta(cliente.getClienteCuentas(),nroCuenta)==null){
      throw new NroDeCuentaNoEncontradoException("Número de cuenta no encontrado");
    }
  }

  public void validarDiferenciaDeClientes(Cliente cliente1,Cliente cliente2) throws ClientesIgualesException{
    if(cliente1.getDni().equals(cliente2.getDni())){
      throw new ClientesIgualesException("No se puede añadir titularidad a usted mismo");
    }
  }

  public ArrayList<Empleado> getEmpleados() {
    return empleados;
  }

  public ArrayList<Cliente> getClientes() {
    return clientes;
  }

  public ArrayList<Administrador> getAdministradores() {
    return administradores;
  }

  public ArrayList<ClienteCuenta> getClienteCuentas() {
    return clienteCuentas;
  }

  public Empleado registrarEmpleado(Persona creador, String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, TipoCargo cargo,String contrasena){
    Empleado empleado=Empleado.crearEmpleado(creador,nombre,apellido,dni,direccion,nroTelefono,correo,cargo,contrasena);
    agregarEmpleado(empleado);
    return empleado;
  }

  public Cliente registrarCliente(Persona creador,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo,String contrasena){
    Cliente cliente=Cliente.crearCliente(creador,nombre,apellido,dni, direccion, nroTelefono,correo,contrasena);
    agregarCliente(cliente);
    return cliente;
  }

  public Cuenta registrarCuenta(Persona creador,Cliente cliente,TipoCuenta tipoCuenta){
    Cuenta cuenta=Cuenta.crearCuenta(creador,tipoCuenta);
    ClienteCuenta cc=ClienteCuenta.crearClienteCuenta(creador,cliente,cuenta);
    agregarClienteCuenta(cc);
    agregarCuenta(cuenta);
    return cuenta;
  }

  public Cuenta registrarCuenta(Persona creador,Cliente cliente,TipoCuenta tipoCuenta,double saldo){
    Cuenta cuenta=Cuenta.crearCuenta(creador,tipoCuenta,saldo);
    ClienteCuenta cc=ClienteCuenta.crearClienteCuenta(creador,cliente,cuenta);
    agregarClienteCuenta(cc);
    agregarCuenta(cuenta);
    return cuenta;
  }


  public ClienteCuenta vincularClienteACuenta(Persona creador,Cliente solicitante, Cliente nuevoTitular,Cuenta cuenta){
    if(cuenta!=null&&nuevoTitular.buscarCuenta(cuenta.getNumeroCuenta())==null){
      return ClienteCuenta.crearClienteCuenta(creador,nuevoTitular,cuenta);
    }
    return null;
  }

  public Transaccion registrarRetiro(Persona creador,Cuenta cuenta,double monto){
    Transaccion transaccion=cuenta.retirar(creador,monto);
    this.anadirTransaccion(transaccion);
    return transaccion;
  }
  public Transaccion registrarDeposito(Persona creador,Cuenta cuenta,double monto){
    Transaccion transaccion=cuenta.deposito(creador,monto);
    this.anadirTransaccion(transaccion);
    return transaccion;
  }

  public Transaccion registrarTransferencia(Persona creador,Cuenta cuenta1,double monto,Cuenta cuenta2){
    Transaccion transaccion=cuenta1.transferencia(creador,monto,cuenta2);
    this.anadirTransaccion(transaccion);
    return transaccion;
  }

  public void agregarAdmin(Administrador admin){
    administradores.add(admin);
  }

  public void agregarEmpleado(Empleado empleado){
    empleados.add(empleado);
  }

  public void agregarCliente(Cliente cliente){
    clientes.add(cliente);
  }

  public void agregarClienteCuenta(ClienteCuenta cc){
    clienteCuentas.add(cc);
  }

  public void agregarCuenta(Cuenta cuenta){
    cuentas.add(cuenta);
  }

  public void anadirTransaccion(Transaccion transaccion){
    this.transacciones.add(transaccion);
  }

  public void mostrarAdmins(){
    administradores.forEach(a-> System.out.println(a.mostrarAdmin()));
  }

  public void mostrarEmpleados(){
    empleados.forEach(e-> System.out.println(e.mostrarEmpleado()));
  }

  public void mostrarClientes(){
    clientes.forEach(c-> System.out.println(c.mostrarCliente()));
  }

  public void mostrarCuentas(){
    cuentas.forEach(cuenta-> System.out.println(cuenta.mostrarCuenta()));
  }
}

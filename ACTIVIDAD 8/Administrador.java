package Actividad6;

import Actividad6.exceptions.ClientesIgualesException;

import java.util.Scanner;

public class Administrador extends Persona implements SubmenuPersona {

  private Administrador creador;

  private Administrador(Administrador creador,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, String contrasena) {
    super(nombre, apellido, dni, direccion, nroTelefono, correo, contrasena);
    this.creador = creador;
  }

  // El super admin
  static Administrador registrarSuperAdmin(Banco banco,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, String contrasena){
    Administrador admin=new Administrador(null, nombre, apellido, dni, direccion, nroTelefono, correo, contrasena);
    banco.agregarAdmin(admin);
    return admin;
  }

  // Constructor controlado
  private static Administrador crearAdmin(Administrador creador,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, String contrasena){
    return new Administrador(creador,nombre,apellido,dni,direccion,nroTelefono,correo,contrasena);
  }

  public Administrador registrarAdmin(Banco banco,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo,String contrasena){
    Administrador admin=Administrador.crearAdmin(this,nombre,apellido,dni,direccion,nroTelefono,correo,contrasena);
    banco.agregarAdmin(admin);
    return admin;
  }

  @Override
  public void submenu(Banco banco, Menu menu,Persona persona) {
    Scanner teclado=new Scanner(System.in);
    Administrador administrador=(Administrador)persona;
    int opcion;
    do{
      System.out.println("-------------------");
      System.out.println("Elija una de las siguientes opciones:\n" +
          "\t1) Registrar administrador\n" +
          "\t2) Registrar empleado\n" +
          "\t3) Registrar cliente\n" +
          "\t4) Registrar cuenta\n" +
          "\t5) Añadir titular a una cuenta\n" +
          "\t6) Buscar cuenta\n" +
          "\t7) Buscar persona\n" +
          "\t8) Mostrar todas las cuentas\n" +
          "\t9) Mostrar todos los usuarios\n" +
          "\t10) Gestionar una cuenta\n" +
          "\t11) Salir");
      opcion=teclado.nextInt();
      switch (opcion) {
        case 1:
          System.out.println("---------Registrando administrador---------");
          System.out.println(registrarAdmin(banco,menu.pedirNombre(),menu.pedirApellido(),menu.pedirDni("Ingrese su DNI: "),menu.pedirDireccion(),menu.pedirNroTelefono(),menu.pedirCorreo(),menu.pedirContrasena()).mostrarAdmin());
          break;
        case 2:
          System.out.println("---------Registrando empleado---------");
          System.out.println(registrarEmpleado(banco,menu.pedirNombre(),menu.pedirApellido(),menu.pedirDni("Ingrese su DNI: "),menu.pedirDireccion(),menu.pedirNroTelefono(),menu.pedirCorreo(),menu.pedirTipoCargo(),menu.pedirContrasena()).mostrarEmpleado());
          break;
        case 3:
          System.out.println("---------Registrando cliente---------");
          System.out.println(registrarCliente(banco,menu.pedirNombre(),menu.pedirApellido(),menu.pedirDni("Ingrese su DNI: "),menu.pedirDireccion(),menu.pedirNroTelefono(),menu.pedirCorreo(),menu.pedirContrasena()).mostrarCliente());
          break;
        case 4:
          Cliente cliente=banco.buscarClientePorDni1(menu.pedirClientePorDni("Ingrese el DNI del cliente: "));
          System.out.println(administrador.registrarCuenta(banco,cliente,menu.pedirTipoCuenta()).mostrarCuenta());
          break;
        case 5:
          Cliente cliente1=banco.buscarClientePorDni1(menu.pedirClientePorDni("Ingrese el DNI del solicitante: "));
          Cliente cliente2;
          boolean esValido=false;
          do{
            cliente2=banco.buscarClientePorDni1(menu.pedirClientePorDni("Ingrese el DNI del nuevo titular: "));
            try{
              banco.validarDiferenciaDeClientes(cliente1,cliente2);
              esValido=true;
            }catch (ClientesIgualesException e){
              System.out.println(e.getMessage());
            }
          }while(!esValido);
          Cuenta cuenta=cliente1.buscarCuenta(menu.pedirNroDeCuentaDeCliente(cliente1));
          administrador.vincularClienteACuenta(banco,cliente1,cliente2,cuenta);
          System.out.println(cuenta.mostrarCuenta());
          break;
        case 6:
          System.out.println(banco.buscarCuentaPorNumeroCuenta(banco.getClienteCuentas(),menu.pedirNroDeCuenta()).mostrarCuenta());
          break;
        case 7:
          Persona p = banco.buscarPersonaPorDni3(menu.buscarDni("Ingrese su DNI: "));
          if (p instanceof Administrador) {
            Administrador admin = (Administrador) p;
            System.out.println(admin.mostrarAdmin());
          } else if (p instanceof Empleado) {
            Empleado emp = (Empleado) p;
            System.out.println(emp.mostrarEmpleado());
          } else if (p instanceof Cliente) {
            Cliente cli = (Cliente) p;
            System.out.println(cli.mostrarCliente());
          }
          break;
        case 8:
          banco.mostrarCuentas();
          break;
        case 9:
          System.out.println("-------------------");
          System.out.println("Administradores");
          banco.mostrarAdmins();
          System.out.println("-------------------");
          System.out.println("Empleados");
          banco.mostrarEmpleados();
          System.out.println("-------------------");
          System.out.println("Clientes");
          banco.mostrarClientes();
          break;
        case 10:
          Cliente c=menu.retornarClientePorDni("Ingrese el DNI del cliente: ");
          Cuenta cuenta2=banco.buscarCuentaPorNumeroCuenta(c.getClienteCuentas(),menu.pedirNroDeCuentaDeCliente(c));
          cuenta2.submenu(banco,menu,administrador,cuenta2);
          break;
        case 11:
          break;
        default:
          System.out.println("El número es inválido (1-11)");
      }
    }while(opcion!=11);
  }

  public Empleado registrarEmpleado(Banco banco, String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, TipoCargo cargo, String contrasena){
   return banco.registrarEmpleado(this,nombre,apellido,dni,direccion,nroTelefono,correo,cargo,contrasena);
  }

  public Cliente registrarCliente(Banco banco,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo,String contrasena){
    return banco.registrarCliente(this,nombre,apellido,dni,direccion,nroTelefono,correo,contrasena);
  }

  public Cuenta registrarCuenta(Banco banco,Cliente cliente,TipoCuenta tipoCuenta){
    return banco.registrarCuenta(this,cliente,tipoCuenta);
  }

  public Cuenta registrarCuenta(Banco banco,Cliente cliente,TipoCuenta tipoCuenta,double saldo){
    return banco.registrarCuenta(this,cliente,tipoCuenta,saldo);
  }

  public ClienteCuenta vincularClienteACuenta(Banco banco,Cliente solicitante, Cliente nuevoTitular, Cuenta cuenta){
    return banco.vincularClienteACuenta(this,solicitante,nuevoTitular,cuenta);
  }

  public Administrador getCreador() {
    return creador;
  }

  public String mostrarAdmin(){
    String infoCreador=(creador==null) ? "SuperAdmin (sin creador)" : creador.getNombre()+" "+creador.getApellido();
    return super.mostrarPersona()+"Registrado por: "+infoCreador;
  }


}

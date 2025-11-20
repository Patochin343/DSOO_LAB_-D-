package Actividad6;

import Actividad6.exceptions.ClientesIgualesException;

import java.util.Scanner;

public class Empleado extends Persona implements SubmenuPersona {

  private TipoCargo cargo;
  private Persona creador;

  private Empleado(Persona creador,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, TipoCargo cargo,String contrasena) {
    super(nombre, apellido, dni, direccion, nroTelefono, correo,contrasena);
    this.cargo = cargo;
    this.creador = creador;
  }
  // Constructor controlado
  static Empleado crearEmpleado(Persona creador, String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, TipoCargo cargo,String contrasena){
    return new Empleado(creador,nombre,apellido,dni,direccion,nroTelefono,correo,cargo,contrasena);
  }

  @Override
  public void submenu(Banco banco, Menu menu,Persona persona) {
    Scanner teclado=new Scanner(System.in);
    Empleado empleado=(Empleado) persona;
    int opcion;
    do{
      System.out.println("-------------------");
      System.out.println("Elija una de las siguientes opciones:\n" +
          "\t1) Registrar cliente\n" +
          "\t2) Registrar cuenta\n" +
          "\t3) Añadir titular a una cuenta\n" +
          "\t4) Buscar cuenta\n" +
          "\t5) Buscar persona\n" +
          "\t6) Mostrar todas las cuentas\n" +
          "\t7) Mostrar todos los usuarios\n" +
          "\t8) Gestionar una cuenta\n" +
          "\t9) Salir");
      opcion=teclado.nextInt();
      switch (opcion) {
        case 1:
          System.out.println("---------Registrando cliente---------");
          System.out.println(registrarCliente(banco,menu.pedirNombre(),menu.pedirApellido(),menu.pedirDni("Ingrese su DNI: "),menu.pedirDireccion(),menu.pedirNroTelefono(),menu.pedirCorreo(),menu.pedirContrasena()).mostrarCliente());
          break;
        case 2:
          Cliente cliente=banco.buscarClientePorDni1(menu.pedirClientePorDni("Ingrese el DNI del cliente: "));
          System.out.println(empleado.registrarCuenta(banco,cliente,menu.pedirTipoCuenta()).mostrarCuenta());
          break;
        case 3:
          Cliente cliente1=(Cliente)banco.buscarClientePorDni1(menu.pedirClientePorDni("Ingrese el DNI del solicitante: "));
          Cliente cliente2;
          boolean esValido=false;
          do{
            cliente2=(Cliente)banco.buscarClientePorDni1(menu.pedirClientePorDni("Ingrese el DNI del nuevo titular: "));
            try{
              banco.validarDiferenciaDeClientes(cliente1,cliente2);
              esValido=true;
            }catch (ClientesIgualesException e){
              System.out.println(e.getMessage());
            }
          }while(!esValido);
          Cuenta cuenta=cliente1.buscarCuenta(menu.pedirNroDeCuentaDeCliente(cliente1));
          empleado.vincularClienteACuenta(banco,cliente1,cliente2,cuenta);
          System.out.println(cuenta.mostrarCuenta());
          break;
        case 4:
          System.out.println(banco.buscarCuentaPorNumeroCuenta(banco.getClienteCuentas(),menu.pedirNroDeCuenta()).mostrarCuenta());
          break;
        case 5:
          Persona p = banco.buscarPersonaPorDni2(menu.buscarDni("Ingrese su DNI: "));
          if (p instanceof Empleado) {
            Empleado emp = (Empleado) p;
            System.out.println(emp.mostrarEmpleado());
          } else if (p instanceof Cliente) {
            Cliente cli = (Cliente) p;
            System.out.println(cli.mostrarCliente());
          }
          break;
        case 6:
          banco.mostrarCuentas();
          break;
        case 7:
          System.out.println("-------------------");
          System.out.println("Empleados");
          banco.mostrarEmpleados();
          System.out.println("-------------------");
          System.out.println("Clientes");
          banco.mostrarClientes();
          break;
        case 8:
          Cliente c=menu.retornarClientePorDni("Ingrese su DNI: ");
          Cuenta cuenta2=banco.buscarCuentaPorNumeroCuenta(c.getClienteCuentas(),menu.pedirNroDeCuentaDeCliente(c));
          cuenta2.submenu(banco,menu,empleado,cuenta2);
          break;
        case 9:
          break;
        default:
          System.out.println("El número es inválido (1-9)");
      }
    }while(opcion!=9);
  }

  public Cliente registrarCliente(Banco banco, String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo, String contrasena){
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

  public String mostrarEmpleado() {
    String infoCreador=(creador==null) ? "SuperAdmin (sin creador)" : creador.getNombre()+" "+creador.getApellido();
    return super.mostrarPersona()+"Cargo: "+cargo+"\nRegistrado por: "+ infoCreador;
  }

}

package Actividad6;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Persona implements SubmenuPersona {

  private Persona creador;
  private ArrayList<ClienteCuenta> clienteCuentas;

  private Cliente(Persona creador,String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo,String contrasena) {
    super(nombre, apellido, dni, direccion, nroTelefono, correo,contrasena);
    this.creador=creador;
    this.clienteCuentas = new ArrayList<>();
  }

  static Cliente crearCliente(Persona creador, String nombre, String apellido, String dni, String direccion, String nroTelefono, String correo,String contrasena){
    return new Cliente(creador,nombre,apellido, dni, direccion, nroTelefono, correo,contrasena);
  }

  @Override
  public void submenu(Banco banco, Menu menu,Persona persona) {
    Scanner teclado=new Scanner(System.in);
    Cliente cliente=(Cliente) persona;
    int opcion;
    do{
      System.out.println("-------------------");
      System.out.println("Elija una de las siguientes opciones:\n" +
          "\t1) Buscar cuenta\n" +
          "\t2) Mostrar mis cuentas\n" +
          "\t3) Crear cuenta\n" +
          "\t4) Gestionar mis cuentas\n" +
          "\t5) Salir");
      opcion=teclado.nextInt();
      switch (opcion) {
        case 1:
          System.out.println(banco.buscarCuentaPorNumeroCuenta(cliente.getClienteCuentas(),menu.pedirNroDeCuentaDeCliente(cliente)).mostrarCuenta());
          break;
        case 2:
          cliente.mostrarCuentas();
          break;
        case 3:
          System.out.println(cliente.registrarCuenta(banco,menu.pedirTipoCuenta()).mostrarCuenta());
          break;
        case 4:
          Cuenta cuenta=cliente.buscarCuenta(menu.pedirNroDeCuentaDeCliente(cliente));
          cuenta.submenu(banco,menu,cliente,cuenta);
          break;
        case 5:
          break;
        default:
          System.out.println("El número es inválido (1-5)");
      }
    }while(opcion!=5);

  }

  public Cuenta registrarCuenta(Banco banco, TipoCuenta tipoCuenta){
    return banco.registrarCuenta(this,this,tipoCuenta);
  }

  public void mostrarCuentas(){
    for(ClienteCuenta cc:clienteCuentas){
      System.out.println(cc.getCuenta().mostrarCuenta());
    }
  }

  public Cuenta buscarCuenta(String nroCuenta){
    Cuenta cuentaBuscada;
    for (ClienteCuenta clienteCuenta : clienteCuentas) {
      cuentaBuscada=clienteCuenta.getCuenta();
      if(cuentaBuscada.getNumeroCuenta().equals(nroCuenta)){
        return cuentaBuscada;
      }
    }
    return null;
  }

  public String mostrarCliente() {
    String infoCreador=(creador==null) ? "SuperAdmin (sin creador)" : creador.getNombre()+" "+creador.getApellido();
    return super.mostrarPersona()+"Registrado por: "+ infoCreador;
  }

  public ArrayList<ClienteCuenta> getClienteCuentas() {
    return clienteCuentas;
  }
}

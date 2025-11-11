package Actividad6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
  Banco banco;

  public Menu(Banco banco) {
    this.banco = banco;
  }

  public Persona login(){
    Scanner teclado = new Scanner(System.in);
    Persona persona = null;
    boolean dniValido=false;
    do {
      System.out.print("DNI: ");
      String dni = teclado.next();
      try {
        banco.validarDni(dni);
        persona = banco.buscarPersonaPorDni3(dni);
        dniValido = true;
      } catch (DniInvalidoException | DniNoEncontradoException e) {
        System.out.println(e.getMessage());
      }
    } while (!dniValido);
    teclado.nextLine();
    boolean contrasenaCorrecta=false;
    do {
      System.out.print("Contraseña: ");
      String contrasena = teclado.nextLine();
      if (contrasena.equals(persona.getContrasena())) {
        contrasenaCorrecta = true;
      } else {
        System.out.println("Contraseña incorrecta, intenta nuevamente.");
      }
    } while (!contrasenaCorrecta);
    return persona;
  }

  public String pedirNombre(){
    Scanner teclado=new Scanner(System.in);
    String nombre;
    boolean nombreValido=false;
    do{
      System.out.print("Ingrese su primer nombre (ejemplo: Luis): ");
      nombre=teclado.next();
      try{
        banco.validarNombre(nombre);
        nombreValido=true;
      }catch (NombreInvalidoException e){
        System.out.println(e.getMessage());
      }
    }while(!nombreValido);
    return nombre;
  }

  public String pedirApellido(){
    Scanner teclado=new Scanner(System.in);
    String apellido;
    boolean apellidoValido=false;
    do{
      System.out.print("Ingrese su primer apellido (ejemplo: García): ");
      apellido=teclado.next();
      try{
        banco.validarApellido(apellido);
        apellidoValido=true;
      }catch (ApellidoInvalidoException e){
        System.out.println(e.getMessage());
      }
    }while(!apellidoValido);
    return apellido;
  }

  public String pedirDni(String mensaje){
    Scanner teclado=new Scanner(System.in);
    String dni;
    boolean dniValido=false;
    do{
      System.out.print(mensaje);
      dni=teclado.next();
      try{
        banco.ingresarDni(dni);
        dniValido=true;
      }catch (DniInvalidoException|DniYaRegistradoException e){
        System.out.println(e.getMessage());
      }
    }while(!dniValido);
    return dni;
  }

  public String buscarDni(String mensaje){
    Scanner teclado=new Scanner(System.in);
    String dni;
    boolean dniValido=false;
    do{
      System.out.print(mensaje);
      dni=teclado.next();
      try{
        banco.validarDni(dni);
        dniValido=true;
      }catch (DniInvalidoException|DniNoEncontradoException e){
        System.out.println(e.getMessage());
      }
    }while(!dniValido);
    return dni;
  }

  public String pedirClientePorDni(String mensaje){
    Scanner teclado=new Scanner(System.in);
    String dni;
    boolean dniValido=false;
    do{
      System.out.print(mensaje);
      dni=teclado.next();
      try{
        banco.validarClienteDni(dni);
        dniValido=true;
      }catch (DniInvalidoException|DniNoEncontradoException e){
        System.out.println(e.getMessage());
      }
    }while(!dniValido);
    return dni;
  }

  public String pedirDireccion(){
    Scanner teclado=new Scanner(System.in);
    String direccion;
    boolean direccionValida=false;
    do{
      System.out.print("Ingrese su dirección: ");
      direccion=teclado.next();
      try{
        banco.validarDireccion(direccion);
        direccionValida=true;
      }catch (DireccionInvalidaException e){
        System.out.println(e.getMessage());
      }
    }while(!direccionValida);
    return direccion;
  }

  public String pedirNroTelefono(){
    Scanner teclado=new Scanner(System.in);
    String nroTelefono;
    boolean nroTelefonoValido=false;
    do{
      System.out.print("Ingrese su número de teléfono: ");
      nroTelefono=teclado.next();
      try{
        banco.ingresarNroTelefono(nroTelefono);
        nroTelefonoValido=true;
      }catch (NroTelefonoInvalidoException | NroTelefonoYaRegistradoException e){
        System.out.println(e.getMessage());
      }
    }while(!nroTelefonoValido);
    return nroTelefono;
  }

  public String pedirCorreo(){
    Scanner teclado=new Scanner(System.in);
    String correo;
    boolean correoValido=false;
    do{
      System.out.print("Ingrese su correo: ");
      correo=teclado.next();
      try{
        banco.ingresarCorreo(correo);
        correoValido=true;
      }catch (CorreoInvalidoException|CorreoYaRegistradoException e){
        System.out.println(e.getMessage());
      }
    }while(!correoValido);
    return correo;
  }

  public String pedirContrasena(){
    Scanner teclado=new Scanner(System.in);
    String contrasena;
    boolean contrasenaValida=false;
    do{
      System.out.print("Ingrese su contraseña: ");
      contrasena=teclado.next();
      try{
        banco.validarContrasena(contrasena);
        contrasenaValida=true;
      }catch (ContrasenaInvalidaException e){
        System.out.println(e.getMessage());
      }
    }while(!contrasenaValida);
    return contrasena;
  }

  public TipoCargo pedirTipoCargo() {
    Scanner teclado = new Scanner(System.in);
    TipoCargo tipoCargo = null;
    boolean cargoValido = false;
    do {
      System.out.print("Ingrese su tipo de cargo (");
      TipoCargo[] cargos = TipoCargo.values();
      for (int i = 0; i < cargos.length; i++) {
        System.out.print(cargos[i].name());
        if (i < cargos.length - 1) {
          System.out.print(", ");
        }
      }
      System.out.print("): ");

      String input = teclado.next();
      try {
        banco.validarTipoCargo(input);
        tipoCargo = TipoCargo.valueOf(input.toUpperCase());
        cargoValido = true;
      } catch (TipoCargoInvalidoException e) {
        System.out.println(e.getMessage());
      }
    } while (!cargoValido);
    return tipoCargo;
  }

  public TipoCuenta pedirTipoCuenta() {
    Scanner teclado = new Scanner(System.in);
    TipoCuenta tipoCargo = null;
    boolean cuentaValida = false;
    do {
      System.out.print("Ingrese su tipo de cuenta (");
      TipoCuenta[] tipo = TipoCuenta.values();
      for (int i = 0; i < tipo.length; i++) {
        System.out.print(tipo[i].name());
        if (i < tipo.length - 1) {
          System.out.print(", ");
        }
      }
      System.out.print("): ");

      String input = teclado.next();
      try {
        banco.validarTipoCuenta(input);
        tipoCargo = TipoCuenta.valueOf(input.toUpperCase());
        cuentaValida = true;
      } catch (TipoCuentaInvalidoException e) {
        System.out.println(e.getMessage());
      }
    } while (!cuentaValida);
    return tipoCargo;
  }

  public String pedirNroDeCuenta(){
    Scanner teclado=new Scanner(System.in);
    String nroDeCuenta;
    boolean nroDeCuentaValido=false;
    do{
      System.out.print("Ingrese su número de cuenta: ");
      nroDeCuenta=teclado.next();
      try{
        banco.validarNroCuenta(nroDeCuenta);
        nroDeCuentaValido=true;
      }catch (NroDeCuentaNoEncontradoException|NroDeCuentaInvalidoException e){
        System.out.println(e.getMessage());
      }
    }while(!nroDeCuentaValido);
    return nroDeCuenta;
  }

  public String pedirNroDeCuentaDeCliente(Cliente cliente){
    Scanner teclado=new Scanner(System.in);
    String nroDeCuenta;
    boolean nroDeCuentaValido=false;
    do{
      System.out.print("Ingrese su número de cuenta: ");
      nroDeCuenta=teclado.next();
      try{
        banco.validarNroCuentaDeCliente(nroDeCuenta,cliente);
        nroDeCuentaValido=true;
      }catch (NroDeCuentaNoEncontradoException|NroDeCuentaInvalidoException e){
        System.out.println(e.getMessage());
      }
    }while(!nroDeCuentaValido);
    return nroDeCuenta;
  }

  public String pedirNroDeCuentaDiferente(Cuenta cuenta){
    Scanner teclado=new Scanner(System.in);
    String nroDeCuenta;
    boolean nroDeCuentaValido=false;
    do{
      System.out.print("Ingrese el número de cuenta de destino: ");
      nroDeCuenta=teclado.next();
      try{
        banco.validarNroCuentaDiferente(nroDeCuenta,cuenta);
        banco.validarNroCuenta(nroDeCuenta);
        nroDeCuentaValido=true;
      }catch (NroDeCuentaIgualesException|NroDeCuentaInvalidoException|NroDeCuentaNoEncontradoException e){
        System.out.println(e.getMessage());
      }
    }while(!nroDeCuentaValido);
    return nroDeCuenta;
  }

  public double pedirMontoRetiro(Cuenta cuenta){
    Scanner teclado=new Scanner(System.in);
    double monto=0;
    boolean montoValido=false;
    do{
      System.out.print("Ingrese el monto: ");
      try {
        monto=teclado.nextDouble();
        cuenta.validarMontoRetiro(monto);
        montoValido = true;
      }catch(InputMismatchException e){
        System.out.println("Debe ingresar un número válido");
        teclado.next();
      }catch (MontoInvalidoException|SaldoInsuficienteException e){
        System.out.println(e.getMessage());
      }
    }while(!montoValido);
    return monto;
  }

  public double pedirMontoDeposito(Cuenta cuenta){
    Scanner teclado=new Scanner(System.in);
    double monto=0;
    boolean montoValido=false;
    do{
      System.out.print("Ingrese el monto: ");
      try {
      monto=teclado.nextDouble();
        cuenta.validarMontoDeposito(monto);
        montoValido = true;
      }catch(InputMismatchException e){
        System.out.println("Debe ingresar un número válido");
        teclado.next();
      }catch (MontoInvalidoException e){
        System.out.println(e.getMessage());
      }
    }while(!montoValido);
    return monto;
  }











}

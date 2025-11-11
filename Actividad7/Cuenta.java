package Actividad6;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Cuenta implements SubmenuCuenta {
  private TipoCuenta tipoCuenta;
  private double saldo;
  private String numeroCuenta;
  private LocalDateTime fechaApertura;
  private ArrayList<Transaccion> transacciones;
  private ArrayList<ClienteCuenta> clienteCuentas;
  private Persona creador;
  private static int count=1;

  private String generarNumeroCuenta(){
    return String.format("%06d",count++);
  }
  private Cuenta(Persona creador,TipoCuenta tipoCuenta, double saldo) {
    this.tipoCuenta = tipoCuenta;
    this.saldo = saldo;
    this.numeroCuenta = generarNumeroCuenta();
    this.fechaApertura = LocalDateTime.now();
    this.transacciones = new ArrayList<>();
    this.clienteCuentas = new ArrayList<>();
    this.creador=creador;
  }
  private Cuenta(Persona creador,TipoCuenta tipoCuenta) {
    this.tipoCuenta = tipoCuenta;
    this.saldo = 0.0;
    this.numeroCuenta = generarNumeroCuenta();
    this.fechaApertura = LocalDateTime.now();
    this.transacciones = new ArrayList<>();
    this.clienteCuentas = new ArrayList<>();
    this.creador=creador;
  }

  static Cuenta crearCuenta(Persona creador,TipoCuenta tipoCuenta){
    return new Cuenta(creador,tipoCuenta);
  }

  static Cuenta crearCuenta(Persona creador,TipoCuenta tipoCuenta, double saldo){
    return new Cuenta(creador,tipoCuenta,saldo);
  }

  @Override
  public void submenu(Banco banco, Menu menu, Persona persona,Cuenta cuenta) {
    Scanner teclado=new Scanner(System.in);
    Cliente cliente=(Cliente) persona;
    int opcion;
    do{
      System.out.println("-------------------");
      System.out.println("Elija una de las siguientes opciones:\n" +
          "\t1) Retirar\n" +
          "\t2) Depositar\n" +
          "\t3) Transferencia\n" +
          "\t4) Mostrar movimientos\n" +
          "\t5) Mostrar cuenta\n" +
          "\t6) Volver");
      opcion=teclado.nextInt();
      switch (opcion) {
        case 1:
          System.out.println(banco.registrarRetiro(cliente,cuenta,menu.pedirMontoRetiro(cuenta)).mostrarTransaccion());
          break;
        case 2:
          System.out.println(banco.registrarDeposito(cliente,cuenta,menu.pedirMontoDeposito(cuenta)).mostrarTransaccion());
          break;
        case 3:
          System.out.println(banco.registrarTransferencia(cliente,cuenta, menu.pedirMontoRetiro(cuenta), banco.buscarCuentaPorNumeroCuenta(banco.getClienteCuentas(),menu.pedirNroDeCuentaDiferente(cuenta))).mostrarTransaccion());
          break;
        case 4:
          cuenta.mostrarMovimientos();
          break;
        case 5:
          System.out.println(cuenta.mostrarCuenta());
          break;
        case 6:
          break;
        default:
          System.out.println("El número es inválido (1-6)");
      }
    }while(opcion!=6);
  }

  public void validarMontoRetiro(double monto)throws MontoInvalidoException,SaldoInsuficienteException{
    if(monto<=0){
      throw new MontoInvalidoException("El monto debe ser mayor que 0");
    }
    if(monto>saldo){
      throw new SaldoInsuficienteException("Saldo insuficiente");
    }
  }

  public void validarMontoDeposito(double monto)throws MontoInvalidoException{
    if(monto<=0){
      throw new MontoInvalidoException("El monto debe ser mayor que 0");
    }
  }

  public Transaccion retirar(Persona creador, double monto){
    saldo -= monto;
    Transaccion transaccion=Transaccion.crearTransaccion(creador,TipoTransaccion.RETIRO,monto);
    anadirTransaccion(transaccion);
    return transaccion;
  }

  public Transaccion deposito(Persona creador,double monto){
    saldo += monto;
    Transaccion transaccion=Transaccion.crearTransaccion(creador,TipoTransaccion.DEPOSITO,monto);
    anadirTransaccion(transaccion);
    return transaccion;
  }

  public Transaccion transferencia(Persona creador,double monto,Cuenta cuenta){
    saldo -= monto;
    Transaccion transaccion=Transaccion.crearTransaccion(creador,TipoTransaccion.TRANSFERENCIA,monto);
    anadirTransaccion(transaccion);
    cuenta.setSaldo(cuenta.getSaldo()+monto);
    cuenta.anadirTransaccion(transaccion);
    return transaccion;
  }

  private void anadirTransaccion(Transaccion transaccion){
    this.transacciones.add(transaccion);
  }

  public void mostrarMovimientos(){
    System.out.println("Movimientos de la cuenta "+numeroCuenta+": ");
    for(Transaccion transaccion:transacciones){
      System.out.println(transaccion.mostrarTransaccion());
    }
  }

  public ArrayList<String> getTitulares(){
    ArrayList<String> titulares=new ArrayList<>();
    for(ClienteCuenta clienteCuenta: clienteCuentas){
      Cliente cliente=clienteCuenta.getCliente();
      titulares.add(cliente.getNombre()+" "+cliente.getApellido());
    }
    return titulares;
  }



  public String getNumeroCuenta() {
    return numeroCuenta;
  }
  public ArrayList<ClienteCuenta> getClienteCuentas() {
    return clienteCuentas;
  }
  public double getSaldo() {
    return saldo;
  }
  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public String mostrarCuenta() {
    StringBuilder sb = new StringBuilder();
    sb.append("-------------------\n")
        .append("Titulares: ").append(String.join(", ", getTitulares())).append("\n")
        .append("Tipo de cuenta: ").append(tipoCuenta).append("\n")
        .append("Saldo: S/.").append(saldo).append("\n")
        .append("Número de Cuenta: ").append(numeroCuenta).append("\n")
        .append("Fecha de apertura: ").append(fechaApertura).append("\n");

    for (ClienteCuenta cc : getClienteCuentas()) {
      sb.append(cc.mostrarClienteCuenta()).append("\n");
    }
    return sb.toString();
  }

}

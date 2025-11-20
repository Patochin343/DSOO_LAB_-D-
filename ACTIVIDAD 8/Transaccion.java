package Actividad6;

import java.time.LocalDateTime;

public class Transaccion {
  private String numeroTransaccion;
  private LocalDateTime fechaTransaccion;
  private TipoTransaccion tipoTransaccion;
  private double monto;
  private static int count=1;
  private Persona creador;

  private Transaccion(Persona creador,TipoTransaccion tipoTransaccion, double monto) {
    this.numeroTransaccion = generarNumeroTransaccion();
    this.fechaTransaccion = LocalDateTime.now();
    this.tipoTransaccion = tipoTransaccion;
    this.monto = monto;
    this.creador=creador;
  }
  public static Transaccion crearTransaccion(Persona creador,TipoTransaccion tipo, double monto) {
    return new Transaccion(creador,tipo, monto);
  }
  private String generarNumeroTransaccion(){
    return "Transaccion-"+count++;
  }


  public String mostrarTransaccion() {
    String infoCreador=(creador==null) ? "SuperAdmin (sin creador)" : creador.getNombre()+" "+creador.getApellido();
    return "-------------------\n" +
        "Número de transacción: "+numeroTransaccion+"\n" +
        "Fecha: "+fechaTransaccion+"\n" +
        "Tipo: "+tipoTransaccion+"\n" +
        "Monto: "+monto+"\n" +
        "Registrador por: " + infoCreador;
  }
}


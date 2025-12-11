package com.banco.model;

import java.sql.Timestamp;

public class Transaccion {
  private int id;
  private int idCuenta;
  private String tipoTransaccion;
  private double monto;
  private Timestamp fecha;

  public Transaccion(int id, int idCuenta, String tipoTransaccion, double monto, Timestamp fecha) {
    this.id = id;
    this.idCuenta = idCuenta;
    this.tipoTransaccion = tipoTransaccion;
    this.monto = monto;
    this.fecha = fecha;
  }

  public Transaccion(int idCuenta, String tipoTransaccion, double monto) {
    this.idCuenta = idCuenta;
    this.tipoTransaccion = tipoTransaccion;
    this.monto = monto;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIdCuenta() {
    return idCuenta;
  }

  public void setIdCuenta(int idCuenta) {
    this.idCuenta = idCuenta;
  }

  public String getTipoTransaccion() {
    return tipoTransaccion;
  }

  public void setTipoTransaccion(String tipoTransaccion) {
    this.tipoTransaccion = tipoTransaccion;
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public Timestamp getFecha() {
    return fecha;
  }

  public void setFecha(Timestamp fecha) {
    this.fecha = fecha;
  }

  @Override
  public String toString() {
    return fecha + " - " + tipoTransaccion + ": $" + monto;
  }
}

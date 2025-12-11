package com.banco.model;

public class Cuenta {
  private int id;
  private String numeroCuenta;
  private double saldo;
  private String tipoCuenta;

  public Cuenta(int id, String numeroCuenta, double saldo, String tipoCuenta) {
    this.id = id;
    this.numeroCuenta = numeroCuenta;
    this.saldo = saldo;
    this.tipoCuenta = tipoCuenta;
  }

  public Cuenta(String numeroCuenta, double saldo, String tipoCuenta) {
    this.numeroCuenta = numeroCuenta;
    this.saldo = saldo;
    this.tipoCuenta = tipoCuenta;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNumeroCuenta() {
    return numeroCuenta;
  }

  public void setNumeroCuenta(String numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public String getTipoCuenta() {
    return tipoCuenta;
  }

  public void setTipoCuenta(String tipoCuenta) {
    this.tipoCuenta = tipoCuenta;
  }

  @Override
  public String toString() {
    return numeroCuenta + " - " + tipoCuenta + " ($" + saldo + ")";
  }
}

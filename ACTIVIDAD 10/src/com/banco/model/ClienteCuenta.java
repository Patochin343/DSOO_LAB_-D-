package com.banco.model;

public class ClienteCuenta {
  private int idCliente;
  private int idCuenta;

  public ClienteCuenta(int idCliente, int idCuenta) {
    this.idCliente = idCliente;
    this.idCuenta = idCuenta;
  }

  public int getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(int idCliente) {
    this.idCliente = idCliente;
  }

  public int getIdCuenta() {
    return idCuenta;
  }

  public void setIdCuenta(int idCuenta) {
    this.idCuenta = idCuenta;
  }
}

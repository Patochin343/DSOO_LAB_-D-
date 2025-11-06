import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Cuenta {
    private String numeroCuenta;
    private String tipoCuenta; 
    private double saldo;
    private Date fechaApertura;
    private List<Transaccion> transacciones;
    public Cuenta(String numeroCuenta, String tipoCuenta, double saldoInicial, Date fechaApertura) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldoInicial;
        this.fechaApertura = fechaApertura;
        this.transacciones = new ArrayList<>();
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito de " + monto + " realizado con éxito.");
        } else {
            System.out.println("El monto del depósito debe ser positivo.");
        }
    }
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            System.out.println("Retiro de " + monto + " realizado con éxito.");
        } else {
            System.out.println("Monto inválido o saldo insuficiente.");
        }
    }
    public double consultarSaldo() {
        return saldo;
    }
    public void registrarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }
    public void mostrarMovimientos() {
        System.out.println("Movimientos de la cuenta " + numeroCuenta + ":");
        for (Transaccion t : transacciones) {
            System.out.println("- " + t);
        }
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public String getTipoCuenta() {
        return tipoCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public Date getFechaApertura() {
        return fechaApertura;
    }
    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}

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
        if (monto <= 0) {
            // --- CAMBIO ---
            throw new IllegalArgumentException("El monto a depositar debe ser positivo: " + monto);
        }
        saldo += monto;
        // Se elimina el System.out.println
    }

    /**
     * Retira un monto de la cuenta.
     * @param monto El monto a retirar (debe ser positivo y no exceder el saldo).
     * @throws IllegalArgumentException si el monto es menor o igual a cero.
     * @throws IllegalStateException si el saldo es insuficiente.
     */
    public void retirar(double monto) {
        if (monto <= 0) {
            // --- CAMBIO ---
            throw new IllegalArgumentException("El monto a retirar debe ser positivo: " + monto);
        }
        if (monto > saldo) {
            // --- CAMBIO ---
            throw new IllegalStateException("Saldo insuficiente. Saldo actual: " + saldo + ", Retiro: " + monto);
        }
        saldo -= monto;
        // Se elimina el System.out.println
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void registrarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    // --- CAMBIO ---
    // Se elimina 'mostrarMovimientos()'.
    // El método 'getTransacciones()' permite a 'Main' o 'Gestor' acceder y mostrar los datos.

    // --- Getters ---
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
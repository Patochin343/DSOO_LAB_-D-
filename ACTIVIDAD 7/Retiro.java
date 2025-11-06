import java.util.Date;
public class Retiro extends Transaccion {
    public Retiro(String idTransaccion, Date fecha, double monto) {
        super(idTransaccion, fecha, monto);
    }
    public void procesar(Cuenta cuenta) {
        if (cuenta.getSaldo() >= getMonto()) {
            cuenta.retirar(getMonto());
            cuenta.registrarTransaccion(this);
            System.out.println("Transacción de retiro procesada correctamente.");
        } else {
            System.out.println("Saldo insuficiente para realizar el retiro.");
        }
    }
    public String toString() {
        return "Retiro - Monto: " + getMonto() + " - Fecha: " + getFecha();
    }
}


// Archivo: Retiro.java
import java.util.Date;
public class Retiro extends Transaccion {
    public Retiro(String idTransaccion, Date fecha, double monto) {
        super(idTransaccion, fecha, monto);
    }
    
    public void procesar(Cuenta cuenta) {
        // --- CAMBIO ---
        // Se elimina la comprobación de saldo. 
        // cuenta.retirar() lo hará y lanzará una excepción si falla.
        
        cuenta.retirar(getMonto()); // Puede lanzar IllegalStateException o IllegalArgumentException
        cuenta.registrarTransaccion(this);
        // Se elimina el System.out.println
    }
    
    public String toString() {
        return "Retiro - Monto: " + getMonto() + " - Fecha: " + getFecha();
    }
}
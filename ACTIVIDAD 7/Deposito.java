// Archivo: Deposito.java
import java.util.Date;
public class Deposito extends Transaccion {
    public Deposito(String idTransaccion, Date fecha, double monto) {
        super(idTransaccion, fecha, monto);
    }
    
    public void procesar(Cuenta cuenta){
            cuenta.depositar(getMonto()); // Puede lanzar IllegalArgumentException
            cuenta.registrarTransaccion(this);
            // Se elimina el System.out.println
    }
    
    public String toString(){
        return "Deposito - Monto: "+getMonto()+" - Fecha: "+getFecha();
    }
}
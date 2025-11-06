import java.util.Date;
public class Deposito extends Transaccion {
    public Deposito(String idTransaccion, Date fecha, double monto) {
        super(idTransaccion, fecha, monto);
    }
    public void procesar(Cuenta cuenta){
            cuenta.depositar(getMonto());
            cuenta.registrarTransaccion(this);
            System.out.println("Transacción de depósito procesada correctamente");
    }
    public String toString(){
        return "Deposito - Monto: "+getMonto()+" - Fecha: "+getFecha();
    }
}

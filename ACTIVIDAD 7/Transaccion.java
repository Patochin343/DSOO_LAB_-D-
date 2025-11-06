import java.util.Date;

public abstract class Transaccion {
    private String idTransaccion;
    private Date fecha;
    private double monto;

    public Transaccion(String idTransaccion, Date fecha, double monto) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
    }

    // Método abstracto a implementar por subclases
    public abstract void procesar(Cuenta cuenta);

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Transaccion{idTransaccion='" + idTransaccion + "', fecha=" + fecha + ", monto=" + monto + "}";
    }
}

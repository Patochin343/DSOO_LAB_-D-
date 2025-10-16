public class Cita {
    private int codigoCita;
    private Paciente paciente;
    private Doctor doctor;
    private String fecha;
    private String estado;
    private String hora;

    public Cita(int codigoCita, Paciente paciente, Doctor doctor, String fecha, String estado, String hora) {
        this.codigoCita = codigoCita;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.estado = estado;
        this.hora = hora;
    }

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHora(){
        return this.hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public void mostrarInfo() {
        System.out.println("Código de cita: " + codigoCita);
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Doctor: " + doctor.getNombre());
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Estado: " + estado);
    }
}

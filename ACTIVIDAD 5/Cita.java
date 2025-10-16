public class Cita {
    private int codigoCita;
    private Paciente paciente;
    private Doctor doctor;
    private FechayHora fechayHora;
    private String estado;

    public Cita(int codigoCita, Paciente paciente, Doctor doctor, String estado, FechayHora fechayHora) {
        this.codigoCita = codigoCita;
        this.paciente = paciente;
        this.doctor = doctor;
        this.estado = estado;
        this.fechayHora = fechayHora;
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

    public FechayHora getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(FechayHora fecha) {
        this.fechayHora = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void mostrarInfo() {
        System.out.println("Código de cita: " + codigoCita);
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Doctor: " + doctor.getNombre());
        System.out.println("Fecha y hora: " + fechayHora.toString());
        System.out.println("Estado: " + estado);
    }
}

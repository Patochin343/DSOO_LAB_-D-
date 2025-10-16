import java.util.*;
public class Cita {
    private static ArrayList<Integer> codigos = new ArrayList<>();

    private int codigoCita;
    private Paciente paciente;
    private Doctor doctor;
    private String fecha;
    private String estado;

    private Cita(int codigoCita, Paciente paciente, Doctor doctor, String fecha, String estado) {
        this.codigoCita = codigoCita;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.estado = estado;
        Cita.codigos.add(codigoCita);
    }
    public Cita crearCita(int codigoCita, Paciente paciente, Doctor doctor, String fecha, String estado){
        if(Cita.codigos.contains(codigoCita))
            return null;
        else
            return new Cita(codigoCita, paciente, doctor, fecha, estado);
    }

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        if(!Cita.codigos.contains(codigoCita)){
            Cita.codigos.remove(this.codigoCita);
            Cita.codigos.add(codigoCita);
        }
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
    public void mostrarInfo() {
        System.out.println("Código de cita: " + codigoCita);
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Doctor: " + doctor.getNombre());
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
    }
}



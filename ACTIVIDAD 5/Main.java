import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Paciente paciente1 = new Paciente("Luis", 12345678, 19, 12345678);
    Paciente paciente2 = new Paciente("María", 87654321, 25, 87654321);
    Paciente paciente3 = new Paciente("Carlos", 11223344, 30, 11223344);
    Paciente paciente4 = new Paciente("Ana", 44332211, 22, 44332211);
    Paciente paciente5 = new Paciente("José", 99887766, 28, 99887766);
    ArrayList<Paciente> pacientes = new ArrayList<>();
    pacientes.add(paciente1);
    pacientes.add(paciente2);
    pacientes.add(paciente3);
    pacientes.add(paciente4);
    pacientes.add(paciente5);
    Doctor doctor1 = new Doctor(12345678, "Mario", "Cardiólogo", "7:00-13:00");
    Doctor doctor2 = new Doctor(87654321, "Lucía", "Pediatra", "8:00-14:00");
    Doctor doctor3 = new Doctor(11223344, "Andrés", "Dermatólogo", "9:00-15:00");
    Doctor doctor4 = new Doctor(44332211, "Sofía", "Neuróloga", "10:00-16:00");
    Doctor doctor5 = new Doctor(99887766, "Raúl", "Traumatólogo", "11:00-17:00");
    ArrayList<Doctor> doctores = new ArrayList<>();
    doctores.add(doctor1);
    doctores.add(doctor2);
    doctores.add(doctor3);
    doctores.add(doctor4);
    doctores.add(doctor5);
    Cita cita1 = new Cita(12345678, paciente1, doctor1, "18/11/2023", "12:00", "Atendida");
    Cita cita2 = new Cita(87654321, paciente2, doctor2, "20/11/2023", "09:30", "Pendiente");
    Cita cita3 = new Cita(11223344, paciente3, doctor3, "22/11/2023", "10:45", "Cancelada");
    Cita cita4 = new Cita(44332211, paciente4, doctor4, "25/11/2023", "11:15", "Cancelada");
    Cita cita5 = new Cita(99887766, paciente5, doctor5, "28/11/2023", "08:50", "Atendida");
    ArrayList<Cita> citas = new ArrayList<>();
    citas.add(cita1);
    citas.add(cita2);
    citas.add(cita3);
    citas.add(cita4);
    citas.add(cita5);
    SistemaDeCitas sistemaDeCitas=new SistemaDeCitas(pacientes,doctores,citas);
    Cita cita6 = new Cita(99887767, paciente5, doctor5, "28/11/2023", "08:51", "Atendida");
    sistemaDeCitas.registrarCita(cita6);
    sistemaDeCitas.mostrarCitasAtendidasYCanceladas();
  }
}

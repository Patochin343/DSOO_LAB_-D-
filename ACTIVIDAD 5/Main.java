import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear pacientes
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

        // CORRECCIÓN: Crear horarios para doctores usando FechayHora
        FechayHora horarioInicioDoc1 = new FechayHora("01/01/2024", "07:00");
        FechayHora horarioFinDoc1 = new FechayHora("01/01/2024", "13:00");
        Doctor doctor1 = new Doctor(12345678, "Mario", "Cardiólogo", horarioInicioDoc1, horarioFinDoc1);

        FechayHora horarioInicioDoc2 = new FechayHora("01/01/2024", "08:00");
        FechayHora horarioFinDoc2 = new FechayHora("01/01/2024", "14:00");
        Doctor doctor2 = new Doctor(87654321, "Lucía", "Pediatra", horarioInicioDoc2, horarioFinDoc2);

        FechayHora horarioInicioDoc3 = new FechayHora("01/01/2024", "09:00");
        FechayHora horarioFinDoc3 = new FechayHora("01/01/2024", "15:00");
        Doctor doctor3 = new Doctor(11223344, "Andrés", "Dermatólogo", horarioInicioDoc3, horarioFinDoc3);

        FechayHora horarioInicioDoc4 = new FechayHora("01/01/2024", "10:00");
        FechayHora horarioFinDoc4 = new FechayHora("01/01/2024", "16:00");
        Doctor doctor4 = new Doctor(44332211, "Sofía", "Neuróloga", horarioInicioDoc4, horarioFinDoc4);

        FechayHora horarioInicioDoc5 = new FechayHora("01/01/2024", "11:00");
        FechayHora horarioFinDoc5 = new FechayHora("01/01/2024", "17:00");
        Doctor doctor5 = new Doctor(99887766, "Raúl", "Traumatólogo", horarioInicioDoc5, horarioFinDoc5);

        ArrayList<Doctor> doctores = new ArrayList<>();
        doctores.add(doctor1);
        doctores.add(doctor2);
        doctores.add(doctor3);
        doctores.add(doctor4);
        doctores.add(doctor5);

        // CORRECCIÓN: Crear citas con parámetros en orden correcto
        FechayHora fechaCita1 = new FechayHora("18/11/2023", "12:00");
        Cita cita1 = new Cita(1, paciente1, doctor1, "Atendida", fechaCita1);

        FechayHora fechaCita2 = new FechayHora("20/11/2023", "09:30");
        Cita cita2 = new Cita(2, paciente2, doctor2, "Pendiente", fechaCita2);

        FechayHora fechaCita3 = new FechayHora("22/11/2023", "10:45");
        Cita cita3 = new Cita(3, paciente3, doctor3, "Cancelada", fechaCita3);

        FechayHora fechaCita4 = new FechayHora("25/11/2023", "11:15");
        Cita cita4 = new Cita(4, paciente4, doctor4, "Cancelada", fechaCita4);

        FechayHora fechaCita5 = new FechayHora("28/11/2023", "08:50");
        Cita cita5 = new Cita(5, paciente5, doctor5, "Atendida", fechaCita5);

        ArrayList<Cita> citas = new ArrayList<>();
        citas.add(cita1);
        citas.add(cita2);
        citas.add(cita3);
        citas.add(cita4);
        citas.add(cita5);

        // Crear sistema de citas
        SistemaDeCitas sistemaDeCitas = new SistemaDeCitas(pacientes, doctores, citas);

        // PRUEBA SIMPLE DEL SISTEMA
        System.out.println("=== PRUEBA DEL SISTEMA DE CITAS MÉDICAS ===\n");

        // 1. Mostrar todas las citas programadas
        System.out.println("1. Todas las citas programadas:");
        sistemaDeCitas.mostrarCitasProgramadas();

        // 2. Mostrar citas de un doctor específico
        System.out.println("\n2. Citas del Dr. Mario (Cardiólogo):");
        sistemaDeCitas.mostrarCitasPorDoctor(12345678);

        // 3. Mostrar citas de un paciente específico
        System.out.println("\n3. Citas del paciente Luis:");
        sistemaDeCitas.mostrarCitasPorPaciente(12345678);

        // 4. Mostrar estadísticas de citas
        System.out.println("\n4. Estadísticas de citas:");
        sistemaDeCitas.mostrarCitasAtendidasYCanceladas();

        // 5. Cambiar estado de una cita
        System.out.println("\n5. Cambiando estado de la cita 2 a 'Atendida':");
        sistemaDeCitas.cambiarEstadoDeCita(2, "Atendida");
        
        // Verificar el cambio
        System.out.println("\n6. Citas del Dr. Lucía después del cambio:");
        sistemaDeCitas.mostrarCitasPorDoctor(87654321);

        // 7. Intentar registrar una nueva cita
        System.out.println("\n7. Registrando nueva cita:");
        sistemaDeCitas.registrarCita(99887766, 11223344, "30/11/2023", "14:30");

        // 8. Mostrar estadísticas finales
        System.out.println("\n8. Estadísticas finales:");
        sistemaDeCitas.mostrarCitasAtendidasYCanceladas();
    }
}

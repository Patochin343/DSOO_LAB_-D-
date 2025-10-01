import java.util.ArrayList;
import java.util.Scanner;

public class SistemaDeMatriculas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Curso> semestre = new ArrayList<>();

        System.out.println("¿Cuántos cursos hay este semestre?");
        int numeroDeCursos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numeroDeCursos; i++) {
            System.out.println("Ingrese el nombre del curso:");
            String nombreDelCurso = sc.nextLine();

            System.out.println("Ingrese el código del curso:");
            int codigoCurso = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingrese nombre del docente:");
            String nombreDelDocente = sc.nextLine();

            System.out.println("Ingrese apellidos del docente:");
            String apellidosDelDocente = sc.nextLine();

            System.out.println("Ingrese DNI del docente:");
            int DNI = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingrese especialidad del docente:");
            String especialidad = sc.nextLine();

            System.out.println("Ingrese años de experiencia del docente:");
            int experiencia = sc.nextInt();
            sc.nextLine();

            Docente docente = new Docente(experiencia, DNI, apellidosDelDocente, especialidad, nombreDelDocente);
            Curso curso = new Curso(codigoCurso, docente, nombreDelCurso);
            semestre.add(curso);
        }

        for (Curso curso : semestre) {
            System.out.println("Ingrese el límite de alumnos por aula:");
            int limitePorAula = sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < limitePorAula; j++) {
                System.out.println("Ingrese nombre del alumno:");
                String nombreDelAlumno = sc.nextLine();

                System.out.println("Ingrese apellidos del alumno:");
                String apellidosDelAlumno = sc.nextLine();

                System.out.println("Ingrese CUI del alumno:");
                int CUI = sc.nextInt();
                sc.nextLine();

                Alumno alumno = new Alumno(CUI, apellidosDelAlumno, nombreDelAlumno);
                curso.IngresarAlumno(alumno);

                System.out.println("Ingrese notas de evaluación 1, 2 y 3 (0-20):");
                int nota1 = sc.nextInt();
                int nota2 = sc.nextInt();
                int nota3 = sc.nextInt();
                sc.nextLine();

                curso.NotasDeEvaluaciones(nota1, nota2, nota3, alumno);
            }

            System.out.println("El mejor del curso fue: " + curso.MejorPonderado());
            System.out.println("Lista de aprobados y desaprobados:");
            curso.Registro();
        }

        sc.close();
    }
}
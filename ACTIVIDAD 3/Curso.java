/*Importamos*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Curso{
    
    private String nombreCurso;
    private int codigoCurso;
    private Docente docente;
    private ArrayList<Alumno> alumnado = new ArrayList<>();
    private ArrayList<Alumno> aprobados = new ArrayList<>();
    private ArrayList<Alumno> desaprobados = new ArrayList<>();
    private Map<Integer, Double> Notas = new HashMap<>();
    private int nota1, nota2, nota3;

    /*Constructor que va inicializar los datos */
    public Curso(int codigoCurso, Docente docente, 
                 String nombreCurso) {

        this.codigoCurso = codigoCurso;
        this.docente = docente;
        this.nombreCurso = nombreCurso;
    }

    /*Metodo que ingresa datos de los alumnos */
    public void IngresarAlumno(Alumno alumno){
        alumnado.add(alumno);
        Notas.put(alumno.getCUI(), 0.0);
    }

    /*Sitema de Evaluacion */
    public void NotasDeEvaluaciones(int nota1, int nota2, int nota3, 
                                    Alumno alumno){
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        Notas.put(alumno.getCUI(), PromedioPonderado());
    }

    /*Promedio Ponderado */
    public double PromedioPonderado(){
        return (nota1*0.2)+(nota2*0.3)+(nota3*0.5);
    }

    /*El mejor del curso */
    public Alumno MejorPonderado(){
        double MejorNota = 0.0;
        int indice = 0;
        for(int i = 0; i < alumnado.size(); i++)
        {
            Double Nota = Notas.get(alumnado.get(i).getCUI());
            if(MejorNota <= Nota)
            {
                indice = i;
            }
        }
        return alumnado.get(indice);
    }

    /*Mostrara una lista de desapobrados y desapobrados */
    public void Registro(){
        for(int i = 0; i < alumnado.size(); i++)
        {
            Double Nota = Notas.get(alumnado.get(i).getCUI());
            if(10.5 <= Nota)
            {
                aprobados.add(alumnado.get(i));
            }
            else
            {
                desaprobados.add(alumnado.get(i));
            }
        }

        System.out.println("Los alumnos Aprobados son ");
        for(Alumno Elemento : aprobados)
        {
            System.out.println(Elemento);
        }

        System.out.println("Los alumnos Desaprobados son ");
        for(Alumno Elemento : desaprobados)
        {
            System.out.println(Elemento);
        }
    }
}
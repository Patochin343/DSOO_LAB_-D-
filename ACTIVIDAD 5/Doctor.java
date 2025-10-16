import java.util.*;
public class Doctor 
{
    private static ArrayList<Integer> codigos = new ArrayList<>();


    /* Atributos del doctor */
    private int codigo;
    private String nombre; 
    private String especialidad; 
    private String horarioDeAtencion; 

    /* Método Constructor */
    private Doctor (int codigo, String nombre, String especialidad, String horarioDeAtencion)
    {
        this.codigo=codigo;
        this.nombre=nombre;
        this.especialidad=especialidad; 
        this.horarioDeAtencion=horarioDeAtencion;
    }

    /* Constructor con verificacion*/
    public Doctor crearDoctor(int codigo, String nombre, String especialidad, String horarioDeAtencion){
        if(Doctor.codigos.contains(codigo))
            return null;
        else
            return new Doctor(codigo, nombre, especialidad, horarioDeAtencion);
    }

    /* Métodos Getters */
    public int getCodigo ()
    {
        return codigo; 
    }
    public String getNombre ()
    {
        return nombre; 
    }
    public String getEspecialidad ()
    {
        return especialidad; 
    }
    public String getHorarioDeAtencion ()
    {
        return horarioDeAtencion; 
    }
    
    /* Métodos Setters */
    public void setCodigo (int codigo)
    {
        if(!Doctor.codigos.contains(codigo)){
            Doctor.codigos.remove(this.codigo);
            Doctor.codigos.add(codigo);
        }
    }
    public void setNombre (String nombre)
    {
        this.nombre=nombre; 
    }
    public void setEspecialidad (String especialidad)
    {
        this.especialidad=especialidad;
    }
    public void setHorarioDeAtencion (String horarioDeAtencion)
    {
        this.horarioDeAtencion=horarioDeAtencion;
    }
}
public class Doctor 
{
    /* Atributos del doctor */
    private int codigo;
    private String nombre; 
    private String especialidad; 
    private String horarioDeAtencion; 

    /* Método Constructor */
    public Doctor (int codigo, String nombre, String especialidad, String horarioDeAtencion)
    {
        this.codigo=codigo;
        this.nombre=nombre;
        this.especialidad=especialidad; 
        this.horarioDeAtencion=horarioDeAtencion;
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
        this.codigo=codigo;
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
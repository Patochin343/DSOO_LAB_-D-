public class Docente{

    private int DNI;
    private String nombre;
    private String apellido;
    private String especialidad;
    private int AñosDeExperiencia;

    /*Constructor Sobrecargado para inicializar los datos */
    public Docente(int AñosDeExperiencia, int DNI, 
                   String apllido, String especialidad, 
                   String nombre) {

        this.AñosDeExperiencia = AñosDeExperiencia;
        this.DNI = DNI;
        this.apellido = apllido;
        this.especialidad = especialidad;
        this.nombre = nombre;
    }

    /*Metodos Acsesores*/
    public int getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getAñosDeExperiencia() {
        return AñosDeExperiencia;
    }

    /*Metodos Mutadores*/
    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apllido) {
        this.apellido = apllido;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setAñosDeExperiencia(int AñosDeExperiencia) {
        this.AñosDeExperiencia = AñosDeExperiencia;
    }

    /*Metodo De Impresion o de Salida*/

    @Override
    public String toString() {
        return apellido+", "+nombre+
            "\nDNI: "+DNI+
            "\nEspecialidad: "+especialidad;
    }

}
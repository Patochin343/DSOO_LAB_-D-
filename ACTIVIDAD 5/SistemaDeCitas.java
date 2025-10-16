import java.util.ArrayList;
public class SistemaDeCitas {
  
  // Atributos del SistemaDeCitas
  private ArrayList<Paciente> pacientes=new ArrayList<>();
  private ArrayList<Doctor> doctores=new ArrayList<>();
  private ArrayList<Cita> citas=new ArrayList<>();

  // Constructor
  public SistemaDeCitas(ArrayList<Paciente> pacientes, ArrayList<Doctor> doctores, ArrayList<Cita> citas) {
    this.pacientes = pacientes;
    this.doctores = doctores;
    this.citas = citas;
  }
  // Métodos getters y setters
  public ArrayList<Paciente> getPacientes() {
    return pacientes;
  }
  public void setPacientes(ArrayList<Paciente> pacientes) {
    this.pacientes = pacientes;
  }
  public ArrayList<Doctor> getDoctores() {
    return doctores;
  }
  public void setDoctores(ArrayList<Doctor> doctores) {
    this.doctores = doctores;
  }
  public ArrayList<Cita> getCitas() {
    return citas;
  }
  public void setCitas(ArrayList<Cita> citas) {
    this.citas = citas;
  }

  
  public void registrarDoctor(Doctor doctor){
    if(buscarDoctorPorCodigo(doctor.getCodigo())==null){
      doctores.add(doctor);  
    }else{
      System.out.println("El doctor ya existe");
    }
  }
  public Doctor buscarDoctorPorCodigo(int codigo){
    for(Doctor doctor:doctores){
      if(codigo==doctor.getCodigo()){
        return doctor;
      }
    }
    return null;
  }  
  public void registrarPaciente(Paciente paciente){
    if (buscarPacientePorCodigo(paciente.getCodigoDocumento()) == null && validarEdadPaciente(paciente)) {
      pacientes.add(paciente);
    } else if (!validarEdadPaciente(paciente)) {
      System.out.println("La edad del paciente no es válida");
    } else {
      System.out.println("El paciente ya existe");
    }
  }
  private Paciente buscarPacientePorCodigo(int codigo){
    for(Paciente paciente:pacientes){
      if(codigo==paciente.getCodigoDocumento()){
        return paciente;
      }
    }
    return null;
  }
  private boolean validarEdadPaciente(Paciente paciente){
    return paciente.getEdad()>0;
  }
  public void registrarCita(Cita cita){
    if(validarCita(cita.getHora(),cita.getFecha())&&buscarCitaPorCodigo(cita.getCodigoCita())==null){
      citas.add(cita);
    }else if(buscarCitaPorCodigo(cita.getCodigoCita())!=null){
      System.out.println("Ya existe una cita con ese código");
    }else{
      System.out.println("Ese horario está ocupado");
    }
  }
  private boolean validarCita(String hora, String fecha){
    for(Cita cita:citas){
      if(hora.equals(cita.getHora())&&fecha.equals(cita.getFecha())){
        return false;
      }
    }
    return true; 
  }
  private Cita buscarCitaPorCodigo(int codigo){
    for(Cita cita:citas){
      if(cita.getCodigoCita()==codigo){
        return cita;
      }
    }
    return null;
  }
  public void cambiarEstadoDeCita(int codigoCita, String estado){
    Cita cita=buscarCitaPorCodigo(codigoCita);
    if(cita!=null){
      cita.setEstado(estado);
    }    
  }
  public void mostrarCitasProgramadas(){
    for(Cita cita:citas){
      cita.mostrarInfo();
      System.out.println("-------------------------");
    }
  }
  public void mostrarCitasPorDoctor(int codigo){
    for(Cita cita:citas){
      if(cita.getDoctor().getCodigo()==codigo){
        cita.mostrarInfo();
        System.out.println("-------------------------");
      }
    }
  }
  public void mostrarCitasPorPaciente(int codigoDocumento){
    for(Cita cita:citas){
      if(cita.getPaciente().getCodigoDocumento()==codigoDocumento){
        cita.mostrarInfo();
        System.out.println("-------------------------");
      }
    }
  }
  public void mostrarCitasAtendidasYCanceladas(){
    System.out.println("Citas atendidas: "+numerosCitasAtendidas()+"\n"+
            "Citas canceladas: "+numerosCitasCanceladas());
  }
  public int numerosCitasAtendidas(){
    int atendidas=0;
    for(Cita cita:citas){
      if(cita.getEstado().equalsIgnoreCase("Atendida")){
        atendidas++;
      }
    }
    return atendidas;
  }
  public int numerosCitasCanceladas(){
    int canceladas=0;
    for(Cita cita:citas){
      if(cita.getEstado().equalsIgnoreCase("Cancelada")){
        canceladas++;
      }
    }
    return canceladas;
  }  
}

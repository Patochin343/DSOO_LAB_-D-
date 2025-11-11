package Actividad6;

public class Main {
  public static void main(String[] args) {
    Banco banco=new Banco();
    Menu menu=new Menu(banco);

    // Super admin
    Administrador admin1=Administrador.registrarSuperAdmin(banco,"Luis","García","60758627","123","989045911","lgarciada@unsa.edu.pe","contraseña123");

    // Creando admins
    Administrador admin2=admin1.registrarAdmin(banco,"Oriana","del Pilar","12345678","456","948384940","ocutipa@unsa.edu.pe","asdfjklñ");

    // Creando empleados
    Empleado emp1=admin1.registrarEmpleado(banco,"Lucía","Corrales","34567891","891","932483922","lcorrales@unsa.edu.pe",TipoCargo.ASESOR_FINANCIERO,"ru38892k");
    Empleado emp2=admin2.registrarEmpleado(banco,"Mario","Luigi","43829389","389","943883921","mluigi@unsa.edu,pe",TipoCargo.ANALISTA_CREDITO,"389skfs");

    // Creando clientes
    Cliente cl1=emp1.registrarCliente(banco,"Anabel","Benito","48399392","389","983929038","abenito@unsa.edu.pe","2389ksdfj");
    Cliente cl2=admin1.registrarCliente(banco,"Estrella","Juarez","48992838","382","938382839","ejuarez@unsa.edu.pe","894k3sjfkd");

    // Registrando cuentas
    Cuenta cuenta1=emp1.registrarCuenta(banco,cl1,TipoCuenta.AHORRO,32_894.2);
    Cuenta cuenta2=admin2.registrarCuenta(banco,cl2,TipoCuenta.CORRIENTE);

    // Añadiendo titulares a una cuenta
    admin1.vincularClienteACuenta(banco,cl1,cl2,cuenta1);
    banco.mostrarCuentas();
    Persona persona=menu.login();
    if (persona instanceof SubmenuPersona usuario) {
      usuario.submenu(banco,menu,persona);
    }
    cuenta2.mostrarMovimientos();
    System.out.println(cuenta2.mostrarCuenta());
  }
}

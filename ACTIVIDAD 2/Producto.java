public class Producto{
    int codigo;
    String nombre;
    double precio;
    int stock;

    //Constructor por defecto
    public Producto (int codigo, String nombre, double precio, int stock){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    } 

    //Elimina la cantidad del inventario
    public void AgotarStock(int Cantidad){
        stock -= Cantidad;
    }

    //Modo en el que se imprimira o se mostrara
    public String toString(){
        return "Codigo : "+codigo+
             "\nNombre : "+nombre+
             "\nPrecio : $"+precio+
             "\nStock : "+stock;
    }
}
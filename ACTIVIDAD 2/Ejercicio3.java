import java.util.*;

public class Ejercicio3 {
    public static void main(String[] args) {
        //Crear productos
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "arroz", 3.5, 12));
        productos.add(new Producto(2, "azúcar", 4.0, 7));
        productos.add(new Producto(3, "aceite", 8.0, 30));
        productos.add(new Producto(4, "mermelada", 2.0, 10));

        //Crear ventas
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, 1, 3)); // venta 1: arroz x3
        ventas.add(new Venta(1, 2, 2)); // venta 1: azúcar x2
        ventas.add(new Venta(2, 3, 5)); // venta 2: aceite x5
        ventas.add(new Venta(2, 4, 1)); // venta 2: mermelada x1

        //Solicitar código de venta
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código de venta a consultar: ");
        int codigoConsulta = scanner.nextInt();

        double total = 0;
        for (Venta v : ventas) {
            if (v.codigoVenta == codigoConsulta) {
                Producto p = buscarProducto(productos, v.codigoProducto);
                if (p != null) {
                    if (p.stock >= v.cantidad) {
                        total += p.precio * v.cantidad;
                        p.AgotarStock(v.cantidad);
                    } else {
                        System.out.println("No hay suficiente stock de " + p.nombre);
                    }
                }
            }
        }

        System.out.println("Monto total a pagar por la venta " + codigoConsulta + ": $" + total);

        //Mostrar productos ordenados por precio
        productos.sort(Comparator.comparingDouble(p -> p.precio));
        System.out.println("\n Productos ordenados por precio:");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public static Producto buscarProducto(List<Producto> productos, int codigo) {
        for (Producto p : productos) {
            if (p.codigo == codigo) return p;
        }
        return null;
    }
}




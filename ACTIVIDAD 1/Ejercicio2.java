public class Ejercicio2{
    public static void main(String[] args) {
        //Inicializamos un Arreglo
         int[] numeros = {3, 7, 1, 9, 4, 2, 8, 5};
        ArregloDe8 arreglo = new ArregloDe8(numeros);

        //Mostrara los Numeros por defectos
        System.out.println("Original: ");
        arreglo.toString(arreglo.getOriginal());

        //Mostrara los Numeros de Forma Inversa
        System.out.println("Inverso: ");
        arreglo.toString(arreglo.getInverso());
    }
}
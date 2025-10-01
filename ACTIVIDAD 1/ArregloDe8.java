public class ArregloDe8 {
    private int[] datos = new int[8];

    // Constructor con datos iniciales
    public ArregloDe8(int[] entrada) {
        for (int i = 0; i < 8; i++) {
            datos[i] = entrada[i];
        }
    }

    // Método para obtener el arreglo original
    public int[] getOriginal() {
        return datos;
    }

    // Método para obtener el arreglo inverso
    public int[] getInverso() {
        int[] inverso = new int[8];
        for (int i = 0; i < 8; i++) {
            inverso[i] = datos[7 - i];
        }
        return inverso;
    }

    // Método para imprimir un arreglo
    public void toString(int[] arreglo) {
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
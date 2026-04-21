package tp1.ejercicio2;

import java.util.Scanner;

public class Multiplos {

    public static int[] multiplosDeN(int n) {
        int[] resultado = new int[n];
        for (int k=1; k <= n ; k++ ){
            resultado[k - 1] = n * k; 
        }
        return resultado;
    }

    public static void imprimirArreglo (int[] arreglo) {
        System.out.print(" [ ");
        for (int i=0; i < arreglo.length; i++){
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1) {
                System.out.print(", ");
            }

        }
        System.out.print( " ] "); 
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese n: ");
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] multiplos = multiplosDeN(n);
            // imprimir el arreglo
            imprimirArreglo (multiplos);

            System.out.print("Ingrese n (Ctrl+D para salir): ");
        }
        sc.close();
    }
}

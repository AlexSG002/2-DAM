package hundirLaFlota;

import java.util.Random;

public class Tablero {
    public static int[][] generarTablero() {
        int[][] tablero = new int[10][10];
        Random rand = new Random();
        int barcos = 0;

        while (barcos < 10) {
            int fila = rand.nextInt(10);
            int columna = rand.nextInt(10);

            if (tablero[fila][columna] == 0) {
                tablero[fila][columna] = 1;
                barcos++;
            }
        }

        return tablero;
    }

    public static void imprimirTablero(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad5;
//Importamos la clase Scanner
import java.util.Scanner;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Principal {
    //Método main
    public static void main(String[] args) {
        //Imprimimos por pantalla solicitando al usuario un número
        System.out.println("Introduce un número para calcular su factorial");
        //Creamos la variable sc de clase Scanner para recibir la entrada del usuario
        Scanner sc = new Scanner(System.in);
        //Creamos la variable entera n1 y la inicializamos al siguiente entero introducido por el usuario
        int n1 = sc.nextInt();
        //Creamos la variable entera total para operar sobre ella y la inicializamos a 1 porque vamos a multiplicar
        int total=1;
        //Creamos bucle while con la condición de que itere mientras n1 sea mayor que 0
        while(n1>0){
            //Calculamos
            total*=n1;
            //Restamos uno a n1, que se repetira en cada iteración de manera que cuando llegue a 0 el bucle finalizará
            n1--;
        }
        //Imprimimos el resultado
        System.out.println("El factorial del número introducido es "+total);
    }
}

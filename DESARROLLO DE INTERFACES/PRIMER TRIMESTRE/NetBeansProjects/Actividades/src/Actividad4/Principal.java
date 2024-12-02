/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad4;
//Importamos la clase scanner
import java.util.Scanner;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Principal {
    //Método main, donde ejecutaremos el código de nuestro programa
    public static void main(String[] args) {
        //Función scanner para introducir datos por consola
        Scanner sc = new Scanner(System.in);
        //Imprimimos por pantalla solicitando al usuario que introduzca un número para ver su tabla de multiplicar
        System.out.println("Introduce un número para ver su tabla de multiplicar: ");
        //Inicializamos variable entera n1 y le damos el valor del próximo entero introducido por el usuario
        int n1=sc.nextInt();
        //Bucle for que inicializa la variable i sobre la que iterará hasta 10 veces, sumando +1 a i en cada pasada
        for(int i=1; i<=10;i++){
            //Inicializamos la variable multi para realizar la multiplicación entre n1 e i en cada iteración del bucle
            int multi=n1*i;
            //Imprimimos el resultado por pantalla
            System.out.println(n1+" x "+i+" = "+multi);
        }
    }
}

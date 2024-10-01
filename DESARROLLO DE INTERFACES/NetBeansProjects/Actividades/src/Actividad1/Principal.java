/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad1;

//Importamos la clase scanner
import java.util.Scanner;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Principal {
    //Método main, donde ejecutaremos el código de nuestro programa
    public static void main (String[] args){
        //Función scanner para introducir datos por consola
        Scanner sc = new Scanner(System.in);
        //Imprimimos por pantalla solicitando al usuario un número
        System.out.println("Introduce un número");
        //Inicializamos una nueva variable entera n1 y le damos el valor del siguiente entero introducido por consola
        int n1=sc.nextInt();
        //Repetimos el proceso anterior esta vez con n2
        System.out.println("Introduce otro número");
        int n2=sc.nextInt();
        //Inicializamos una nueva variable entera total y le damos el valor de la suma de n1 y n2
        int total=n1+n2;
        //Imprimimos el resultado por pantalla
        System.out.println("La suma entre "+n1+" y "+n2+" es: "+total);
    }
}

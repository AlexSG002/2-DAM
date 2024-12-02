/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad2;
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
        //Imprimimos por pantalla solicitando al usuario la temperatura en Farenheit
        System.out.println("Introduce la temperatura en Farenheit");
        //Inicializamos una nueva variable entera Celsius y le damos el valor del siguiente entero introducido por consola
        int Celsius=sc.nextInt();
        //Inicializamos una nueva variable entera Farenheit y le damos el valor según la formula de celsius a Farenheit
        int Farenheit = (Celsius*9/5)+32;
        //Imprimimos por pantalla el resultado
        System.out.println(Celsius+" en grados farenheit es: "+Farenheit);
    }
}

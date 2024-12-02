/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad8;
//Importamos la clase Scanner
import java.util.Scanner;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Principal {
    //Método main
    public static void main(String[] args) {
        //Imprimimos por pantalla las indicaciones
        System.out.println("Juguemos, intenta adivinar el número en el que estoy pensando: ");
        //Creamos una variable booleana con la que finalizaremos el bucle en caso de que no se cumpla
        boolean ganaste=false;
        //Creamos e inicializamos la variable numAl a la que le daremos el valor de un número aleatorio del 1 al 100
        int numAl = (int) (Math.random()*100);
        //Imprimo el número aleatorio por control, en un caso real esto no estaría impreso
        System.out.println(numAl);
        do{
            //Creamos la variable sc y le damos valor para aceptar entradas del usuario
            Scanner sc = new Scanner(System.in);
            //Solicitamos por pantalla que el usuario introduzca un número
            System.out.println("Introduce un número: ");
            //Creamos la variable numUsu y la inicializamos al próximo valor entero que introduzca el usuario
            int numUsu=sc.nextInt();
            //If que revisa si las variables numUsu y numAl son iguales
            if(numUsu==numAl){
                //En caso de ser iguales imprime por pantalla ganaste
                System.out.println("Ganaste!");
                //Y cambia a true el valor de la booleana ganaste, lo que finaliza el bucle
                ganaste=true;
            }
            //If que comprueba si numUsu es mayor a numAl
            if(numUsu>numAl){
                //En caso de que si, el sistema imprime un mensaje haciéndolse saber al usuario que el número a adivinar es menor
                System.out.println("El número en el que estoy pensando es menor");
                //Else para indicar caso contrario
            }else
                //If para especificar que en caso contrario si el numUsu es menor al numAl
                if(numUsu<numAl)
                    //Imprima por pantalla un mensaje para indicarle al usuario que el número pensado es mayor
                System.out.println("El número en el que estoy pensando es mayor");
            //Condición de salida del bucle
        }while(!ganaste);
    }
}

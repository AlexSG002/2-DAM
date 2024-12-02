/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad6;
//Importamos la clase scanner
import java.util.Scanner;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Principal {
    //Método main
    public static void main(String[] args) {
        //Imprimimos por pantalla solicitando al usuario una palabra
        System.out.println("Introduce una palabra para contar sus vocales");
        //Creamos variable scanner para introducir datos por consola
        Scanner sc = new Scanner(System.in);
        //Creamos variable String p1 y la inicializamos al siguiente valor que introduzca el usuario por consola
        String p1 = sc.next();
        //Creamos variable entera cont para contar el número de vocales
        int cont=0;
        //Creamos un bucle for que iterará durante la longitud de la palabra introducida, es decir el mismo número de veces que caracteres tenga la palabra
        for(int i=0;i<p1.length();i++){
            //Creamos variable caracter voc para con la función charAt igualar el valor de cada letra a la variable
            char voc=p1.charAt(i);
            //Condición que comprueba si la variable voc es igual a una vocal
            if(voc=='a' || voc=='e' || voc=='i'||voc=='o'||voc=='u'){
                //Si se cumple suma 1 a cont
                cont++;
            }
        }
        //Imprimimos resultado por pantalla
        System.out.println("El número de vocales en la palabra: "+p1+" es de: "+cont);
    }
}

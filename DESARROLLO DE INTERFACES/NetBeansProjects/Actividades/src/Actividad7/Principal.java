/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad7;
//Importamos las clases scanner y arrays
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Alejandro Sánchez Gil
 */
public class Principal {
    //Método main
    public static void main(String[] args){
        //Inicializamos el array de enteros con 5 espacios a 0
        int calificaciones[]={0,0,0,0,0};
        //Inicializamos la variable entera total para operar sobre la misma
        int total=0;
        //Imprimimos cabecera
        System.out.println("CALIFICACIONES");
        //Bucle for que se iterara durante la longitud de la variable calificaciones(5) además de crear e inicializar el iterador i=0
        for(int i=0; i<calificaciones.length;i++){
            //Creamos variable de la clase scanner para solicitar al usuario las calificaciones por pantalla
            Scanner sc = new Scanner(System.in);
            //Solicitamos al usuario que introduzca una calificación
            System.out.println("Introduce una calificación");
            //Creamos variable calificación y le damos el valor del siguiente entero introducido por pantalla
            int cal = sc.nextInt();
            //Utilizando el iterador igualamos cada posición del Array a la calificación introducida por el usuario
            calificaciones[i]=cal;
        }
        //Realizamos otro bucle for, también durante la longitud de la variable calificaciones y otro iterador i=0;
        for(int i=0; i<calificaciones.length;i++){
            //Sumamos al total las calificaciones existentes en cada posición de la variable calificaciones utilizando el iterador
            total+=calificaciones[i];
        }
        //Dividimos el total entre la longitud de calificaciones, en este caso para calcular la media
        total=total/calificaciones.length;
        //Imprimimos por pantalla las calificaciones haciendo uso del método Arrays.toString para que se imprima correctamente
        System.out.println("Las calificaciones introducidas son: "+Arrays.toString(calificaciones));
        //Imprimos la media de las calificaciones introducidas
        System.out.println("La media de las calificaciones introducidas es de: "+total);
    }
}

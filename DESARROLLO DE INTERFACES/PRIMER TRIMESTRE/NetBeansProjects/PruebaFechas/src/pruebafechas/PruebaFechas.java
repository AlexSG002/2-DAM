/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebafechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarde
 */
public class PruebaFechas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date();
        System.out.println(sdf.format(d));
        
        String s = "05-10-2024";
        try {
            d = sdf.parse(s);
        } catch (ParseException ex) {
            System.out.println("Hubo un error con las fechas");
        }
        
        System.out.println(d.toString());
    }
    
}

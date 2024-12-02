/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Tarde
 */
public class MiActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Esta es la nueva llamada al método.");
    }
    
}

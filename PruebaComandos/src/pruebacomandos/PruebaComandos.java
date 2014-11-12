/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pruebacomandos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus Pc
 */
public class PruebaComandos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Process p;
        String com="vlc \"C:\\Users\\Asus PC\\Downloads\\video1.avi\"";
        try {
            
            p=Runtime.getRuntime().exec(com);
        } catch (IOException ex) {
            Logger.getLogger(PruebaComandos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(com);
    }
    
}

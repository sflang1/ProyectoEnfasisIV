/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package programa.de.cambio.de.clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author Asus Pc
 */
public class ProgramaDeCambioDeClases 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            File file=new File("C:\\Users\\Asus Pc\\Desktop\\Proyectos Sebastián\\Enfasis IV\\PrimerXlet\\src\\org\\university\\unicauca\\tdi\\scene\\HS_welcome.java");
            File escritura=new File("C:\\Users\\Asus Pc\\Desktop\\Servidor\\HS_welcome.java");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            BufferedWriter bw=new BufferedWriter(new FileWriter(escritura));
            String buffer="";
            String cadena="";
            String cadena2="";
            while((buffer=br.readLine())!=null)
            {
                if(buffer.contains("LB_SIGNIN_MSG ="))
                {
                    System.out.println("Leyendo la línea");
                    System.out.println(buffer);
                    cadena2=buffer.substring(buffer.indexOf("\"")+1, buffer.lastIndexOf("\""));
                    buffer=buffer.replace(cadena2, "Hola");
                    System.out.println(cadena2);
                    System.out.println(buffer);
                }
                bw.write(buffer+"\n");
                cadena=cadena+"\n"+buffer;
            }
            bw.flush();
            bw.close();
            //Compilación
            String com="javac -cp "
                    + ".;\"C:\\Users\\Asus PC\\Desktop\\Proyectos Sebastián\\Enfasis IV\\libs\\javatv.jar\""
                    + ";\"C:\\Users\\Asus PC\\Desktop\\Proyectos Sebastián\\Enfasis IV\\libs\\mhpstubs.jar\""
                    + ";\"C:\\Users\\Asus PC\\Desktop\\Proyectos Sebastián\\Enfasis IV\\libs\\jmf.jar\""
                    + ";\"C:\\Users\\Desktop\\Proyectos Sebastián\\Enfasis IV\\Primer Xlet\\src\" "
                    + "\"C:\\Users\\Asus PC\\Desktop\\Servidor\\HS_welcome.java\"";
            System.out.println(com);
            Process p=Runtime.getRuntime().exec(com);
            InputStream is=p.getErrorStream();
            byte[] buff=new byte[4096];
            int bytesleidos;
            while((bytesleidos=is.read(buff))!=-1)
            {
                System.out.println((char) bytesleidos);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}

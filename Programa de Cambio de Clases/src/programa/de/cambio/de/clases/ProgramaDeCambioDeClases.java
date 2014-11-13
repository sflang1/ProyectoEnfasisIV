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
    public static final String libjavatv="\"C:\\Users\\Asus PC\\Desktop\\Proyectos Sebastián\\Enfasis IV\\libs\\javatv.jar\"";
    public static final String libjmf="\"C:\\Users\\Asus PC\\Desktop\\Proyectos Sebastián\\Enfasis IV\\libs\\mhpstubs.jar\"";
    public static final String mhpstubs="\"C:\\Users\\Asus PC\\Desktop\\Proyectos Sebastián\\Enfasis IV\\libs\\jmf.jar\"";
    public static final String dirXlet="\"C:\\Users\\Asus PC\\Desktop\\Proyectos Sebastián\\Enfasis IV\\PrimerXlet\\src\" ";
    public static final String claseacompilar="\"C:\\Users\\Asus PC\\Desktop\\Servidor\\HS_welcome.java\"";
    public static final String movera="\"C:\\Users\\Asus PC\\Desktop\\Carpeta pegada";
    public static final String nombreclase="HS_welcome.java";
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
            String com="javac -cp "+".;"+libjavatv+";"+libjmf+";"+mhpstubs+";"+dirXlet+" "+claseacompilar;
            System.out.println(com);
            Process p=Runtime.getRuntime().exec(com);
            BufferedReader readInput = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String linea="";
            while((linea=readInput.readLine())!=null)
            {
                System.out.println(linea);
            }
            String ubicacionclass=claseacompilar.replace(".java", ".class").replace("\"", "").replace("\\", "/");
            System.out.println(ubicacionclass);
            File claseacopiar=new File(ubicacionclass);
            String ubicaciondestino=movera+"\\"+nombreclase.replace(".java", ".class")+"\"";
            ubicaciondestino=ubicaciondestino.replace("\"","").replace("\\","/");
            System.out.println(ubicaciondestino);
            File clasedestino=new File(ubicaciondestino);
            if(clasedestino.exists())
            {
                System.out.println("El fichero en el destino ya existe. Borrando....");
                clasedestino.delete();
            }
            if(!claseacopiar.exists())
            {
                System.out.println("No hay clase de origen");
            }
            Thread.sleep(20000);
            boolean success=claseacopiar.renameTo(clasedestino);
            if(!success)
            {
                System.out.println("No se pudo copiar");
            }
            /*if(clasedestino.exists())
            {
                System.out.println("El fichero en el destino ya existe. Borrando....");
                clasedestino.delete();
            }
            boolean success=claseacopiar.renameTo(clasedestino);
            if(!success)
            {
                System.out.println("No se pudo copiar");
            }
            if(!claseacopiar.exists())
            {
                System.out.println("No hay clase de origen");
            }
            */
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}

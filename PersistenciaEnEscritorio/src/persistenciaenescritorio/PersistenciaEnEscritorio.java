/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistenciaenescritorio;

import Entidad.Titular;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Asus Pc
 */
public class PersistenciaEnEscritorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException 
    {
        int contador=0,i;
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("PersistenciaEnEscritorioPU");
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        Query query=em.createQuery("SELECT t FROM Titular t ORDER BY t.orden");
        List<Titular> titulares=query.getResultList();
        while(true)
        {
            
            query=em.createQuery("SELECT t FROM Titular t ORDER BY t.orden");
            titulares=query.getResultList();
            if(titulares.isEmpty())
            {
                System.out.println("No hay titulares");
            }
            else
            {
                //Reproducir video en el orden contador hacer todos los cambios en el oc-update
                if(!titulares.get(contador).getCommited())
                {
                    System.out.println("Transcodificando el titular: "+titulares.get(contador).getTitular());
                    transcodificarvideo(titulares.get(contador).getUbicacion());
                }
                //Después de transcodificar todo, correr el video
                contador++;
                System.out.println("Contador: "+contador);
                System.out.println("Comparado con: "+(titulares.get(titulares.size()-1).getOrden()));
                if(contador>=(titulares.get(titulares.size()-1).getOrden()))
                {
                    System.out.println("Entra a la comparación");
                    contador=0;
                }
                System.out.println("Nuevo titular a revisar: "+titulares.get(contador).getTitular());
            }
            Thread.sleep(5000);
        }
    }
    public static void transcodificarvideo(String direccion)
    {
        try 
        {
            System.out.println("La dirección recibida es: "+direccion);
            String nomvideo=direccion.substring((direccion.lastIndexOf("\\")+1),direccion.lastIndexOf("."));
            String com="ffmpeg -i "+direccion+" -an -s 720x576 -deinterlace -r 25 -force_fps"
                    + " -aspect 4:3 -vcodec mpeg2video -f mpeg2video -b 2000k -maxrate 2000k"
                    + " -minrate 2000k  -vf 2 -bufsize 1343488 "
                    + nomvideo
                    + ".m2v";
            System.out.println(com);
            com="ffmpeg -i "+direccion+" -ac 2 -vn -acodec mp2 -f mp2 -ab 128000"
                    + " -ar 48000 "
                    + nomvideo
                    + ".mp2";
            System.out.println(com);
            com="esvideompeg2pes "+nomvideo+".m2v > "+nomvideo+".video.pes";
            System.out.println(com);
            com="esaudio2pes "+nomvideo+".mp2 1152 48000 768 -1 3600 >"+nomvideo+".audio.pes";
            System.out.println(com);
            com="pesvideo2ts 2064 25 112 2000000 0 "+nomvideo+".video.pes > "+nomvideo+".ts";
            System.out.println(com);
            com="pesaudio2ts 2068 1152 48000 768 -1 0 "+nomvideo+".audio.pes > "+(nomvideo.replace("video", "audio"))+".ts";
            System.out.println(com);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PersistenciaEnEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

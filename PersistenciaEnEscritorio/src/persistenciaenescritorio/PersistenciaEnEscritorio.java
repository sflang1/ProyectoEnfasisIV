/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistenciaenescritorio;

import Entidad.Titular;
import java.util.List;
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
            //Reproducir video en el orden contador hacer todos los cambios en el oc-update
            query=em.createQuery("SELECT t FROM Titular t ORDER BY t.orden");
            titulares=query.getResultList();
            if(titulares.isEmpty())
            {
                System.out.println("No hay titulares");
            }
            else
            {
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
    
}

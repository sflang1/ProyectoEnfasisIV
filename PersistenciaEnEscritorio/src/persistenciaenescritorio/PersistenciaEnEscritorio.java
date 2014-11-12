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
    public static void main(String[] args) 
    {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("PersistenciaEnEscritorioPU");
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        Query query=em.createNamedQuery("Titular.findAll");
        List<Titular> titulares=query.getResultList();
        for(int i=0;i<titulares.size();i++)
        {
            System.out.println(titulares.get(i).getTitular());
        }
    }
    
}

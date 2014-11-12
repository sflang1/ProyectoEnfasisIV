/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Auxiliar.TitularAuxiliar;
import Entidad.Titular;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus Pc
 */
@Stateless
@LocalBean
public class TitularEJB 
{
    @PersistenceContext(unitName="AplicacionAdministrador-ejbPU")
    private EntityManager em;
    public List<Titular> listarTitulares()
    {
        try
        {
            Query query=em.createQuery("SELECT t FROM Titular t ORDER BY t.orden");
            List<Titular> titulares=new ArrayList<>();
            titulares=query.getResultList();
            return titulares;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public List<Titular> agregarcampo(List<Titular> tits, int a)
    {
        int i,b;
        b=a+1;
        try
        {
            Titular t=new Titular();
            t.setTitular("");
            t.setOrden(a);
            tits.add(t);
            return tits;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public boolean agregarTitular(Titular t)
    {
        try
        {
            em.persist(t);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public int borrarTodo()
    {
        int a;
        try
        {
            Query query=em.createQuery("DELETE FROM Titular");
            a=query.executeUpdate();
            System.out.println(a);
            return 1;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    public List<TitularAuxiliar> mapear(List<Titular> t)
    {
        int i;
        List<TitularAuxiliar> listata=new ArrayList<>();
        for(i=0;i<t.size();i++)
        {
            TitularAuxiliar ta=new TitularAuxiliar();
            ta.setT(t.get(i));
            ta.setChecked(false);
            listata.add(i, ta);
        }
        return listata;
    }
    public int obtenerOrden()
    {
        try
        {
            int a=-999999;
            Query query=em.createNamedQuery("Titular.findAll");
            List<Titular> titulares=query.getResultList();
            if(titulares.isEmpty())
            {
                return 0;
            }
            else
            {
                for(int i=0;i<titulares.size();i++)
                {
                    if(a<titulares.get(i).getOrden())
                    {
                        a=titulares.get(i).getOrden();
                    }
                }
                return a;
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
}

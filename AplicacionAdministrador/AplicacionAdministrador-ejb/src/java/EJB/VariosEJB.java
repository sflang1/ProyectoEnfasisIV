/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EJB;

import Entidad.Varios;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Asus Pc
 */
@Stateless
@LocalBean
public class VariosEJB {
    @PersistenceContext(unitName="AplicacionAdministrador-ejbPU")
    private EntityManager em;
    public int obtenerContador()
    {
        try
        {
            Query query=em.createQuery("SELECT v FROM Varios v WHERE v.descripcion='contador'");
            Varios v=(Varios) query.getSingleResult();
            return Integer.parseInt(v.getValor());
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    public void aumentarContador(int cont)
    {
        try
        {
            Query query=em.createQuery("SELECT v FROM Varios v WHERE v.descripcion='contador'");
            Varios v=(Varios) query.getSingleResult();
            v.setValor(""+cont);
            em.merge(v);
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entidad.Administrador;
import Entidad.Usuario;
import java.util.ArrayList;
import java.util.List;
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
public class AdministradorEJB 
{
    @PersistenceContext(unitName="AplicacionAdministrador-ejbPU")
    private EntityManager em;
    public void listarAdministradores()
    {
        try
        {
            List<Administrador> admins=new ArrayList<>();
            Query q=em.createNamedQuery("Administrador.findAll");
            admins=q.getResultList();
            for(int i=0;i<admins.size();i++)
            {
                System.out.println(admins.get(i).toString());
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public Administrador obtenerAdmin(String username)
    {
        try
        {
            Query query=em.createQuery("SELECT a FROM Administrador a WHERE a.username='"+username+"'");
            Administrador a=(Administrador) query.getSingleResult();
            return a;
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public int autenticarUsuario(String username, String password)
    {
        try
        {
            Query query=em.createQuery("SELECT a FROM Administrador a WHERE a.username='"+username+"'");
            Administrador a=(Administrador) query.getSingleResult();
            if(a.getPassword().equals(password))
            {
                return 3;
            }
            else
            {
                return 1;
            }
        }
        catch(NoResultException ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
}

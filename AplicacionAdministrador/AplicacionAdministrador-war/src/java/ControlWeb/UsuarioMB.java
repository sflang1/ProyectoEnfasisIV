/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlWeb;

import Auxiliar.TitularAuxiliar;
import EJB.AdministradorEJB;
import EJB.TitularEJB;
import EJB.VariosEJB;
import Entidad.Titular;
import Entidad.Administrador;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Asus Pc
 */
@ManagedBean
@SessionScoped
public class UsuarioMB 
{
    @EJB
    private AdministradorEJB aejb;
    @EJB
    private TitularEJB tejb;
    @EJB
    private VariosEJB vejb;
    private Administrador administrador;
    private Administrador sesionAdministrador;
    private List<Titular> titularesviejos;
    private List<TitularAuxiliar> titularesviejosaux;
    private List<TitularAuxiliar> titularesnuevosaux;
    private List<Titular> titularespasados;
    private List<Titular> titularesnuevos;
    private Titular titular;
    private String mensaje;
    private boolean todos;
    private boolean vacio;
    private int diahoy;
    private int meshoy;
    private int agnohoy;
    private int diaayer;
    private int mesayer;
    private int agnoayer;
    private int ordentotal;
    private boolean validado;
    private Part file;
    public UsuarioMB()
    {
        administrador=new Administrador();
        sesionAdministrador=new Administrador();
        mensaje="";
        validado=false;
        vacio=false;
        ordentotal=0;
    }
    public String autenticarAdministrador()
    {
        int a;
        String redir="";
        a=aejb.autenticarUsuario(administrador.getUsername(), administrador.getPassword());
        switch(a)
        {
            case 0:
                mensaje="El usuario no se encuentra en la base de datos";
                redir="error.xhtml";
                break;
            case 1:
                mensaje="No tienes permiso para ver este contenido";
                redir="error.xhtml";
                break;
            case 2:
                mensaje="Contraseña incorrecta";
                redir="error.xhtml";
                break;
            case 3:
                validado=true;
                sesionAdministrador=aejb.obtenerAdmin(administrador.getUsername());
                Calendar c1=Calendar.getInstance();
                Calendar c2=Calendar.getInstance();
                c2.add(Calendar.DAY_OF_MONTH, -1);
                diahoy=c1.get(Calendar.DATE);
                meshoy=1+c1.get(Calendar.MONTH);
                agnohoy=c1.get(Calendar.YEAR);
                diaayer=c2.get(Calendar.DATE);
                System.out.println(""+diaayer);
                mesayer=c2.get(Calendar.MONTH);
                agnoayer=c2.get(Calendar.YEAR);
                titularesviejos=new ArrayList<>();
                titularesviejos=tejb.listarTitulares();
                titularesviejosaux=tejb.mapear(titularesviejos);
                if(titularesviejosaux.isEmpty())
                {
                    vacio=true;
                }
                titularesnuevos=new ArrayList<>();
                titularesnuevosaux=tejb.mapear(titularesnuevos);
                titularespasados=new ArrayList<>();
                mensaje="";
                ordentotal=0;
                redir="admin.xhtml";
                break;
        }
        return redir;
    }
    public String agregarTitulares()
    {
        //System.out.println("Tamaño de la lista: "+titularesnuevos.size());
        ordentotal++;
        titularesnuevos=tejb.agregarcampo(titularesnuevos, ordentotal);
        titularesnuevosaux=new ArrayList<>();
        titularesnuevosaux=tejb.mapear(titularesnuevos);
        return "admin.xhtml";
    }
    public String cerrarSesion()
    {
        sesionAdministrador=new Administrador();
        validado=false;
        return "index.xhtml";
    }
    public void enviarTitulares()
    {
        int i;
        int a=0;
        boolean confirmaciones=true,error=false;
        mensaje="";
        for(i=0;i<titularesnuevos.size();i++)
        {
            if(titularesnuevos.get(i).getTitular().equals("")||titularesnuevos.get(i).getTitular()==null)
            {
                mensaje="No se puede enviar el formulario, un campo está vacío. Intente nuevamente";
                confirmaciones=false;
                break;
            }
            else
            {
                if(!upload(titularesnuevosaux.get(i).getFile(),i))
                {
                    confirmaciones=false;
                    break;
                }
            }
        }
        if(confirmaciones)
        {
            for(i=0;i<titularesviejosaux.size();i++)
            {
                borrar(titularesviejosaux.get(i).getT().getUbicacion());
            }
            a=tejb.borrarTodo();
            for(i=0;i<titularesnuevos.size();i++)
            {
                if(!tejb.agregarTitular(titularesnuevos.get(i)))
                    {
                        mensaje="Error creando el titular: "+titularesnuevos.get(i).getTitular();
                        error=true;
                        break;
                    }
            }
            for(i=0;i<titularespasados.size();i++)
            {
                if(!tejb.agregarTitular(titularespasados.get(i)))
                {
                    mensaje="Error creando el titular: "+titularespasados.get(i).getTitular();
                    error=true;
                    break;
                }
            }
            if(!error)
            {
                mensaje="B.D actualizada exitosamente";
            }
        }
        ordentotal=0;
        titularesnuevos=new ArrayList<>();
        titularespasados=new ArrayList<>();
        titularesviejos=new ArrayList<>();
        titularesviejos=tejb.listarTitulares();
        titularesviejosaux=tejb.mapear(titularesviejos);
        titularesnuevosaux=tejb.mapear(titularesnuevos);
        if(!titularesviejosaux.isEmpty())
        {
            vacio=false;
        }
    }
    public void pasar()
    {
        int i,k=0,mayor=0;
        if(!todos)
        {
            mensaje="";
            List<Titular> vec=new ArrayList<>();
            ordentotal=1;
            for(i=0;i<titularesviejosaux.size();i++)
            {
                if(titularesviejosaux.get(i).isChecked())
                {
                    titularesviejosaux.get(i).getT().setCommited(true);
                    titularesviejosaux.get(i).getT().setOrden(ordentotal);
                    titularespasados.add(titularesviejosaux.get(i).getT());
                    vec.add(titularesviejosaux.get(i).getT());
                    ordentotal++;
                }
            }
            for(i=0;i<vec.size();i++)
            {
                titularesviejos.remove(vec.get(i));
            }
            if(!titularespasados.isEmpty())
            {
                for(i=0;i<titularespasados.size();i++)
                {
                    if(mayor<titularespasados.get(i).getOrden())
                    {
                        mayor=titularespasados.get(i).getOrden();
                    }
                }
                System.out.println("El mayor es: "+mayor);
                ordentotal=ordentotal+mayor;
                for(i=0;i<titularesnuevos.size();i++)
                {
                    titularesnuevos.get(i).setOrden(titularesnuevos.get(i).getOrden()+mayor);
                }
            }
            titularesviejosaux=new ArrayList<>();
            titularesviejosaux=tejb.mapear(titularesviejos);
        }
        else
        {
            for(i=0;i<titularesviejosaux.size();i++)
            {
                titularespasados.add(titularesviejosaux.get(i).getT());
            }
            for(i=0;i<titularespasados.size();i++)
                {
                    if(mayor<titularespasados.get(i).getOrden())
                    {
                        mayor=titularespasados.get(i).getOrden();
                    }
                }
                System.out.println("El mayor es: "+mayor);
                ordentotal=ordentotal+mayor;
                for(i=0;i<titularesnuevos.size();i++)
                {
                    titularesnuevos.get(i).setOrden(titularesnuevos.get(i).getOrden()+mayor);
                }
            titularesviejos=new ArrayList<>();
            titularesviejosaux=new ArrayList<>();
            todos=false;
        }
        if(titularesviejosaux.isEmpty())
        {
            vacio=true;
        }
    }
    public void borrar(String direccion)
    {
        try
        {
            File fichero=new File(direccion);
            fichero.delete();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public boolean upload(Part file,int pos)
    {
        try
        {
            String filename=getFilename(file);
            String tipo=filename.substring((filename.indexOf('.')+1),filename.length());
            System.out.println(tipo);
            if(tipo.equals("avi"))
            {
                mensaje="";
                int cont=vejb.obtenerContador();
                InputStream stream=file.getInputStream();
                String direccion="C:\\Users\\Asus Pc\\Desktop\\Servidor\\video"+cont+"."+tipo;
                titularesnuevosaux.get(pos).getT().setUbicacion(direccion);
                FileOutputStream outputstream=new FileOutputStream("C:\\Users\\Asus Pc\\Desktop\\Servidor\\video"+cont+"."+tipo);
                byte[] buffer=new byte[4096];
                while(true)
                {
                    int data=stream.read(buffer);
                    if(data>0)
                    {
                        outputstream.write(buffer,0,data);
                    }
                    else
                    {
                        break;
                    }
                }        
                stream.close();
                outputstream.close();
                cont++;
                vejb.aumentarContador(cont);
                return true;
            }
            else
            {
                mensaje="Tipo no permitido. Sólo se permite carga de archivos .avi";
                ordentotal=0;
                return false;
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    private String getFilename(Part p)
    {
        for(String cd: p.getHeader("content-disposition").split(";"))
        {
            if(cd.trim().startsWith("filename"))
            {
                String filename=cd.substring(cd.indexOf('=')+1).trim().replace("\"", ""); //Quitar comillas dobles
                return filename;
            }
        }
        return null;
    }
    
    //---------SETTERS Y GETTERS----------
    public Administrador getAdministrador() {
        return administrador;
    }
    public void setAdministrador(Administrador usuario) {
        this.administrador = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Administrador getSesionAdministrador() {
        return sesionAdministrador;
    }

    public void setSesionAdministrador(Administrador sesionAdministrador) {
        this.sesionAdministrador = sesionAdministrador;
    }

    public boolean isValidado() {
        return validado;
    }

    public boolean isTodos() {
        return todos;
    }

    public void setTodos(boolean todos) {
        this.todos = todos;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public List<Titular> getTitularesviejos() {
        return titularesviejos;
    }

    public void setTitularesviejos(List<Titular> titularesviejos) {
        this.titularesviejos = titularesviejos;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public List<Titular> getTitularesnuevos() {
        return titularesnuevos;
    }

    public void setTitularesnuevos(List<Titular> titularesnuevos) {
        this.titularesnuevos = titularesnuevos;
    }

    public List<TitularAuxiliar> getTitularesviejosaux() {
        return titularesviejosaux;
    }

    public void setTitularesviejosaux(List<TitularAuxiliar> titularesviejosaux) {
        this.titularesviejosaux = titularesviejosaux;
    }

    public int getDiahoy() {
        return diahoy;
    }

    public void setDiahoy(int diahoy) {
        this.diahoy = diahoy;
    }

    public int getMeshoy() {
        return meshoy;
    }

    public void setMeshoy(int meshoy) {
        this.meshoy = meshoy;
    }

    public int getAgnohoy() {
        return agnohoy;
    }

    public boolean isVacio() {
        return vacio;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void setVacio(boolean vacio) {
        this.vacio = vacio;
    }

    public void setAgnohoy(int agnohoy) {
        this.agnohoy = agnohoy;
    }

    
    public int getDiaayer() {
        return diaayer;
    }

    public void setDiaayer(int diaayer) {
        this.diaayer = diaayer;
    }

    public int getMesayer() {
        return mesayer;
    }

    public void setMesayer(int mesayer) {
        this.mesayer = mesayer;
    }

    public int getAgnoayer() {
        return agnoayer;
    }

    public void setAgnoayer(int agnoayer) {
        this.agnoayer = agnoayer;
    }

    public List<TitularAuxiliar> getTitularesnuevosaux() {
        return titularesnuevosaux;
    }

    public void setTitularesnuevosaux(List<TitularAuxiliar> titularesnuevosaux) {
        this.titularesnuevosaux = titularesnuevosaux;
    }

    public List<Titular> getTitularespasados() {
        return titularespasados;
    }

    public void setTitularespasados(List<Titular> titularespasados) {
        this.titularespasados = titularespasados;
    }
    
}

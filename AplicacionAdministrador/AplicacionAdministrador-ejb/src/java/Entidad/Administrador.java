/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus Pc
 */
@Entity
@Table(name = "administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByIdadministrador", query = "SELECT a FROM Administrador a WHERE a.idadministrador = :idadministrador"),
    @NamedQuery(name = "Administrador.findByUsername", query = "SELECT a FROM Administrador a WHERE a.username = :username"),
    @NamedQuery(name = "Administrador.findByPassword", query = "SELECT a FROM Administrador a WHERE a.password = :password"),
    @NamedQuery(name = "Administrador.findByNombre", query = "SELECT a FROM Administrador a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Administrador.findByApellido", query = "SELECT a FROM Administrador a WHERE a.apellido = :apellido")})
public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadministrador")
    private Integer idadministrador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "apellido")
    private String apellido;
    @Lob
    @Size(max = 65535)
    @Column(name = "foto")
    private String foto;

    public Administrador() {
    }

    public Administrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }

    public Administrador(Integer idadministrador, String username, String password, String nombre, String apellido) {
        this.idadministrador = idadministrador;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadministrador != null ? idadministrador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.idadministrador == null && other.idadministrador != null) || (this.idadministrador != null && !this.idadministrador.equals(other.idadministrador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Administrador[ idadministrador=" + idadministrador + " ]";
    }
    
}

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
@Table(name = "varios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Varios.findAll", query = "SELECT v FROM Varios v"),
    @NamedQuery(name = "Varios.findByIdvarios", query = "SELECT v FROM Varios v WHERE v.idvarios = :idvarios"),
    @NamedQuery(name = "Varios.findByDescripcion", query = "SELECT v FROM Varios v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Varios.findByValor", query = "SELECT v FROM Varios v WHERE v.valor = :valor")})
public class Varios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvarios")
    private Integer idvarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "valor")
    private String valor;

    public Varios() {
    }

    public Varios(Integer idvarios) {
        this.idvarios = idvarios;
    }

    public Varios(Integer idvarios, String descripcion, String valor) {
        this.idvarios = idvarios;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Integer getIdvarios() {
        return idvarios;
    }

    public void setIdvarios(Integer idvarios) {
        this.idvarios = idvarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvarios != null ? idvarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Varios)) {
            return false;
        }
        Varios other = (Varios) object;
        if ((this.idvarios == null && other.idvarios != null) || (this.idvarios != null && !this.idvarios.equals(other.idvarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Varios[ idvarios=" + idvarios + " ]";
    }
    
}

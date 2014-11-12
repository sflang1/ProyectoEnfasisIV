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
@Table(name = "titular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Titular.findAll", query = "SELECT t FROM Titular t"),
    @NamedQuery(name = "Titular.findByIdtitular", query = "SELECT t FROM Titular t WHERE t.idtitular = :idtitular"),
    @NamedQuery(name = "Titular.findByTitular", query = "SELECT t FROM Titular t WHERE t.titular = :titular")})
public class Titular implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "commited")
    private boolean commited;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtitular")
    private Integer idtitular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 70)
    @Column(name = "titular")
    private String titular;

    public Titular() {
    }

    public Titular(Integer idtitular) {
        this.idtitular = idtitular;
    }

    public Titular(Integer idtitular, String titular) {
        this.idtitular = idtitular;
        this.titular = titular;
    }

    public Integer getIdtitular() {
        return idtitular;
    }

    public void setIdtitular(Integer idtitular) {
        this.idtitular = idtitular;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtitular != null ? idtitular.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titular)) {
            return false;
        }
        Titular other = (Titular) object;
        if ((this.idtitular == null && other.idtitular != null) || (this.idtitular != null && !this.idtitular.equals(other.idtitular))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Titular[ idtitular=" + idtitular + " ]";
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean getCommited() {
        return commited;
    }

    public void setCommited(boolean commited) {
        this.commited = commited;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.entities;

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
 * @author JavierSanchezGozalo
 */
@Entity
@Table(name = "apuntar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apuntar.findAll", query = "SELECT a FROM Apuntar a")
    , @NamedQuery(name = "Apuntar.findByIdapuntar", query = "SELECT a FROM Apuntar a WHERE a.idapuntar = :idapuntar")
    , @NamedQuery(name = "Apuntar.findByEmail", query = "SELECT a FROM Apuntar a WHERE a.email = :email")
    , @NamedQuery(name = "Apuntar.findByIdactividad", query = "SELECT a FROM Apuntar a WHERE a.idactividad = :idactividad")})
public class Apuntar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idapuntar")
    private Integer idapuntar;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idactividad")
    private int idactividad;

    public Apuntar() {
    }

    public Apuntar(Integer idapuntar) {
        this.idapuntar = idapuntar;
    }

    public Apuntar(Integer idapuntar, String email, int idactividad) {
        this.idapuntar = idapuntar;
        this.email = email;
        this.idactividad = idactividad;
    }

    public Integer getIdapuntar() {
        return idapuntar;
    }

    public void setIdapuntar(Integer idapuntar) {
        this.idapuntar = idapuntar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idapuntar != null ? idapuntar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apuntar)) {
            return false;
        }
        Apuntar other = (Apuntar) object;
        if ((this.idapuntar == null && other.idapuntar != null) || (this.idapuntar != null && !this.idapuntar.equals(other.idapuntar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mygim.mygim.entities.Apuntar[ idapuntar=" + idapuntar + " ]";
    }
    
}

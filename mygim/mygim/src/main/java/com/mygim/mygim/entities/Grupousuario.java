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
@Table(name = "grupousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupousuario.findAll", query = "SELECT g FROM Grupousuario g")
    , @NamedQuery(name = "Grupousuario.findByIdgrupo", query = "SELECT g FROM Grupousuario g WHERE g.idgrupo = :idgrupo")
    , @NamedQuery(name = "Grupousuario.findByEmail", query = "SELECT g FROM Grupousuario g WHERE g.email = :email")
    , @NamedQuery(name = "Grupousuario.findByNombregrupo", query = "SELECT g FROM Grupousuario g WHERE g.nombregrupo = :nombregrupo")
, @NamedQuery(name = "Grupousuario.deleteByEmail", query = "DELETE FROM Grupousuario g WHERE g.email = :email")})
public class Grupousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgrupo")
    private Integer idgrupo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombregrupo")
    private String nombregrupo;

    public Grupousuario() {
    }

    public Grupousuario(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Grupousuario(Integer idgrupo, String email, String nombregrupo) {
        this.idgrupo = idgrupo;
        this.email = email;
        this.nombregrupo = nombregrupo;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombregrupo() {
        return nombregrupo;
    }

    public void setNombregrupo(String nombregrupo) {
        this.nombregrupo = nombregrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupo != null ? idgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupousuario)) {
            return false;
        }
        Grupousuario other = (Grupousuario) object;
        if ((this.idgrupo == null && other.idgrupo != null) || (this.idgrupo != null && !this.idgrupo.equals(other.idgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mygim.mygim.entities.Grupousuario[ idgrupo=" + idgrupo + " ]";
    }
    
}

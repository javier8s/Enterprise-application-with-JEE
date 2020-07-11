/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JavierSanchezGozalo
 */
@Entity
@Table(name = "sala")





@SqlResultSetMapping(name="deleteResult", columns = { @ColumnResult(name = "count")})
 
@NamedNativeQueries({
        @NamedNativeQuery(
                name    =   "deleteSalas",
                query   =   "DELETE FROM Sala s WHERE s.nombresala = ?"
                ,resultSetMapping = "deleteResult"
        )
})






@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
    , @NamedQuery(name = "Sala.findByNombresala", query = "SELECT s FROM Sala s WHERE s.nombresala = :nombresala")
    , @NamedQuery(name = "Sala.findByPuestos", query = "SELECT s FROM Sala s WHERE s.puestos = :puestos")
, @NamedQuery(name = "Sala.delete", query = "DELETE FROM Sala s WHERE s.nombresala= :nombresala")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nombresala")
    private String nombresala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puestos")
    private int puestos;

    public Sala() {
    }

    public Sala(String nombresala) {
        this.nombresala = nombresala;
    }

    public Sala(String nombresala, int puestos) {
        this.nombresala = nombresala;
        this.puestos = puestos;
    }

    public String getNombresala() {
        return nombresala;
    }

    public void setNombresala(String nombresala) {
        this.nombresala = nombresala;
    }

    public int getPuestos() {
        return puestos;
    }

    public void setPuestos(int puestos) {
        this.puestos = puestos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombresala != null ? nombresala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.nombresala == null && other.nombresala != null) || (this.nombresala != null && !this.nombresala.equals(other.nombresala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mygim.mygim.entities.Sala[ nombresala=" + nombresala + " ]";
    }
    
}

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
@Table(name = "ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByIdcuentas", query = "SELECT v FROM Ventas v WHERE v.idcuentas = :idcuentas")
    , @NamedQuery(name = "Ventas.findByNombreactividad", query = "SELECT v FROM Ventas v WHERE v.nombreactividad = :nombreactividad")
    , @NamedQuery(name = "Ventas.findByVenta", query = "SELECT v FROM Ventas v WHERE v.venta = :venta")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuentas")
    private Integer idcuentas;
    @Size(max = 25)
    @Column(name = "nombreactividad")
    private String nombreactividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta")
    private int venta;

    public Ventas() {
    }

    public Ventas(Integer idcuentas) {
        this.idcuentas = idcuentas;
    }

    public Ventas(Integer idcuentas, int venta) {
        this.idcuentas = idcuentas;
        this.venta = venta;
    }

    public Integer getIdcuentas() {
        return idcuentas;
    }

    public void setIdcuentas(Integer idcuentas) {
        this.idcuentas = idcuentas;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuentas != null ? idcuentas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idcuentas == null && other.idcuentas != null) || (this.idcuentas != null && !this.idcuentas.equals(other.idcuentas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mygim.mygim.entities.Ventas[ idcuentas=" + idcuentas + " ]";
    }
    
}

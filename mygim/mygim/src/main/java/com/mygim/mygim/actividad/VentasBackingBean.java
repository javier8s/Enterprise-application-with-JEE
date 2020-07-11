/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.actividad;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@SessionScoped
public class VentasBackingBean implements Serializable {

    Integer idcuentas;
    String nombreactividad;

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
    int venta;
}

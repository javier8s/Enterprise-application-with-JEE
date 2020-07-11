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
public class ApuntarBackingBean  implements Serializable{
    Integer idapuntar;
     String email;
     int idactividad;

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
     
     
}

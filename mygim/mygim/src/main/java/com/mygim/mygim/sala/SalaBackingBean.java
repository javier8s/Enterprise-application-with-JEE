/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.sala;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@SessionScoped
public class SalaBackingBean implements Serializable {

    String nombresala;
    int puestos;

    public int getPuestos() {
        return puestos;
    }

    public void setPuestos(int puestos) {
        this.puestos = puestos;
    }

    public String getNombresala() {

        return nombresala;
    }

    public void setNombresala(String nombresala) {
        this.nombresala = nombresala;
    }

   
}

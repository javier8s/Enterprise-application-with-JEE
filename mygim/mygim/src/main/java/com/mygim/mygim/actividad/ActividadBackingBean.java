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
public class ActividadBackingBean implements Serializable {

    int actividadId;
    String email;
    String nombre;
    String nombresala;
    int precio;
    String dia;
    String horacomienzo;
    String horafin;

    String descripcion;
    int vacantes;

    public void setActividadBackingBean(String email,int actividadId, String nombre, String nombresala, int precio, String dia, String hi, String hf, String descripcion, int vacantes) {
        
        this.email = email;
        this.actividadId = actividadId;
        this.nombre = nombre;
        this.nombresala = nombresala;
        this.precio = precio;
        this.dia = dia;
        this.horacomienzo = hi;
        this.horafin = hf;
        this.descripcion = descripcion;
        this.vacantes = vacantes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoracomienzo() {
        return horacomienzo;
    }

    public void setHoracomienzo(String horacomienzo) {
        this.horacomienzo = horacomienzo;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombresala() {
        return nombresala;
    }

    public void setNombresala(String nombresala) {
        this.nombresala = nombresala;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.actividad;

import com.mygim.mygim.entities.Actividad;
import com.mygim.mygim.entities.Apuntar;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@RequestScoped
public class ActividadesApuntadasBean extends ActividadGeneric {

 
    @Override
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.apuntar");
        this.filteredActividades = getFromDataActividades();
        this.actividadesList = this.filteredActividades;
    }

    @Override
    @PreDestroy

    public void destroy() {
        client.close();
    }

    @Override
    public String getEmail() {
        FacesContext context = FacesContext.getCurrentInstance();
        return ((HttpServletRequest) context.getExternalContext().getRequest()).getUserPrincipal().getName();
    }

    @Override
    public List<Actividad> getFromDataActividades() {
        List<Actividad> listActs = new ArrayList();
        for (Apuntar r : em.createNamedQuery("Apuntar.findByEmail", Apuntar.class).setParameter("email", getEmail()).getResultList()) {
            listActs.add(em.createNamedQuery("Actividad.findByIdactividad", Actividad.class).setParameter("idactividad", r.getIdactividad()).getSingleResult());
        }
        return listActs;
    }

    public String deleteSelected() {

        if (selectedActividad != null) {

            for (Apuntar a : em.createNamedQuery("Apuntar.findAll", Apuntar.class).getResultList()) {
                if (a.getEmail().equals(getEmail()) && selectedActividad.getIdactividad() == a.getIdactividad()) {
                    target.path("{idactividad}").resolveTemplate("idactividad", a.getIdapuntar())
                            .request().delete();
                }
            }
            this.filteredActividades = getFromDataActividades();
            this.actividadesList = this.filteredActividades;
        }
        return "actividades";
    }

    @Override
    public List<Actividad> getFilteredActividades() {
        return filteredActividades;
    }

    @Override
    public void setFilteredActividades(List<Actividad> filteredActividades) {
        this.filteredActividades = filteredActividades;
    }

    @Override
    public Actividad[] getSelectedActividades() {
        return selectedActividades;
    }

    @Override
    public void setSelectedActividades(Actividad[] selectedActividades) {
        this.selectedActividades = selectedActividades;
    }

    @Override
    public List<Actividad> getActividadesList() {
        return actividadesList;
    }

    @Override
    public void setActividadesList(List<Actividad> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @Override
    public Actividad getSelectedActividad() {
        return selectedActividad;
    }

    @Override
    public void setSelectedActividad(Actividad selectedActividad) {
        this.selectedActividad = selectedActividad;
    }

    @Override
    public String[] getSalas() {
        return salas;
    }

    @Override
    public void setSalas(String[] salas) {
        this.salas = salas;
    }

}

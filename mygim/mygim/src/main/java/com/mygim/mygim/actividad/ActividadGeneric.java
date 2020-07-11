/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.actividad;

import com.mygim.mygim.entities.Actividad;
import com.mygim.mygim.entities.Apuntar;
import com.mygim.mygim.entities.Sala;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author JavierSanchezGozalo
 */
public abstract class ActividadGeneric {

    List<Actividad> filteredActividades;
    Actividad[] selectedActividades;
    List<Actividad> actividadesList;
    Actividad selectedActividad;
    String[] salas;

    Client client;

    WebTarget target;
    @Inject
    ActividadBackingBean bean;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.actividad");
        this.actividadesList = getFromDataActividades();
        filteredActividades = this.actividadesList;
        this.selectedActividades = this.actividadesList.toArray(new Actividad[this.actividadesList.size()]);
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public List<Actividad> getFromDataActividades() {
        List<Actividad> idsact = new ArrayList();
        for (Apuntar s : em.createNamedQuery("Apuntar.findByEmail", Apuntar.class).setParameter("email", getEmail()).getResultList()) {
            idsact.add(findByIdActividad(s.getIdactividad()));
        }
        return idsact;
    }

    public Actividad findByIdActividad(Integer id) {
        return em.createNamedQuery("Actividad.findByIdactividad", Actividad.class).setParameter("idactividad", id).getSingleResult();
    }

    public void onRowSelect(SelectEvent<Actividad> event) {
        selectedActividad = event.getObject();
        setBean(selectedActividad);
    }

    public void onRowUnselect(UnselectEvent<Actividad> event) {
        selectedActividad = null;
    }

    public String getEmail() {
        FacesContext context = FacesContext.getCurrentInstance();
        return ((HttpServletRequest) context.getExternalContext().getRequest()).getUserPrincipal().getName();
    }

    public Actividad getActividad() {
        return target.path("{actividadId}").resolveTemplate("actividadId", bean.getActividadId()).request().get(Actividad.class);
    }

    public String[] getAllSalas() {
        List<String> salas = new ArrayList();
        for (Sala s : em.createNamedQuery("Sala.findAll", Sala.class).getResultList()) {
            salas.add(s.getNombresala());
        }
        return salas.toArray(new String[salas.size()]);
    }

    public int findPuestosByNombresala(String nombresala) {
        Sala sala = em.createNamedQuery("Sala.findByNombresala", Sala.class).setParameter("nombresala", nombresala).getSingleResult();
        return sala.getPuestos();
    }

    public Actividad getBeanValues() {
        Actividad act = new Actividad();
        act.setNombre(bean.getNombre());
        act.setDia(bean.getDia());
        act.setDescripcion(bean.getDescripcion());
        act.setHoracomienzo(bean.getHoracomienzo());
        act.setHorafin(bean.getHorafin());
        act.setNombresala(bean.getNombresala());
        act.setVacantes(findPuestosByNombresala(bean.getNombresala()));
        act.setPrecio(bean.getPrecio());
        act.setEmail(bean.getEmail());
        return act;
    }

    public List<Actividad> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividad> actividadesList) {
        this.actividadesList = actividadesList;
    }

    public void setBean(Actividad act) {
        bean.setActividadBackingBean(act.getEmail(),act.getIdactividad(), act.getNombre(), act.getNombresala(), act.getPrecio(), act.getDia(), act.getHoracomienzo(), act.getHorafin(), act.getDescripcion(), act.getVacantes());
    }

    public Actividad[] getSelectedActividades() {
        return selectedActividades;
    }

    public void setSelectedActividades(Actividad[] selectedActividades) {
        this.selectedActividades = selectedActividades;
    }

    public Actividad getSelectedActividad() {
        return selectedActividad;
    }

    public void setSelectedActividad(Actividad selectedActividad) {
        this.selectedActividad = selectedActividad;
    }

    public String[] getSalas() {
        return salas;
    }

    public void setSalas(String[] salas) {
        this.salas = salas;
    }

    public List<Actividad> getFilteredActividades() {
        return filteredActividades;
    }

    public void setFilteredActividades(List<Actividad> filteredActividades) {
        this.filteredActividades = filteredActividades;
    }

}

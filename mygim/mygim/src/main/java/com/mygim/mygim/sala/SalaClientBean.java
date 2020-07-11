/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.sala;

import com.mygim.mygim.entities.Actividad;
import com.mygim.mygim.entities.Sala;
import com.mygim.mygim.json.SalaWriter;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@RequestScoped
public class SalaClientBean {

    List<Sala> filteredSalas;
    Sala[] selectedSalas;
    List<Sala> salasList;
    Sala selectedSala;

    Client client;
    WebTarget target;
    WebTarget target1;
    @Inject
    SalaBackingBean bean;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.sala");
        target1 = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.actividad");

        this.filteredSalas = getSalas();
        this.salasList = this.filteredSalas;
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Sala[] getSelectedSalas() {
        return selectedSalas;
    }

    public String addSala() {
        if (getBeanSala().getNombresala().length() > 15) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No es un email valido.", ""));
        } else {
            target.register(SalaWriter.class).request().post(Entity.entity(getBeanSala(), MediaType.APPLICATION_JSON));
        }
        this.filteredSalas = getSalas();
        this.salasList = this.filteredSalas;
        return "salas";
    }

    public Sala getBeanSala() {
        return new Sala(bean.getNombresala(), bean.getPuestos());

    }

    public void setSelectedSalas(Sala[] selectedSalas) {
        this.selectedSalas = selectedSalas;
    }

    public Sala getSala() {
        return target
                .path("{nombresala}")
                .resolveTemplate("nombresala", bean.getNombresala())
                .request()
                .get(Sala.class);
    }

    public List<Sala> getSalas() {
        this.selectedSalas = target.request().get(Sala[].class);
        return Arrays.asList(selectedSalas);
    }

    public Sala getSelectedSala() {
        return selectedSala;
    }

    public void setSelectedSala(Sala selectedSala) {
        this.selectedSala = selectedSala;
    }

    public List<Sala> getSalasList() {
        return salasList;
    }

    public void setSalasList(List<Sala> salasList) {
        this.salasList = salasList;
    }

    public List<Sala> getFilteredSalas() {
        return filteredSalas;
    }

    public void setFilteredSalas(List<Sala> filteredSalas) {
        this.filteredSalas = filteredSalas;
    }

    public List<Actividad> getActividadesByNombresala(String nombresala) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.findByNombresala",
                Actividad.class);
        return query.setParameter("nombresala", nombresala).getResultList();
    }

    public String deleteSala(String nombresala) {
        for (Actividad a : getActividadesByNombresala(nombresala)) {
            target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.sala");
            target1.path("{nombresala}").resolveTemplate("nombresala", nombresala).request().delete();
        }
        return "salas";
    }

    public int getIndexColumn(String name) {

        switch (name) {
            case "Nombre sala":
                return 0;
            case "Puestos":
                return 1;
            default:
                return 0;
        }
    }

    public String deleteSelected() {
        if (selectedSalas != null) {
            for (Sala s : selectedSalas) {
                deleteSala(s.getNombresala());
                target.path("{nombresala}").resolveTemplate("nombresala", s.getNombresala())
                        .request().delete();
            }
        }
        this.filteredSalas = getSalas();
        this.salasList = getSalas();
        return "salas";
    }

    public void changeByNombreSala(String nombresala, String antiguanombresala) {
        for (Sala s : filteredSalas) {
            if (nombresala.equals(s.getNombresala())) {
                target.path("{nombresala}")
                        .resolveTemplate("nombresala", antiguanombresala)
                        .request()
                        .delete();
                target.register(SalaWriter.class)
                        .request()
                        .post(Entity.entity(s, MediaType.APPLICATION_JSON));
            }
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            if (getIndexColumn(event.getColumn().getHeaderText()) == 0) {
                changeByNombreSala((String) newValue, (String) oldValue);
            } else {
                changeByNombreSala(event.getRowKey(), event.getRowKey());
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda cambiada", "Viejo valor: " + oldValue + ", Nuevo valor:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void checkNombresala(ComponentSystemEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInput = (UIInput) components.findComponent("nombresala");
        boolean flag = false;
        String id = uiInput.getLocalValue() == null ? "" : uiInput.getLocalValue().toString();

        for (Sala s : getSelectedSalas()) {
            if (s.getNombresala().equals(id)) {
                flag = true;
                FacesMessage msg = new FacesMessage("Ya existe una sala con ese nombre");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(uiInput.getClientId(), msg);
                facesContext.renderResponse();
            }

        }
    }

}

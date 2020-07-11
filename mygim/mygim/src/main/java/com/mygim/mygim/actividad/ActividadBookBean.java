/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.actividad;

import com.mygim.mygim.entities.Actividad;
import com.mygim.mygim.entities.Apuntar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@RequestScoped
public class ActividadBookBean extends ActividadGeneric {

    @Override
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.actividad");
        this.actividadesList = getFromDataActividades();
        filteredActividades = this.actividadesList;
        this.salas = getAllSalas();
    }

    @Override
    public Actividad getBeanValues() {
        Actividad act = new Actividad();
        act.setIdactividad(bean.getActividadId());
        act.setNombre(bean.getNombre());
        act.setDia(bean.getDia());
        act.setDescripcion(bean.getDescripcion());
        act.setHoracomienzo(bean.getHoracomienzo());
        act.setHorafin(bean.getHorafin());
        act.setNombresala(bean.getNombresala());
        act.setVacantes(bean.getVacantes());
        act.setPrecio(bean.getPrecio());
        act.setEmail(bean.getEmail());
        return act;
    }

    @Override
    public List<Actividad> getFromDataActividades() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        List<Actividad> idsSinApuntar = new ArrayList();
        List<Integer> idsApuntadas = new ArrayList();
        for (Apuntar p : em.createNamedQuery("Apuntar.findByEmail", Apuntar.class).setParameter("email", getEmail()).getResultList()) {
            idsApuntadas.add(p.getIdactividad());
        }
        for (Actividad a : em.createNamedQuery("Actividad.findAll", Actividad.class).getResultList()) {
            String[] dia1 = a.getDia().split("-");
            String[] hora = a.getHoracomienzo().split("-");
            if (!idsApuntadas.contains(a.getIdactividad())) {
                if (Integer.parseInt(dia1[2]) >= now.getYear()) {
                    if (Integer.parseInt(dia1[1]) == now.getMonthValue()) {
                        if (Integer.parseInt(dia1[0]) > now.getDayOfMonth()) {
                            idsSinApuntar.add(a);
                        } else if (Integer.parseInt(dia1[0]) == now.getDayOfMonth()) {
                            if (Integer.parseInt(hora[0]) > now.getHour() && Integer.parseInt(hora[1]) > now.getMinute()) {
                                idsSinApuntar.add(a);
                            }
                        }
                    } else if (Integer.parseInt(dia1[1]) > now.getMonthValue()) {
                        idsSinApuntar.add(a);
                    }
                }
            }
        }
        return idsSinApuntar;
    }

    public ActividadBackingBean bean() {
        return bean;
    }

    public String createSell() {
        if (selectedActividad == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Niguna actividad seleccionada.", ""));
            return "";
        } else {
            return "details";
        }

    }

    public String apuntar() {

        bean.setVacantes(bean.getVacantes() - 1);
        target.path(Integer.toString(bean.getActividadId())).request().put(Entity.entity(getBeanValues(), MediaType.APPLICATION_JSON), Actividad.class);

        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.apuntar");
        Apuntar apuntado = new Apuntar();
        apuntado.setEmail(getEmail());
        apuntado.setIdactividad(bean.getActividadId());
        apuntado.setIdapuntar(Integer.BYTES);
        target.register(Apuntar.class).request().post(Entity.entity(apuntado, MediaType.APPLICATION_JSON));
        this.actividadesList = getFromDataActividades();
        filteredActividades = this.actividadesList;
        return "detalles";

    }

    //Métodos del action pop up
    public void handleClose(CloseEvent event) {
        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
    }

    public void handleMove(MoveEvent event) {
        addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}

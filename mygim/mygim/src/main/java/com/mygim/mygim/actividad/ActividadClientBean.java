/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.actividad;

import com.mygim.mygim.entities.Actividad;
import com.mygim.mygim.entities.Ventas;
import com.mygim.mygim.json.ActividadWriter;
import com.mygim.mygim.batch.SalesWriter;
import com.mygim.mygim.entities.Apuntar;
import com.mygim.mygim.entities.Sala;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@RequestScoped
public class ActividadClientBean extends ActividadGeneric {

    String nuevaActividad;

    @PersistenceContext
    private EntityManager em;

    @Override
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.actividad");
        this.actividadesList = getFromDataActividades();
        filteredActividades = this.actividadesList;
        if (this.actividadesList.size() != 0) {
            this.selectedActividad = this.actividadesList.get(0);
        }
        this.salas = getAllSalas();
    }

    @Override
    public List<Actividad> getFromDataActividades() {
        return em.createNamedQuery("Actividad.findByEmail", Actividad.class).setParameter("email", getEmail()).getResultList();
    }

    public String addNuevaActividad(String nombre) {
        Actividad a = new Actividad();
        a.setNombre(nombre);
        a.setDescripcion("Sin descripción.");
        a.setDia("10-07-2019");
        a.setEmail(getEmail());
        a.setHoracomienzo("23:58");
        a.setHorafin("23:59");
        a.setPrecio(0);
        a.setVacantes(0);
        a.setIdactividad(Integer.MAX_VALUE);
        a.setNombresala(getAllSalas()[0]);

        target.register(ActividadWriter.class).request().post(Entity.entity(a, MediaType.APPLICATION_JSON));
        return "actividades";
    }

    public String addSelected() {
        if (nuevaActividad != null) {
            if (nuevaActividad.length() > 15) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No es un email valido.", ""));
            } else {
                addNuevaActividad(nuevaActividad);
            }
        }
        this.filteredActividades = getFromDataActividades();
        this.actividadesList = this.filteredActividades;
        return "actividades";
    }

    public void deleteApuntadosByActividad(Integer idactividad) {

        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.apuntar");
        Apuntar[] ps = target.request().get(Apuntar[].class);
        if (ps != null) {
            for (Apuntar p : ps) {
                if (p.getIdactividad() == idactividad) {
                    target.path("{idapuntar}").resolveTemplate("idapuntar", p.getIdapuntar()).request().delete();
                }
            }
        }
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.actividad");
    }

    public String deleteSelected() {
        if (selectedActividad != null) {
            target.path("{idactividad}").resolveTemplate("idactividad", selectedActividad.getIdactividad())
                    .request().delete();
            deleteApuntadosByActividad(selectedActividad.getIdactividad());
        }
        this.filteredActividades = getFromDataActividades();
        this.actividadesList = getFromDataActividades();
        return "actividades";
    }

    public void onCellEdit(CellEditEvent event) {

        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            updateActividad(selectedActividad.getIdactividad());
            if (updateActividad(Integer.parseInt(event.getRowKey()))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda cambiada", "Viejo valor: " + oldValue + ", Nuevo valor:" + newValue));
            }
        }
        this.filteredActividades = getFromDataActividades();
        this.actividadesList = this.filteredActividades;
    }

    public boolean updateActividad(Integer id) {
        boolean flag = false;
        for (Actividad actividad : filteredActividades) {
            if (Objects.equals(id, actividad.getIdactividad())) {
                if (Integer.parseInt(actividad.getHorafin().split(":")[0]) < Integer.parseInt(actividad.getHoracomienzo().split(":")[0])) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hora incorrecta", "Hora inferior a la inicial"));
                } else if (Integer.parseInt(actividad.getHorafin().split(":")[0]) == Integer.parseInt(actividad.getHoracomienzo().split(":")[0])) {
                    if (Integer.parseInt(actividad.getHorafin().split(":")[1]) <= Integer.parseInt(actividad.getHoracomienzo().split(":")[1])) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hora incorrecta", "Minutos inferiores al inicial"));
                    }
                } else {
                    for (Sala s : em.createNamedQuery("Sala.findAll", Sala.class).getResultList()) {
                        if (s.getNombresala().equals(actividad.getNombresala())) {
                            if (s.getPuestos() < actividad.getVacantes()) {
                                flag = true;
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor incorrecto", "La sala no tiene suficientes puestos"));
                            }
                        }
                    }
                    if (flag == true) {
                        flag = false;
                    } else {
                        flag = true;
                        target.path(Integer.toString(actividad.getIdactividad())).request().put(Entity.entity(actividad, MediaType.APPLICATION_JSON), Actividad.class);
                    }
                }
            }
        }
        return flag;
    }

    public String addVentaDeActividad() {
        List<Ventas> ventas = new ArrayList();
        for (Actividad actividad : filteredActividades) {
            Ventas vent = new Ventas();
            vent.setNombreactividad(actividad.getNombre() + actividad.getDia());
            vent.setVenta(actividad.getPrecio() * (findPuestosByNombresala(actividad.getNombresala()) - actividad.getVacantes()));
            target.path("{idactividad}").resolveTemplate("idactividad", actividad.getIdactividad())
                    .request().delete();
            deleteApuntadosByActividad(actividad.getIdactividad());
            ventas.add(vent);
        }
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.ventas");
        for (Ventas vent : ventas) {
            target.register(SalesWriter.class).request().post(Entity.entity(vent, MediaType.APPLICATION_JSON));
        }
        try {
            updateVentasWithCsv();
        } catch (IOException e) {
            System.out.println("Exc no se guardo " + e);
        }
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.actividad");
        this.filteredActividades = getFromDataActividades();
        this.actividadesList = this.filteredActividades;

        return "actividades";
    }

    public void updateVentasWithCsv() throws IOException {
        List<Ventas> vents = em.createNamedQuery("Ventas.findAll", Ventas.class).getResultList();
        List<String> rows = new ArrayList();
        for (Ventas v : vents) {
            rows.add(v.getIdcuentas() + "," + v.getNombreactividad() + "," + v.getVenta());
        }
        File file = new File("sales.csv");
        file.setWritable(true);
        file.setReadable(true);
        FileWriter csvWriter = new FileWriter(file);
        csvWriter.append("");
        for (String rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ventas añadidas.", " Guardadas como copia."));
        csvWriter.flush();
        csvWriter.close();
    }

    public String getNuevaActividad() {
        return nuevaActividad;
    }

    public void setNuevaActividad(String nuevaActividad) {
        this.nuevaActividad = nuevaActividad;
    }
}

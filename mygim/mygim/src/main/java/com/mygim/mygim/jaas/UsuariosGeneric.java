/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.jaas;

import com.mygim.mygim.entities.Usuario;
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
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author JavierSanchezGozalo
 */
public abstract class UsuariosGeneric {

    List<Usuario> filteredUsuarios;
    Usuario[] selectedUsuarios;
    List<Usuario> usuariosList;
    Usuario selectedUsuario;
    String[] salas;
    String nuevoEmail;

    Client client;

    WebTarget target;
    @Inject
    UserEJB userejb;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target
                = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.usuario");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public String getEmail() {
        FacesContext context = FacesContext.getCurrentInstance();
        return ((HttpServletRequest) context.getExternalContext().getRequest()).getUserPrincipal().getName();
    }

    public List<Usuario> getFilteredUsuarios() {
        return filteredUsuarios;
    }

    public void onCellEdit(CellEditEvent event) {
    }

    public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
        this.filteredUsuarios = filteredUsuarios;
    }

    public Usuario[] getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public List<Usuario> getFromDataEntrenadores() {
        return null;
    }

    public void setSelectedUsuarios(Usuario[] selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }

    public List<Usuario> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuario> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String[] getSalas() {
        return salas;
    }

    public void setSalas(String[] salas) {
        this.salas = salas;
    }

    public String getNuevoEmail() {
        return nuevoEmail;
    }

    public void setNuevoEmail(String nuevoEmail) {
        this.nuevoEmail = nuevoEmail;
    }

}

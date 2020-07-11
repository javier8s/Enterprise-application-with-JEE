/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.jaas;

import com.mygim.mygim.entities.Grupousuario;
import com.mygim.mygim.entities.Usuario;
import com.mygim.mygim.json.SalaWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@RequestScoped
public class EntrenadorAdminBean extends UsuariosGeneric {

    @Override
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target
                = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.usuario");
        this.filteredUsuarios = getFromDataEntrenadores();
        this.usuariosList = this.filteredUsuarios;
        this.selectedUsuario = this.usuariosList.get(0);
        
        nuevoEmail = "";
    }

    @Override
    public List<Usuario> getFromDataEntrenadores() {
        
         List<Usuario> users = em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
        
        List<Usuario> entrenadores = new ArrayList<>();
        List<String> emails = getFromDataGruposEntrenadores();
        for (Usuario u : users) {
            if(u!=null){
            if(emails.contains(u.getEmail()))
                entrenadores.add(u);
            }

        }
        return entrenadores;
    }

    public List<String> getFromDataGruposEntrenadores() {
        List<Grupousuario> grupousuario = em.createNamedQuery("Grupousuario.findByNombregrupo",
                Grupousuario.class).setParameter("nombregrupo", "entrenador").getResultList();
        ArrayList<String> entrenadores = new ArrayList();
        for (int i = 0; i < grupousuario.size(); i++) {
            entrenadores.add(grupousuario.get(i).getEmail());
        }
        return entrenadores;
    }

    public void changeByEmail(String email, String antiguoemail) {
        for (Usuario user : filteredUsuarios) {
            if (email.equals(user.getEmail())) {
                target.request()
                        .put(Entity.entity(user, MediaType.APPLICATION_XML));
                target.path(email)
                        .request()
                        .put(Entity.entity(user, MediaType.APPLICATION_XML));
            }
        }
    }

    @Override
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            changeByEmail(event.getRowKey(), event.getRowKey());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda cambiada", "Viejo valor: " + oldValue + ", Nuevo valor:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        this.filteredUsuarios = getFromDataEntrenadores();
        this.usuariosList = this.filteredUsuarios;
    }

    public String deleteSelected() {
        if (selectedUsuario == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay selección.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "entrenadores";
        }
        deleteEntrenador(selectedUsuario.getEmail());
        usuariosList = getFromDataEntrenadores();
        return "entrenadores";
    }
    
    

    public void deleteEntrenador(String email) {
        target.path("{email}").resolveTemplate("email", email)
                .request().delete();
    }

    public void emailNoValido() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No es un email valido.", "");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public List<String> findEmails() {
        List<String> emails = new ArrayList();
        for (Usuario u : target.request().get(Usuario[].class)) {
            emails.add(u.getEmail());
        }
        return emails;
    }

    public void addEmail(String email, String pass) {
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setApellido1("estandar");
        u.setApellido2("estandar");
        u.setNombre("estandar");
        u.setDni("00000000A");
        u.setPassword(pass);
        u.setTelefono("123456789");
        u.setNacimiento("10-10-2000");
        userejb.createUser(u);
        
    }

    public String addSelected() {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(nuevoEmail.toString()).matches() || nuevoEmail == null || findEmails().contains(nuevoEmail)) {
            emailNoValido();

        } else {
            addEmail(nuevoEmail, "Pass123456789");
        }
         usuariosList = getFromDataEntrenadores();
        return "entrenadores";
    }

    public void onRowSelect(SelectEvent<Usuario> event) {
        selectedUsuario = event.getObject();
    }

    public void onRowUnselect(UnselectEvent<Usuario> event) {
        selectedUsuario = null;
    }

}

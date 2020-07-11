/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.jaas;

import com.mygim.mygim.entities.Grupousuario;
import com.mygim.mygim.entities.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class EditProfileBean extends UsuariosGeneric {

    String pass;

    @PostConstruct
    @Override
    public void init() {
        usuariosList = new ArrayList();
        client = ClientBuilder.newClient();
        target
                = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.usuario");
        this.selectedUsuario = getSelectedUsuario();
        System.out.println("EMAIL " + this.selectedUsuario);
        pass = this.selectedUsuario.getPassword();
        this.selectedUsuario.setPassword("");
        usuariosList.add(selectedUsuario);
    }

    @Override
    public Usuario getSelectedUsuario() {

        return target.path("{email}").resolveTemplate("email", getEmail())
                .request()
                .get(Usuario.class);
    }

    public String adios() {

        Logger log = Logger.getLogger(RegisterView.class.getName());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Fallo durante el proceso de logout!", e);
        }
        borrarUsuario(this.usuariosList.get(0).getEmail());
        return "/index?faces-redirect=true";
    }

    public void borrarUsuario(String email) {
        Grupousuario g = em.createNamedQuery("Grupousuario.findByEmail",
                Grupousuario.class).setParameter("email", email).getSingleResult();
        target.path("{email}").resolveTemplate("email", email)
                .request().delete();
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.grupousuario");
        target.path("{grupousuario}").resolveTemplate("grupousuario", g.getIdgrupo())
                .request().delete();
        target = client.target("http://localhost:8080/mygim/webresources/com.mygim.mygim.entities.usuario");

    }

    public void deleteAndCreate(String email, String oldEmail) {

    }

    @Override
    public void onCellEdit(CellEditEvent event) {

        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            if (event.getColumn().getHeaderText().equals("Contraseña")) {
                try {
                    this.usuariosList.get(0).setPassword(AuthenticationUtils.encodeSHA256((String) newValue));
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EditProfileBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(EditProfileBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.usuariosList.get(0).setPassword(pass);
            }

            if (event.getColumn().getHeaderText().equals("Email")) {
                deleteAndCreate((String) newValue, (String) oldValue);

            } else {
                String email = this.usuariosList.get(0).getEmail();
                Usuario e = this.usuariosList.get(0);
                target.request()
                        .put(Entity.entity(e, MediaType.APPLICATION_XML));
                target.path(email)
                        .request()
                        .put(Entity.entity(e, MediaType.APPLICATION_XML));

            }

            if (!event.getColumn().getHeaderText().equals("Contraseña")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda cambiada", "Viejo valor: " + oldValue + ", Nuevo valor:" + newValue));
            }
        }
        this.selectedUsuario = getSelectedUsuario();

    }

}

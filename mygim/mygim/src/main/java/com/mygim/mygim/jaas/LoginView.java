/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.jaas;

import com.mygim.mygim.entities.Usuario;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@SessionScoped
public class LoginView implements Serializable {

    private String pass;
    private String email;
    private String password;
    private static Logger log = Logger.getLogger(RegisterView.class.getName());
    @Inject
    private UserEJB userEJB;
    private Usuario usuario;
    @PersistenceContext
    private EntityManager em;

    public Usuario getAuthenticatedUser() {
        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean loged() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.isUserInRole("cliente") || request.isUserInRole("admin") || request.isUserInRole("entrenador")) {
            return false;
        }
        return true;

    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login incorrecto!", null));
            return "login";
        }
        this.usuario = userEJB.findByEmail(request.getUserPrincipal().getName());
        if (request.isUserInRole("cliente")) {
            return "/cliente/privatepage?faces-redirect=true";
        } else if (request.isUserInRole("entrenador")) {
            return "/entrenador/privatepage?faces-redirect=true";
        } else if (request.isUserInRole("admin")) {
            return "/admin/privatepage?faces-redirect=true";
        } else {
            return "login";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.usuario = null;
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Fallo durante el proceso de logout!", e);
        }
        return "/index?faces-redirect=true";
    }

    public String editProfile() {
        if (pass != null) {
            try {
                String p = AuthenticationUtils.encodeSHA256(pass);
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                String passR = em.createNamedQuery("Usuario.findByEmail", Usuario.class).setParameter("email", request.getUserPrincipal().getName()).getSingleResult().getPassword();
                if (p.equals(passR)) {
                    return "/edit/profile.xhtml";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Contraseña no valida");
        FacesContext.getCurrentInstance().addMessage(null, message);

        return "";
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.jaas;

import com.mygim.mygim.entities.Usuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@SessionScoped
public class RegisterView implements Serializable {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private String email;
    private String telefono;
    private String password;
    private String confirmPassword;
    private Date nacimiento;

    private static Logger log = Logger.getLogger(RegisterView.class.getName());
    @Inject
    private UserEJB userEJB;

    
    @PersistenceContext
    private EntityManager em;
    
    public Date getNacimiento() {
        return nacimiento;
    }

    
    public String getNacimientoToString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String nacimientoAsString = df.format(nacimiento);

        return nacimientoAsString;
    }

    
    
    
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void validatePassword(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();
// Que required="true" se encargue de esto. https://www.logicbig.com/tutorials/misc/primefaces/date-range-picker.html
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Las contraseñas no coinciden");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputPassword.getClientId(), msg);
            facesContext.renderResponse();
        }
        UIInput uiInputEmail = (UIInput) components.findComponent("email");
        String email = uiInputEmail.getLocalValue() == null ? "" : uiInputEmail.getLocalValue().toString();
        if (userEJB.findByEmail(email) != null) {
            FacesMessage msg = new FacesMessage("Ya existe un usuario con ese email");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputPassword.getClientId(), msg);
            facesContext.renderResponse();
        }
    } 
    
    
    public String register() {
        Usuario usuario = new Usuario(email, dni, nombre, apellido1, apellido2,getNacimientoToString(), telefono, password);
        
        
        userEJB.createUser(usuario);
        log.info("Nuevo usuario creado con e-mail: " + email + " y nombre: "
                + nombre);
        return "regok";
    }
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author JavierSanchezGozalo
 */
@FacesValidator("len255Validator")
public class Len255Validator  implements Validator {
  
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        if ((value.toString()).length() > 255) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", value + " excede el tamaño 50."));
        }
    }
}
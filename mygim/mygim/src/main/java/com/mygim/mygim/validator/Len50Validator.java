/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@FacesValidator("len50Validator")
public class Len50Validator  implements Validator {
  
    
    Pattern p = Pattern.compile("[ 0-9A-Za-zñÑáéíóúÁÉÍÓÚ]{1,50}");
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        Matcher c = p.matcher(value.toString());
        if ((value.toString()).length() > 50  || !c.matches())  {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", value + " excede el tamaño 50."));
        }
    }
}
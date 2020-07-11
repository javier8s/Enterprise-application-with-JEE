/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.validator;

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
@FacesValidator("dateValidator")
public class DateValidator  implements Validator {
    
      
    private static final String DNI_PATTERN = "^[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
    private Pattern pattern;

    public DateValidator() {
        pattern = Pattern.compile(DNI_PATTERN);
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        if (!pattern.matcher(value.toString()).matches()) {
            
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación", value + " no es una fecha válida."));
        }
        
    }
}
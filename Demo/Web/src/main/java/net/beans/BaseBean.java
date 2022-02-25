package net.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by PhungHop on 10:55 AM - 4/26/2021.
 */
public abstract class BaseBean {

    public void addValidateError(String errorMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                errorMessage, null));
    }

    public void addError(String errorMessage) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                errorMessage, null));
    }

    public void addMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                message, null));
    }

}

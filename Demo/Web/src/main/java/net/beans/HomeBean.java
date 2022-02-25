package net.beans;

import org.apache.commons.validator.GenericValidator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by PhungHop on 10:52 AM - 4/26/2021.
 */
@ManagedBean(name = "Home")
@ViewScoped
public class HomeBean extends BaseBean {
    //CONST=============================================================================================================

    //VARIABLE==========================================================================================================
    private String username;
    private String password;

    //GET-SET===========================================================================================================
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //FUNCTION==========================================================================================================
    public String doLogin() {
        try {
            if (validate()) {
                if (username.equals("admin") && password.equals("123123")) {
                    addMessage("Đăng nhập thành công");
                    return "home.page";
                } else {
                    addValidateError("Đăng nhập thất bại, vui lòng kiểm tra lại");
                }
            }
        } catch (Exception ex) {
            addError("Có lỗi xảy ra: " + ex.getMessage());
        }
        return "";
    }

    public String doDirect() {
        return "kiet?faces-redirect=true";
    }

    //VALIDATE==========================================================================================================
    private boolean validate() {
        if (GenericValidator.isBlankOrNull(username)) {
            addValidateError("Vui lòng nhập username");
            return false;
        }
        if (GenericValidator.isBlankOrNull(password)) {
            addValidateError("Vui lòng nhập password");
            return false;
        }
        return true;
    }
    //EVENT-LISTENER====================================================================================================

    //OTHER=============================================================================================================
}

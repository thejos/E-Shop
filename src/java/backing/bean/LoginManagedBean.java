package backing.bean;

import ejb.LoginSessionBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.entities.User;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
@ManagedBean(name = "loginBackingBean", eager = true)
@SessionScoped
public class LoginManagedBean implements Serializable {

    @EJB
    private LoginSessionBeanLocal loginSessionBean;

    private String username;
    private String password;
    private String message;
    private boolean loggedIn;
    private boolean administrator;
    private User user;

    public LoginManagedBean() {
    }

    public User getUser() {
    return user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login() {
        this.user = loginSessionBean.login(username, password);
        if (user != null) {
            if (user.getUsername().equals("admin")) {
                administrator = true;
            } else {
                administrator = false;
            }
            loggedIn = true;
            this.message = user.getName() + ", welcome to E-Shop!";
            return "index";
        } else {
            loggedIn = false;
            this.message = "Invalid data input.";
            return "login";
        }
    }

    public String logout() {
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

}

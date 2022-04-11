
package backing.bean;


import ejb.RegisterSessionBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */

@ManagedBean(name = "registerManagedBean", eager = true)
@ViewScoped
public class RegisterManagedBean implements Serializable{

    @EJB
    private RegisterSessionBeanLocal registerSessionBeanLocal;
    
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String message;
    private boolean userCreated;
    
    public RegisterManagedBean() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }
    
    public String register(){
        
        userCreated = registerSessionBeanLocal.register(firstName, lastName, username, password);
        
        if(userCreated){
            message = "Your account has been created. Please log in.";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("okMessage", message);
            return "login";   
        }
        
        //message = "Username already taken";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("failMessage", "Username already taken");
        return "register";
    }

    
}

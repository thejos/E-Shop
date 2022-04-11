package ejb;

import javax.ejb.Local;
import model.entities.User;

@Local
public interface LoginSessionBeanLocal {
    public User login(String username, String password);
}

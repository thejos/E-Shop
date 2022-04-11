package ejb;


import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entities.User;
import secure.HashGenerator;


@Stateless
public class RegisterSessionBean implements RegisterSessionBeanLocal {

    @PersistenceContext(unitName = "EShopDemoPU")
    private EntityManager entityManager;

    @Override
    public boolean register(String name, String surname, String username, String password) {

        try {
            Query query = entityManager.createNamedQuery("User.findByUsername");
            query.setParameter("username", username);

            List<User> users = query.getResultList();
            User user = new User();
            
            if (users.isEmpty()) {
                
                //Process plain text password
                String hashedData = new HashGenerator().toSHA2(password);
                
                user.setName(name);
                user.setSurname(surname);
                user.setUsername(username);
                user.setPassword(hashedData);
                entityManager.persist(user);
                return true;

            } else {
                return false;
            }

        } catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }
    }//register() END

}

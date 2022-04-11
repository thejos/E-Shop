package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entities.User;
import secure.HashGenerator;

@Stateless
public class LoginSessionBean implements LoginSessionBeanLocal {
    
    @PersistenceContext(unitName = "EShopDemoPU")
    private EntityManager entityManager;
    
    
    @Override
    public User login(String username, String password) {
        
        String hashedPassword = new HashGenerator().toSHA2(password);
        
        try{
             Query query = entityManager.createNamedQuery("User.findByUsernameAndPassword");
             query.setParameter("username", username);
             query.setParameter("password", hashedPassword);
             return (User)query.getSingleResult();
        }catch(Exception exc){
            exc.printStackTrace();
            return null;
        }
    }
}

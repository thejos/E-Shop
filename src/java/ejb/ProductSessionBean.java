package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entities.Product;


@Stateless
public class ProductSessionBean implements ProductSessionBeanLocal {
    
    @PersistenceContext(unitName = "EShopDemoPU")
    private EntityManager entityManager;
    
    @Override
    public List<Product> getallProducts() {
        
        try{
            Query query = entityManager.createNamedQuery("Product.findAll");
            return query.getResultList();
        }catch(Exception exc){
            exc.printStackTrace();
            return null;
        }
    }
}

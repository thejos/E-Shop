package ejb;

import java.util.List;
import javax.ejb.Local;
import model.entities.Product;

@Local
public interface ProductSessionBeanLocal {

    public List<Product> getallProducts();
    
}

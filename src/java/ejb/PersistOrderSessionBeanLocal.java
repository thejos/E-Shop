package ejb;

import java.math.BigDecimal;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import model.entities.Product;
import model.entities.Purchase;
import model.entities.User;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 */
@Local
public interface PersistOrderSessionBeanLocal {
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Purchase persistOrder(User user, String firstName, String lastName, String creditCardNumber, BigDecimal priceTotal);
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean persistOrderProduct(int quantity, Product product, Purchase purchase);
    
}

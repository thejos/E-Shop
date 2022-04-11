package ejb;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entities.Product;
import model.entities.Purchase;
import model.entities.PurchaseProduct;
import model.entities.User;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 */
@Stateless
public class PersistOrderSessionBean implements PersistOrderSessionBeanLocal {

    @PersistenceContext(unitName = "EShopDemoPU")
    private EntityManager entityManager;

    @Override
    public Purchase persistOrder(User user, String firstName, String lastName, String creditCardNumber, BigDecimal priceTotal) {

        try {
            Purchase order = new Purchase();
            order.setUserId(user);
            order.setName(firstName);
            order.setSurname(lastName);
            order.setCardNumber(creditCardNumber);
            order.setTotalPrice(priceTotal);

            entityManager.persist(order);
            entityManager.flush();
            return order;
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }//persistOrer() END

    @Override
    public boolean persistOrderProduct(int quantity, Product product, Purchase purchase) {

        try {

            PurchaseProduct orderProduct = new PurchaseProduct();
            orderProduct.setQuantity(quantity);
            orderProduct.setProductId(product);
            orderProduct.setPurchaseId(purchase);

            entityManager.persist(orderProduct);
            return true;
        } catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }
    }//persistOrderProduct() END
    

}

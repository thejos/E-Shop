package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entities.PurchaseProduct;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */
@Stateless
public class PurchaseProductFacade extends AbstractFacade<PurchaseProduct> {

    @PersistenceContext(unitName = "EShopDemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseProductFacade() {
        super(PurchaseProduct.class);
    }

}

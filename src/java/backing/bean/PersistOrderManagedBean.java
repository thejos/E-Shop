package backing.bean;

import backing.bean.model.ShoppingCartItem;
import ejb.PersistOrderSessionBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import model.entities.Purchase;

/**
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 *
 */

@ManagedBean(name = "persistOrderBackingBean", eager = true)
@ViewScoped
public class PersistOrderManagedBean implements Serializable{

    @EJB
    private PersistOrderSessionBeanLocal persistOrderSessionBean;
    
    @ManagedProperty(value = "#{loginBackingBean}")
    private LoginManagedBean loginManagedBean;
    
    @ManagedProperty(value = "#{productManagedBean}")
    private ProductManagedBean productManagedBean;
    
    private String creditCardNumber;
    private boolean orderArchived;
    private boolean orderProductArchived;
    private String message;
    private List<ShoppingCartItem> cartItems = new ArrayList<>();
    private Purchase purchase;
    

    public PersistOrderManagedBean() {
    }
    
    public Purchase getPurchase() {
        return purchase;
    }

    //
    public void setPurchase(Purchase purchase) {    
        this.purchase = purchase;
    }

    //
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    

    public boolean isOrderArchived() {
        return orderArchived;
    }

    public void setOrderArchived(boolean orderArchived) {
        this.orderArchived = orderArchived;
    }

    public boolean isOrderProductArchived() {
        return orderProductArchived;
    }

    public void setOrderProductArchived(boolean orderProductArchived) {
        this.orderProductArchived = orderProductArchived;
    }
    
    
    
    public LoginManagedBean getLoginManagedBean() {
        return loginManagedBean;
    }

    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }

    public ProductManagedBean getProductManagedBean() {
        return productManagedBean;
    }

    public void setProductManagedBean(ProductManagedBean productManagedBean) {
        this.productManagedBean = productManagedBean;
    }
    
    ////
    
    public void persistOrder(){
        purchase = persistOrderSessionBean.persistOrder(loginManagedBean.getUser(), loginManagedBean.getUser().getName(), loginManagedBean.getUser().getSurname(), creditCardNumber, productManagedBean.getGrandTotal());
        
//cartItems = productManagedBean.getShoppingCartItems();
        for(ShoppingCartItem cartItem : productManagedBean.getShoppingCartItems()){
            orderProductArchived = persistOrderSessionBean.persistOrderProduct(cartItem.getQuantity(), cartItem.getProduct(), purchase);
        }
        
        
        if(purchase!=null && orderProductArchived){
            productManagedBean.emptyCart();
            this.message="Your order has been placed. Products will be delivered soon. Thanks for shopping at E-Shop.";
            
        }else{
            this.message="Unexpected error occured.";
        }
        
    }

    
}

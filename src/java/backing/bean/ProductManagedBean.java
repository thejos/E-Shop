package backing.bean;

import backing.bean.model.ShoppingCartItem;


import ejb.ProductSessionBeanLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.entities.Product;

@ManagedBean(name = "productManagedBean", eager = true)
@SessionScoped
public class ProductManagedBean implements Serializable {

    @EJB
    private ProductSessionBeanLocal productSessionBeanLocal;

    private int quantity;
    private int cartItemsQuantity;
    private List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
    
    private BigDecimal grandTotal = BigDecimal.valueOf(0.0);

    public ProductManagedBean() {
    }

    /////////////
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    ////////////////////
    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartItemsQuantity() {
        return cartItemsQuantity;
    }

    public void setCartItemsQuantity(int cartItemsQuantity) {
        this.cartItemsQuantity = cartItemsQuantity;
    }
    

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void addProductToCart(Product product) {

        for (ShoppingCartItem scItem : shoppingCartItems) {
            
            if (product.getProductId() == scItem.getProduct().getProductId()) {
                scItem.setQuantity(scItem.getQuantity() + quantity);
                this.cartItemsQuantity+=quantity;
                
                grandTotal = grandTotal.add(scItem.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity)));
                
                return;
            }
        }
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
        shoppingCartItems.add(shoppingCartItem);
        this.cartItemsQuantity+=shoppingCartItem.getQuantity();
        
        grandTotal = grandTotal.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        
        
    }

    public List<Product> getAllProducts() {

        return productSessionBeanLocal.getallProducts();
    }
    
    public void emptyCart(){
        
        shoppingCartItems.clear();
        this.cartItemsQuantity=0;
        grandTotal = BigDecimal.valueOf(0.0);
        
    }

}

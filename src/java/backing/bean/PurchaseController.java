package backing.bean;

import model.entities.Purchase;
import backing.bean.util.JsfUtil;
import backing.bean.util.JsfUtil.PersistAction;
import ejb.PurchaseFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("purchaseController")
@SessionScoped
public class PurchaseController implements Serializable {

    @EJB
    private ejb.PurchaseFacade ejbFacade;
    private List<Purchase> items = null;
    private Purchase selected;

    public PurchaseController() {
    }

    public Purchase getSelected() {
        return selected;
    }

    public void setSelected(Purchase selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PurchaseFacade getFacade() {
        return ejbFacade;
    }

    public Purchase prepareCreate() {
        selected = new Purchase();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("localization/Bundle").getString("PurchaseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("localization/Bundle").getString("PurchaseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("localization/Bundle").getString("PurchaseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Purchase> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("localization/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("localization/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Purchase getPurchase(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Purchase> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Purchase> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Purchase.class)
    public static class PurchaseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PurchaseController controller = (PurchaseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "purchaseController");
            return controller.getPurchase(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Purchase) {
                Purchase o = (Purchase) object;
                return getStringKey(o.getPurchaseId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Purchase.class.getName()});
                return null;
            }
        }

    }

}

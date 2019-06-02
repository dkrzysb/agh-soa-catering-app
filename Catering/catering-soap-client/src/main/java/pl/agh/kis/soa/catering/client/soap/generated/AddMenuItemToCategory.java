
package pl.agh.kis.soa.catering.client.soap.generated;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addMenuItemToCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addMenuItemToCategory"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="menuItemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="menuItemServingSize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="menuItemPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="menuItemCategoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addMenuItemToCategory", propOrder = {
    "menuItemName",
    "menuItemServingSize",
    "menuItemPrice",
    "menuItemCategoryId"
})
public class AddMenuItemToCategory {

    protected String menuItemName;
    protected int menuItemServingSize;
    protected BigDecimal menuItemPrice;
    protected Long menuItemCategoryId;

    /**
     * Gets the value of the menuItemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMenuItemName() {
        return menuItemName;
    }

    /**
     * Sets the value of the menuItemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMenuItemName(String value) {
        this.menuItemName = value;
    }

    /**
     * Gets the value of the menuItemServingSize property.
     * 
     */
    public int getMenuItemServingSize() {
        return menuItemServingSize;
    }

    /**
     * Sets the value of the menuItemServingSize property.
     * 
     */
    public void setMenuItemServingSize(int value) {
        this.menuItemServingSize = value;
    }

    /**
     * Gets the value of the menuItemPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMenuItemPrice() {
        return menuItemPrice;
    }

    /**
     * Sets the value of the menuItemPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMenuItemPrice(BigDecimal value) {
        this.menuItemPrice = value;
    }

    /**
     * Gets the value of the menuItemCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMenuItemCategoryId() {
        return menuItemCategoryId;
    }

    /**
     * Sets the value of the menuItemCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMenuItemCategoryId(Long value) {
        this.menuItemCategoryId = value;
    }

}

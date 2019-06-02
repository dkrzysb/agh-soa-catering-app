package pl.agh.kis.soa.server.soap.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.math.BigDecimal;

@WebService
public interface CateringService {
    @WebMethod
    void addMenuItemToCategory(@WebParam(name = "menuItemName")String name, @WebParam(name = "menuItemServingSize")int servingSize, @WebParam(name = "menuItemPrice") BigDecimal price, @WebParam(name = "menuItemCategoryId")Long menuCategoryId);
}


package pl.agh.kis.soa.server.soap.implementations;


import pl.agh.kis.soa.catering.server.api.IMenuItemRepository;
import pl.agh.kis.soa.server.soap.interfaces.CateringService;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;

@WebService(endpointInterface = "pl.agh.kis.soa.server.soap.interfaces.CateringService")
public class CateringServiceImpl implements CateringService {
    @EJB(lookup = "java:global/catering-business-logic/MenuItemRepository")
    IMenuItemRepository menuItemRepository;

    @WebMethod
    public void addMenuItemToCategory(String name, int servingSize, BigDecimal price, Long menuCategoryId) {
        menuItemRepository.addMenuItem(name, servingSize, price, menuCategoryId, false);
    }
}

package pl.agh.kis.soa.catering.server.rest.services;

import io.swagger.annotations.Api;
import pl.agh.kis.soa.catering.server.api.IMenuCategoryRepository;
import pl.agh.kis.soa.catering.server.model.MenuCategory;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/menu")
@Api(value = "/menu", description = "Retrieving menu elements")
public class MenuService {
    @EJB(lookup = "java:global/catering-business-logic/MenuCategoryRepository")
    IMenuCategoryRepository menuCategoryRepository;

    @GET
    @Produces({ "text/xml", "application/xml", "application/json" })
    public List<MenuCategory> getAllMenuCategories(@HeaderParam("Accept-Language") String language) {
        if ("en".equals(language)) {
            return getAllMenuCategoriesEN();
        }
        return getAllMenuCategoriesPL();
    }

    @GET
    @Produces({ "text/xml", "application/xml", "application/json" })
    @Path("{menuCategoryId}")
    public MenuCategory getMenuCategoryById(@PathParam("menuCategoryId")Long menuCategoryId) {
        return menuCategoryRepository.getMenuCategoryById(menuCategoryId);
    }

    private List<MenuCategory> getAllMenuCategoriesPL() {
        return menuCategoryRepository.getAllMenuCategories();
    }

    private List<MenuCategory> getAllMenuCategoriesEN() {
        List<MenuCategory> menuCategories = menuCategoryRepository.getAllMenuCategories();

        for(MenuCategory menuCategory : menuCategories)
            menuCategory.setName(menuCategory.getName().toUpperCase());

        return menuCategories;
    }
}

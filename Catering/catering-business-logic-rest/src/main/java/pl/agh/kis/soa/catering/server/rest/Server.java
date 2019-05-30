package pl.agh.kis.soa.catering.server.rest;

import io.swagger.jaxrs.config.BeanConfig;
import pl.agh.kis.soa.catering.server.rest.services.MenuService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Server extends Application {
    public Server() {
        initSwagger();
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();

        classes.add(MenuService.class);
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return classes;
    }

    private void initSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/catering-business-logic-rest/api");
        beanConfig.setResourcePackage(pl.agh.kis.soa.catering.server.rest.services.MenuService.class.getPackage().getName());
        beanConfig.setTitle("CateringApp Swagger");
        beanConfig.setScan(true);
    }
}

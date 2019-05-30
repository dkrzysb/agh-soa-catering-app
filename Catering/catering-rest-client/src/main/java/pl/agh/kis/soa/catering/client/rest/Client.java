package pl.agh.kis.soa.catering.client.rest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int option = 1;
        Long menuCategoryId;

        while(option != 0) {
            System.out.println("\nMENU");
            System.out.println("Select 1 to get all menu categories with items as json");
            System.out.println("Select 2 to get all menu categories with items as xml");
            System.out.println("Select 3 to get menu category with items as json");
            System.out.println("Select 4 to get menu category with items as xml");
            System.out.println("Select 0 to exit");

            option = reader.nextInt();
            switch(option) {
                case 1:
                    getAllMenuCategoriesAsJson();
                    break;
                case 2:
                    getAllMenuCategoriesAsXml();
                    break;
                case 3:
                    System.out.print("Write category id which would you like to display: ");
                    menuCategoryId = reader.nextLong();
                    getMenuCategoryByIdAsJson(menuCategoryId);
                    break;
                case 4:
                    System.out.print("Write category id which would you like to display: ");
                    menuCategoryId = reader.nextLong();
                    getMenuCategoryByIdAsXml(menuCategoryId);
                    break;
            }
        }
    }

    private static void getAllMenuCategoriesAsJson() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/catering-business-logic-rest/api/menu");
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println("\nGet all menu categories json status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void getAllMenuCategoriesAsXml() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/catering-business-logic-rest/api/menu");
        Response response = target.request().accept(MediaType.TEXT_XML).get();
        System.out.println("\nGet all menu categories as xml status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void getMenuCategoryByIdAsJson(Long menuCategoryId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/catering-business-logic-rest/api/menu/" + menuCategoryId);
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        System.out.println("\nGet menu category with id: " + menuCategoryId + " as json status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void getMenuCategoryByIdAsXml(Long menuCategoryId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/catering-business-logic-rest/api/menu/" + menuCategoryId);
        Response response = target.request().accept(MediaType.TEXT_XML).get();
        System.out.println("\nGet menu category with id: " + menuCategoryId + " as xml status: " + response.getStatus());
        System.out.println("Response:");
        printResponse(response);
    }

    private static void printResponse(Response response) {
        String value = response.readEntity(String.class);
        System.out.println(value);
        response.close();
    }
}

package pl.agh.kis.soa.catering.client.soap.client;

import pl.agh.kis.soa.catering.client.soap.generated.CateringService;
import pl.agh.kis.soa.catering.client.soap.generated.CateringServiceImplService;

import java.math.BigDecimal;
import java.util.Scanner;

public class CateringServiceClient {
    public static void main(String[] args) {
        CateringServiceImplService cateringServiceImplService = new CateringServiceImplService();
        CateringService cateringService = cateringServiceImplService.getCateringServiceImplPort();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while(option != 0) {
            System.out.println("Press 1 to add new menu item to specific category");
            System.out.println("Press 0 to exit");

            option = scanner.nextInt();
            if(option == 1) {
                String name;
                int servingSize;
                BigDecimal price;
                Long menuCategoryId;

                System.out.print("Menu item name: ");
                name = scanner.next();

                System.out.print("Menu item serving size: ");
                servingSize = scanner.nextInt();

                System.out.print("Menu item price: ");
                price = scanner.nextBigDecimal();

                System.out.print("Menu item category id: ");
                menuCategoryId = scanner.nextLong();

                cateringService.addMenuItemToCategory(name, servingSize, price, menuCategoryId);
            }
        }
    }
}

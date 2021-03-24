package ua.com.alevel.helpers;

import java.sql.Connection;
import java.util.Scanner;

import static ua.com.alevel.helpers.HelperCategory.methodsCategory;
import static ua.com.alevel.helpers.HelperCategory.suggestCategoryMethod;
import static ua.com.alevel.helpers.HelperOrder.suggestOrderMethod;
import static ua.com.alevel.helpers.HelperProduct.methodsProduct;
import static ua.com.alevel.helpers.HelperProduct.suggestProductMethod;
import static ua.com.alevel.helpers.HelperUser.methodsUser;
import static ua.com.alevel.helpers.HelperUser.suggestUserMethod;

public class HelperApp {
    public static void methodsApp(String method) {
        if (method.equals("Category")) {
            methodsCategory(suggestCategoryMethod());
        } else if (method.equals("Products")) {
           methodsProduct(suggestProductMethod());
        } else if (method.equals("User")) {
            methodsUser((suggestUserMethod()));
        } else if (method.equals("Orders")) {
            methodsApp(suggestOrderMethod());
        } else if (method.equals("Finish")) {
            System.exit(0);
        }
    }


    public static String appSuggestion () {
        System.out.println("Here you can manipulate with objects : Category, Products, User, Orders or you can press Finish to Exit the App");
        System.out.println("Enter the table name you wanna manipulate");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }
}

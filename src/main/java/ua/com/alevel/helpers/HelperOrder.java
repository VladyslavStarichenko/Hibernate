package ua.com.alevel.helpers;

import ua.com.alevel.dao.OrderDao;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class HelperOrder {
    private static final String messageToGetName = "Enter the product Name to create an order";
    private static final String messageToGetUserName = "Enter your Mail to create an order";
    private static final String orderStatus = "Pending";

    public static String suggestOrderMethod() {
        System.out.println("Here you can Create, Read Orders or press Back to return to the main menu");
        System.out.println("Enter the method you wanna do.");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }

    public static void methodsOrders(String method){
        if(method.equals("Read")){
            OrderDao.showOrders();
            methodsOrders(suggestOrderMethod());
        }else if(method.equals("Create")){
            String productNameOrder = getOrderString(messageToGetName);
            String mailOrder = getOrderString(messageToGetUserName);
            OrderDao.createOrder(productNameOrder,mailOrder,orderStatus);
            methodsOrders(suggestOrderMethod());
        }else if(method.equals("Back")){
            HelperApp.methodsApp(HelperApp.appSuggestion());
        }
    }

    private static String getOrderString(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return name;
    }
}

package ua.com.alevel;


import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.dao.ProductDao;
import ua.com.alevel.helpers.HelperCategory;
import ua.com.alevel.helpers.HelperOrder;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;

import static ua.com.alevel.helpers.HelperApp.appSuggestion;
import static ua.com.alevel.helpers.HelperApp.methodsApp;

public class App {
    public static void main(String[] args) {
        methodsApp(appSuggestion());
    }



}

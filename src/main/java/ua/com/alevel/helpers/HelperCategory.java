package ua.com.alevel.helpers;

import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.model.Category;

import java.sql.Connection;
import java.util.Scanner;

public class HelperCategory {

    private static final String messageToCreate = "Enter the Category name to create one";
    private static final String messageToDelete = "Enter the Category name to delete one";
    private static final String messageToUpdate = "Enter the Category name to update one";
    private static final String messageToGetOne = "Enter Category name to show it";

    public static Category createCategoryModel(){
        String categoryName = getNameCategory(messageToCreate);
        Category category = new Category();
        category.setCategoryName(categoryName);
        return category;
    }

    private static String getNameCategory(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return name;
    }


    public static String suggestCategoryMethod() {
        System.out.println("Here you can Create, Read, Update and Delete Categories or press Finish to Exit the App");
        System.out.println("Enter the method you wanna do.");
        Scanner scanner = new Scanner(System.in);
        String method = scanner.nextLine();
        return method;
    }

    public static void methodsCategory(String method){
        if(method.equals("Read")){
            CategoryDao.showCategories();
            methodsCategory(suggestCategoryMethod());
        }else if(method.equals("Update")){

            methodsCategory(suggestCategoryMethod());
        }else if(method.equals("Create")){
            Category category = createCategoryModel();
            CategoryDao.createCategory(category.getCategoryName(),category);
            methodsCategory(suggestCategoryMethod());
        }else if(method.equals("Delete")){
            String categoryToDelete = getNameCategory(messageToDelete);
            CategoryDao.deleteCategory(categoryToDelete);
        }else if(method.equals("Back")){

        }
    }

}

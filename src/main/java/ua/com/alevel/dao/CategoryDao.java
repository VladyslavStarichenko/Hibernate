package ua.com.alevel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Scanner;

public class CategoryDao {



    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    public static void createCategory(String categoryName, Category category) {
        if (checkCategoryExisting(categoryName)) {
            System.out.println("The category with the passed name already exists");
        } else {
            categoryCreation(category);
        }
    }

    public static void categoryCreation(Category category) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkCategoryExisting(String categoryName) {
        List<Category> categoryList = getAllCategories();
        boolean flag = false;
        for (Category category : categoryList) {
            if (category.getCategoryName().equals(categoryName)) {
                return true;
            }
            flag = false;
        }
        return flag;
    }


    public static List<Category> getAllCategories() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Category> query = session.createQuery("  FROM  Category ", Category.class);
            List<Category> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }



    public static void showCategories() {
        List<Category> categoryList = getAllCategories();
        for (Category category : categoryList) {
            System.out.println(category + "\n");
        }
    }

    public static void deleteCategory(String name) {
        if (checkCategoryBeforeDeleting(name) && deleteOrNot()) {
            ProductDao.deleteProductWithCategory(name);
        }
        deletingCategory(name);
    }

    public static void deletingCategory(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Category WHERE categoryName =: name");
            query.setParameter("name", name);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static boolean checkCategoryBeforeDeleting(String name) {
        List<Product> productList = ProductDao.getAllProducts();
        for (Product product : productList) {
            if (product.getCategoryId().getCategoryName().equals(name)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean deleteOrNot() {
        boolean flag = false;
        System.out.println("There are products with category you wanna delete." + "\n" +
                "To perform this action enter 'Yes', to undo - enter 'No'");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        if (response.equals("Yes")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}

package ua.com.alevel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class ProductDao {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public static void main(String[] args) {
        System.out.println(getAllProducts());
    }

    public static void createProduct(Product product){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProductWithCategory(String name){
        try (Session session = sessionFactory.openSession()) {
            CategoryDao categoryDao = new CategoryDao();
            List<Category> categoryList = categoryDao.getAllCategories();
            Category idToDelete = null;
            for (Category category : categoryList) {
                if (category.getCategoryName().equals(name)) {
                    idToDelete = category;
                }
            }
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Product WHERE categoryId =: idToDelete");
            query.setParameter("idToDelete", idToDelete);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static void deleteProduct(Integer id){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Product WHERE productId =: id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Product> query = session.createQuery("  FROM  Product ", Product.class);
            List<Product> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public static List<Product> getProductsByCategoryName(String name) {
        try (Session session = sessionFactory.openSession()) {
            CategoryDao categoryDao = new CategoryDao();
            List<Category> categoryList = categoryDao.getAllCategories();
            Integer id = null;
            for (Category category : categoryList) {
                if (category.getCategoryName().equals(name)) {
                    id = category.getCategoryId();
                }
            }
            session.beginTransaction();
            Query<Product> query = session.createQuery("  FROM  Product WHERE categoryId =: id", Product.class);
            query.setParameter("id", id);
            List<Product> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public static void showProducts(){
        List<Product> categoryList = getAllProducts();
        for(Product product : categoryList){
            System.out.println(product + "\n");
        }
    }

}

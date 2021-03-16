package ua.com.alevel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class ProductDao {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public void createProduct(Product product){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProductWithCategory(String name){
        try (Session session = sessionFactory.openSession()) {
            List<Product> categoryList = getProductsByCategoryName(name);
            Integer idToDelete = null;
            for (Product product : categoryList) {
                idToDelete = product.getProductId();
            }
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Product WHERE productId =: idToDelete");
            query.setParameter("idToDelete", idToDelete);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Product> query = session.createQuery("  FROM  Product ", Product.class);
            List<Product> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public List<Product> getProductsByCategoryName(String name) {
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

    public void showProducts(){
        List<Product> categoryList = getAllProducts();
        for(Product product : categoryList){
            System.out.println(product + "\n");
        }
    }

}

package ua.com.alevel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class CategoryDao {

    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public void createCategory(Category category){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Category> query = session.createQuery("  FROM  Category ", Category.class);
            List<Category> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public void showCategories(){
        List<Category> categoryList = getAllCategories();
        for(Category category : categoryList){
            System.out.println(category + "\n");
        }
    }

    public void deleteCategory(String name){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Category WHERE categoryName =: name");
            query.setParameter("name", name);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}

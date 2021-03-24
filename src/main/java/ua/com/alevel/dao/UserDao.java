package ua.com.alevel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;


public class UserDao {

    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public static void createUser(String userMail, User user, String massage) {
        if (checkUserExisting(userMail)) {
            System.out.println(massage);
        } else {
            userCreation(user);
        }
    }

    public static boolean checkUserExisting(String userMail) {
        List<User> users = getAllUsers();
        boolean flag = false;
        for (User user : users) {
            if (user.getEmail().equals(userMail)) {
                return true;
            }else{
                return false;
            }
        }
        return flag;
    }

    public static void userCreation(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("  FROM  User ", User.class);
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }


    public static void showUser() {
        List<User> categoryList = getAllUsers();
        for (User user : categoryList) {
            System.out.println("\n" + user + "\n");
        }
    }

    public static void deleteUser(Integer id){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE user_id =: id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static void updateUser(int userId, String newName, String newSurName,
                                     Integer newPostCode, String newAddress, String newMail) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("UPDATE User SET name =: newName, last_name =: newSurName," +
                    " address =: newAddress,  post_code =: newPostCode, email =: newMail WHERE id =: userId");
            if(!checkUserExisting(newMail)) {
                query.setParameter("userId", userId);
                query.setParameter("newSurName",newSurName);
                query.setParameter("newPostCode", newPostCode);
                query.setParameter("newName", newName);
                query.setParameter("newAddress", newAddress);
                query.setParameter("newMail", newMail);
                int rows = query.executeUpdate();
                session.getTransaction().commit();
                if(rows > 0){
                    System.out.println("User is successfully updated");
                }
            }else {
                System.out.println("User with same mail is already Exists");
            }
        }
    }



}

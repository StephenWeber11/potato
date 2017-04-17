/*
 * Hard-Coded set of user details (your choice on how to represent
 * User getUser(String email) – returns a user object based on the email
 * List/Collection<User> getUsers() – returns a set of all the users in the hardcoded “database”
 */
package Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import Business.User;
import java.util.ArrayList;
import java.util.List;
//import Data.DBUtil;

/**
 *
 * @author Stephen Weber
 */
public class UserDB {
        
    public static void insert(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
        public static void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(user));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }
    
    public static User getUser(String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u " +
                "WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
            User user = q.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<User> getUsers(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u + "
                + "WHERE EMAIL != null";
        TypedQuery<User> q = em.createQuery(qString,User.class);
        List<User> users;
        try{
            users = q.getResultList();
            if(users == null || users.isEmpty()){
                users = null;
            }
        }finally{
            em.close();
        }
        return users;
    }
    
    public static boolean emailExists(String email) {
        User u = getUser(email);   
        return u != null;
    }
    
    
}

/*
 * Hard-Coded set of study details (your choice on how to represent internally, but must be converted into a list/collection of Study beans when required)
 * Study getStudy(String studyCode) – returns a Study object based on studyCode.
 * List/Collection<Study> getStudies() – returns a set of all the studies in the hardcoded “database”
 * List/Collection<Study> getStudies(String email) – returns a set of all the studies in the hardcoded “database” for the passed-in email
 * addStudy(Study study)
 * updateStudy(String SCode, Study study)
 */
package Data;

import Business.Study;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Stephen Weber
 */
public class StudyDB {
    
    public static void addStudy(Study study) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(study);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateStudy(String sCode, Study study) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(study);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Study study) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(study));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }

    public static Study getStudy(String studyCode) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM Study s " +
                "WHERE s.studyCode = :studyCode";
        TypedQuery<Study> q = em.createQuery(qString, Study.class);
        q.setParameter("studyCode", studyCode);
        try {
            Study study = q.getSingleResult();
            return study;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<Study> getOpenStudies(String status){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM Study s "
                + "WHERE s.status = :status ";
        TypedQuery<Study> q = em.createQuery(qString,Study.class);
        q.setParameter("status",status);
        List<Study> studies;
        try{
            studies = q.getResultList();
            if(studies == null || studies.isEmpty()){
                studies = null;
            }
        }finally{
            em.close();
        } 
        return studies;
    }
    
    public static List<Study> getStudies(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM Study s";
        TypedQuery<Study> q = em.createQuery(qString,Study.class);
        List<Study> studies;
        try{
            studies = q.getResultList();
            if(studies == null || studies.isEmpty()){
                studies = null;
            }
        }finally{
            em.close();
        }
        return studies;
    }
        
    public static List<Study> getStudies(String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT s FROM Study s "
                + "WHERE s.email = :email";
        TypedQuery<Study> q = em.createQuery(qString,Study.class);
        q.setParameter("email", email);
        List<Study> studies;
        try{
            studies = q.getResultList();
            if(studies == null || studies.isEmpty()){
                studies = null;
            }
        }finally{
            em.close();
        }
        return studies;
    }
    
}

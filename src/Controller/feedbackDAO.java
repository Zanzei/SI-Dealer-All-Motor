/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Feedback;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class feedbackDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Feedback> getAllFeedbacks() {
        Session sess = bukaSession();

        List<Feedback> temp = sess.createQuery("SELECT p FROM feedback p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public List<Feedback> getFeedbacklById(int param) {
        Session sess = bukaSession();

        List<Feedback> temp = sess.createQuery("SELECT p FROM detailretur p WHERE IDRetur = "+param).list();
        sess.close();
        return temp;
    }
    
    public boolean addFeedback(Feedback param) {
        try {
            Session sess = bukaSession();

            Transaction t = sess.beginTransaction();
            sess.save(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

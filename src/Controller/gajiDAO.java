/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Gaji;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class gajiDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Gaji> getAllGaji() {
        Session sess = bukaSession();

        List<Gaji> temp = sess.createQuery("SELECT p FROM Gaji p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public List<Gaji> getGajiById(String param) {
        Session sess = bukaSession();

        List<Gaji> temp = sess.createQuery("SELECT p FROM Gaji p WHERE Jabatan = "+param).list();
        sess.close();
        return temp;
    }
    
    
    public boolean addOrUpdateGaji(Gaji param) {
        try {
            Session sess = bukaSession();

            Transaction t = sess.beginTransaction();
            sess.saveOrUpdate(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

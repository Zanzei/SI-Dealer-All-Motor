/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Detailretur;
import Model.Retur;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class returDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Retur> getAllRetur() {
        Session sess = bukaSession();

        List<Retur> temp = sess.createQuery("SELECT p FROM Retur p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public List<Retur> getReturById(int param) {
        Session sess = bukaSession();

        List<Retur> temp = sess.createQuery("SELECT p FROM Retur p WHERE p.iDRetur = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Detailretur> getDetailRetur(int param) {
        Session sess = bukaSession();

        List<Detailretur> temp = sess.createQuery("SELECT p FROM Detailretur p WHERE p.iDRetur = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Detailretur> getDetailReturByCustomer(int param) {
        Session sess = bukaSession();

        List<Detailretur> temp = sess.createQuery("SELECT p FROM Retur p WHERE p.iDCustomer = "+param).list();
        sess.close();
        return temp;
    }
    
    public boolean addOrUpdateRetur(Retur param) {
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
    
    public boolean addOrUpdateDetailRetur(Detailretur param) {
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
    
    public boolean deleteRetur(Retur param) {
        try {
            Session sess = bukaSession();

            Transaction t = sess.beginTransaction();
            sess.delete(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean deleteDetailRetur(Detailretur param) {
        try {
            Session sess = bukaSession();

            Transaction t = sess.beginTransaction();
            sess.delete(param);
            t.commit();
            sess.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

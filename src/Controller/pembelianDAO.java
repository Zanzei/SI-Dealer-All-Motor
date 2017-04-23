/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Detailpembelian;
import Model.Pembelian;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class pembelianDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Pembelian> getAllPembelian() {
        Session sess = bukaSession();

        List<Pembelian> temp = sess.createQuery("SELECT p FROM Pembelian p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public List<Pembelian> getPembelianById(int param) {
        Session sess = bukaSession();

        List<Pembelian> temp = sess.createQuery("SELECT p FROM Pembelian p WHERE p.iDOrder = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Detailpembelian> getDetailPembelian(int param) {
        Session sess = bukaSession();

        List<Detailpembelian> temp = sess.createQuery("SELECT p FROM Detailpembelian p WHERE p.iDOrder = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Pembelian> getPembelianBySupplier(int param) {
        Session sess = bukaSession();

        List<Pembelian> temp = sess.createQuery("SELECT p FROM Pembelian p WHERE p.iDSupplier = "+param).list();
        sess.close();
        return temp;
    } 
    
    public boolean addOrUpdatePembelian(Pembelian param) {
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
    
    public boolean addOrUpdateDetailPembelian(Detailpembelian param) {
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
    
    public boolean deletePembelian(Pembelian param) {
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
    
    public boolean deleteDetailPembelian(Detailpembelian param) {
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

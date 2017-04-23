/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Detailpenjualan;
import Model.Penjualan;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class penjualanDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Penjualan> getAllPenjualan() {
        Session sess = bukaSession();

        List<Penjualan> temp = sess.createQuery("SELECT p FROM Penjualan p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public List<Penjualan> getPenjualanById(int param) {
        Session sess = bukaSession();

        List<Penjualan> temp = sess.createQuery("SELECT p FROM Penjualan p WHERE p.iDOrder = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Detailpenjualan> getDetailPenjualan(int param) {
        Session sess = bukaSession();

        List<Detailpenjualan> temp = sess.createQuery("SELECT p FROM Detailpenjualan p WHERE p.iDOrder = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Detailpenjualan> getDetailPenjualanByCustomer(int param) {
        Session sess = bukaSession();

        List<Detailpenjualan> temp = sess.createQuery("SELECT p FROM Penjualan p WHERE p.iDCustomer = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Penjualan> getPenjualanByKaryawan(int param) {
        Session sess = bukaSession();

        List<Penjualan> temp = sess.createQuery("SELECT p FROM Penjualan p WHERE p.iDKaryawan = "+param).list();
        sess.close();
        return temp;
    }
    
    public boolean addOrUpdatePenjualan(Penjualan param) {
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
    
    public boolean addOrUpdateDetailPenjualan(Detailpenjualan param) {
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
    
    public boolean deletePenjualan(Penjualan param) {
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
    
    public boolean deleteDetailPenjualan(Detailpenjualan param) {
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

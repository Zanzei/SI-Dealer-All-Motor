/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Kategorimotor;
import Model.Stokdigudang;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class stokDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Stokdigudang> getAllStok() {
        Session sess = bukaSession();

        List<Stokdigudang> temp = sess.createQuery("SELECT p FROM Stokdigudang p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public List<Stokdigudang> getStokById(int param) {
        Session sess = bukaSession();

        List<Stokdigudang> temp = sess.createQuery("SELECT p FROM Stokdigudang p WHERE p.IDBarang = "+param).list();
        sess.close();
        return temp;
    }
    
    public List<Stokdigudang> getStokByKategori(int param) {
        Session sess = bukaSession();

        List<Stokdigudang> temp = sess.createQuery("SELECT p FROM Stokdigudang p WHERE p.iDKategori = "+param).list();
        sess.close();
        return temp;
    }
    
    public boolean addOrUpdateStok(Stokdigudang param) {
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
    
    public boolean deleteStok(Stokdigudang param) {
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

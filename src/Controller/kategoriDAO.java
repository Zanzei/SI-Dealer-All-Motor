/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Kategorimotor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class kategoriDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Kategorimotor> getAllKategori() {
        Session sess = bukaSession();

        List<Kategorimotor> temp = sess.createQuery("SELECT p FROM Kategorimotor p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public Kategorimotor getKategoriById(int param) {
        Session sess = bukaSession();

        List<Kategorimotor> temp = sess.createQuery("SELECT p FROM Kategorimotor p WHERE p.iDKategori = "+param).list();
        sess.close();
        return temp.get(0);
    }
    
   public List<Kategorimotor> getStokByKategori(int param) {
        Session sess = bukaSession();
           
        List<Kategorimotor> temp = sess.createQuery("SELECT p FROM Stokdigudang p WHERE p.iDKategori = "+param).list();
        sess.close();
        return temp;
    }
   
    public boolean addOrUpdateKategori(Kategorimotor param) {
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
    
    public boolean deleteKategori(Kategorimotor param) {
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

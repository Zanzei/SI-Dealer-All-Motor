/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Karyawan;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class karyawanDAO {

    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Karyawan> getAllKaryawans() {
        Session sess = bukaSession();

        List<Karyawan> temp = sess.createQuery("SELECT p FROM Karyawan p").list();
        sess.close();
        return temp;
    }

    /**
     *
     * @param param
     * @return
     */
    public List<Karyawan> getKaryawanById(int param) {
        Session sess = bukaSession();

        List<Karyawan> temp = sess.createQuery("SELECT p FROM Karyawan p WHERE iDKaryawan = " + param).list();
        sess.close();
        return temp;
    }

    public List<Karyawan> getKaryawanSpv() {
        Session sess = bukaSession();

        List<Karyawan> temp = sess.createQuery("FROM Karyawan WHERE jabatan like 'SPV%' OR  jabatan like 'MNGR%' OR jabatan like 'CEO%'").list();
        sess.close();
        return temp;
    }

    public List<Karyawan> getLogin(int id, String password) {
        try {
            Session sess = bukaSession();

            List<Karyawan> temp = sess.createQuery("FROM Karyawan WHERE id = " + id + " AND password = " + password).list();
            //like 'CEO%'
            sess.close();
            return temp;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addOrUpdateKaryawan(Karyawan param) {
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

    public boolean deleteKaryawan(Karyawan param) {
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

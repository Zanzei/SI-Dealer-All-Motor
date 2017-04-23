/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ANDRE
 */
public class customerDAO {
    SessionFactory session = HibernateUtil.getSessionFactory();

    private Session bukaSession() {
        return session.openSession();
    }

    /**
     *
     * @return
     */
    public List<Customer> getAllCustomers() {
        Session sess = bukaSession();

        List<Customer> temp = sess.createQuery("SELECT p FROM customer p").list();
        sess.close();
        return temp;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public List<Customer> getCustomerById(int param) {
        Session sess = bukaSession();

        List<Customer> temp = sess.createQuery("SELECT p FROM customer p WHERE p.IDCustomer = "+param).list();
        sess.close();
        return temp;
    }
    
    
    public boolean addOrUpdateCustomer(Customer param) {
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
    
    public boolean deleteCustomer(Customer param) {
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

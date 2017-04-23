/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ANDRE
 */
@Entity
@Table(name = "retur")
@NamedQueries({
    @NamedQuery(name = "Retur.findAll", query = "SELECT r FROM Retur r")})
public class Retur implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDRetur")
    private List<Detailretur> detailreturList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRetur")
    private Integer iDRetur;
    @Basic(optional = false)
    @Column(name = "IDCustomer")
    private int iDCustomer;
    @JoinColumn(name = "IDOrder", referencedColumnName = "IDOrder")
    @ManyToOne(optional = false)
    private Penjualan iDOrder;

    public Retur() {
    }

    public Retur(Integer iDRetur) {
        this.iDRetur = iDRetur;
    }

    public Retur(Integer iDRetur, int iDCustomer) {
        this.iDRetur = iDRetur;
        this.iDCustomer = iDCustomer;
    }

    public Integer getIDRetur() {
        return iDRetur;
    }

    public void setIDRetur(Integer iDRetur) {
        this.iDRetur = iDRetur;
    }

    public int getIDCustomer() {
        return iDCustomer;
    }

    public void setIDCustomer(int iDCustomer) {
        this.iDCustomer = iDCustomer;
    }

    public Penjualan getIDOrder() {
        return iDOrder;
    }

    public void setIDOrder(Penjualan iDOrder) {
        this.iDOrder = iDOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDRetur != null ? iDRetur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retur)) {
            return false;
        }
        Retur other = (Retur) object;
        if ((this.iDRetur == null && other.iDRetur != null) || (this.iDRetur != null && !this.iDRetur.equals(other.iDRetur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Retur[ iDRetur=" + iDRetur + " ]";
    }

    public List<Detailretur> getDetailreturList() {
        return detailreturList;
    }

    public void setDetailreturList(List<Detailretur> detailreturList) {
        this.detailreturList = detailreturList;
    }
    
}

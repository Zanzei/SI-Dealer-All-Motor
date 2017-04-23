/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANDRE
 */
@Entity
@Table(name = "penjualan")
@NamedQueries({
    @NamedQuery(name = "Penjualan.findAll", query = "SELECT p FROM Penjualan p")})
public class Penjualan implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDOrder")
    private List<Retur> returList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOrder")
    private Integer iDOrder;
    @Basic(optional = false)
    @Column(name = "MetodePembayaran")
    private String metodePembayaran;
    @Basic(optional = false)
    @Column(name = "TanggalPembayaran")
    @Temporal(TemporalType.DATE)
    private Date tanggalPembayaran;
    @Basic(optional = false)
    @Column(name = "Lunas")
    private boolean lunas;
    @JoinColumn(name = "IDCustomer", referencedColumnName = "IDCustomer")
    @ManyToOne(optional = false)
    private Customer iDCustomer;
    @JoinColumn(name = "IDKaryawan", referencedColumnName = "IDKaryawan")
    @ManyToOne(optional = false)
    private Karyawan iDKaryawan;

    public Penjualan() {
    }

    public Penjualan(Integer iDOrder) {
        this.iDOrder = iDOrder;
    }

    public Penjualan(Integer iDOrder, String metodePembayaran, Date tanggalPembayaran, boolean lunas) {
        this.iDOrder = iDOrder;
        this.metodePembayaran = metodePembayaran;
        this.tanggalPembayaran = tanggalPembayaran;
        this.lunas = lunas;
    }

    public Integer getIDOrder() {
        return iDOrder;
    }

    public void setIDOrder(Integer iDOrder) {
        this.iDOrder = iDOrder;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public Date getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(Date tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public boolean getLunas() {
        return lunas;
    }

    public void setLunas(boolean lunas) {
        this.lunas = lunas;
    }

    public Customer getIDCustomer() {
        return iDCustomer;
    }

    public void setIDCustomer(Customer iDCustomer) {
        this.iDCustomer = iDCustomer;
    }

    public Karyawan getIDKaryawan() {
        return iDKaryawan;
    }

    public void setIDKaryawan(Karyawan iDKaryawan) {
        this.iDKaryawan = iDKaryawan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDOrder != null ? iDOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penjualan)) {
            return false;
        }
        Penjualan other = (Penjualan) object;
        if ((this.iDOrder == null && other.iDOrder != null) || (this.iDOrder != null && !this.iDOrder.equals(other.iDOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+iDKaryawan;
    }

    public List<Retur> getReturList() {
        return returList;
    }

    public void setReturList(List<Retur> returList) {
        this.returList = returList;
    }
    
}

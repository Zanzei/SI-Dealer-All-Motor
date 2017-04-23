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
@Table(name = "pembelian")
@NamedQueries({
    @NamedQuery(name = "Pembelian.findAll", query = "SELECT p FROM Pembelian p")})
public class Pembelian implements Serializable {

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
    @Column(name = "TanggalPembelian")
    @Temporal(TemporalType.DATE)
    private Date tanggalPembelian;
    @JoinColumn(name = "IDSupplier", referencedColumnName = "IDSupplier")
    @ManyToOne(optional = false)
    private Supplier iDSupplier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDOrder")
    private List<Detailpembelian> detailpembelianList;

    public Pembelian() {
    }

    public Pembelian(Integer iDOrder) {
        this.iDOrder = iDOrder;
    }

    public Pembelian(Integer iDOrder, String metodePembayaran, Date tanggalPembelian) {
        this.iDOrder = iDOrder;
        this.metodePembayaran = metodePembayaran;
        this.tanggalPembelian = tanggalPembelian;
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

    public Date getTanggalPembelian() {
        return tanggalPembelian;
    }

    public void setTanggalPembelian(Date tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    public Supplier getIDSupplier() {
        return iDSupplier;
    }

    public void setIDSupplier(Supplier iDSupplier) {
        this.iDSupplier = iDSupplier;
    }

    public List<Detailpembelian> getDetailpembelianList() {
        return detailpembelianList;
    }

    public void setDetailpembelianList(List<Detailpembelian> detailpembelianList) {
        this.detailpembelianList = detailpembelianList;
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
        if (!(object instanceof Pembelian)) {
            return false;
        }
        Pembelian other = (Pembelian) object;
        if ((this.iDOrder == null && other.iDOrder != null) || (this.iDOrder != null && !this.iDOrder.equals(other.iDOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Pembelian[ iDOrder=" + iDOrder + " ]";
    }
    
}

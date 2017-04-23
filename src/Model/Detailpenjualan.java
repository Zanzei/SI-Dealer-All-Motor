/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ANDRE
 */
@Entity
@Table(name = "detailpenjualan")
@NamedQueries({
    @NamedQuery(name = "Detailpenjualan.findAll", query = "SELECT d FROM Detailpenjualan d")})
public class Detailpenjualan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOrderDetails")
    private Integer iDOrderDetails;
    @Basic(optional = false)
    @Column(name = "Harga")
    private int harga;
    @Basic(optional = false)
    @Column(name = "Jumlah")
    private int jumlah;
    @Basic(optional = false)
    @Column(name = "PotonganHarga")
    private int potonganHarga;
    @JoinColumn(name = "IDOrder", referencedColumnName = "IDOrder")
    @ManyToOne(optional = false)
    private Penjualan iDOrder;
    @JoinColumn(name = "IDProduk", referencedColumnName = "IDBarang")
    @ManyToOne(optional = false)
    private Stokdigudang iDProduk;

    public Detailpenjualan() {
    }

    public Detailpenjualan(Integer iDOrderDetails) {
        this.iDOrderDetails = iDOrderDetails;
    }

    public Detailpenjualan(Integer iDOrderDetails, int harga, int jumlah, int potonganHarga) {
        this.iDOrderDetails = iDOrderDetails;
        this.harga = harga;
        this.jumlah = jumlah;
        this.potonganHarga = potonganHarga;
    }
    
    public Integer getSubtotal(){
        return jumlah * harga;
    }
    
    public Integer getIDOrderDetails() {
        return iDOrderDetails;
    }

    public void setIDOrderDetails(Integer iDOrderDetails) {
        this.iDOrderDetails = iDOrderDetails;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getPotonganHarga() {
        return potonganHarga;
    }

    public void setPotonganHarga(int potonganHarga) {
        this.potonganHarga = potonganHarga;
    }

    public Penjualan getIDOrder() {
        return iDOrder;
    }

    public void setIDOrder(Penjualan iDOrder) {
        this.iDOrder = iDOrder;
    }

    public Stokdigudang getIDProduk() {
        return iDProduk;
    }

    public void setIDProduk(Stokdigudang iDProduk) {
        this.iDProduk = iDProduk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDOrderDetails != null ? iDOrderDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailpenjualan)) {
            return false;
        }
        Detailpenjualan other = (Detailpenjualan) object;
        if ((this.iDOrderDetails == null && other.iDOrderDetails != null) || (this.iDOrderDetails != null && !this.iDOrderDetails.equals(other.iDOrderDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+iDOrderDetails;
    }
    
}

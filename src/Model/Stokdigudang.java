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
@Table(name = "stokdigudang")
@NamedQueries({
    @NamedQuery(name = "Stokdigudang.findAll", query = "SELECT s FROM Stokdigudang s")})
public class Stokdigudang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBarang")
    private Integer iDBarang;
    @Basic(optional = false)
    @Column(name = "NamaBarang")
    private String namaBarang;
    @Basic(optional = false)
    @Column(name = "Stok")
    private int stok;
    @Basic(optional = false)
    @Column(name = "Brand")
    private String brand;
    @Basic(optional = false)
    @Column(name = "Harga")
    private int harga;
    @Basic(optional = false)
    @Column(name = "TanggalDidapat")
    @Temporal(TemporalType.DATE)
    private Date tanggalDidapat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDProduk")
    private List<Detailpenjualan> detailpenjualanList;
    @JoinColumn(name = "IDSupplier", referencedColumnName = "IDSupplier")
    @ManyToOne(optional = false)
    private Supplier iDSupplier;
    @JoinColumn(name = "IDKategori", referencedColumnName = "IDKategori")
    @ManyToOne(optional = false)
    private Kategorimotor iDKategori;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDProduk")
    private List<Detailretur> detailreturList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDProduk")
    private List<Detailpembelian> detailpembelianList;

    public Stokdigudang() {
    }

    public Stokdigudang(Integer iDBarang) {
        this.iDBarang = iDBarang;
    }

    public Stokdigudang(Integer iDBarang, String namaBarang, int stok, String brand, int harga, Date tanggalDidapat) {
        this.iDBarang = iDBarang;
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.brand = brand;
        this.harga = harga;
        this.tanggalDidapat = tanggalDidapat;
    }

    public Integer getIDBarang() {
        return iDBarang;
    }

    public void setIDBarang(Integer iDBarang) {
        this.iDBarang = iDBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Date getTanggalDidapat() {
        return tanggalDidapat;
    }

    public void setTanggalDidapat(Date tanggalDidapat) {
        this.tanggalDidapat = tanggalDidapat;
    }

    public List<Detailpenjualan> getDetailpenjualanList() {
        return detailpenjualanList;
    }

    public void setDetailpenjualanList(List<Detailpenjualan> detailpenjualanList) {
        this.detailpenjualanList = detailpenjualanList;
    }

    public Supplier getIDSupplier() {
        return iDSupplier;
    }

    public void setIDSupplier(Supplier iDSupplier) {
        this.iDSupplier = iDSupplier;
    }

    public Kategorimotor getIDKategori() {
        return iDKategori;
    }

    public void setIDKategori(Kategorimotor iDKategori) {
        this.iDKategori = iDKategori;
    }

    public List<Detailretur> getDetailreturList() {
        return detailreturList;
    }

    public void setDetailreturList(List<Detailretur> detailreturList) {
        this.detailreturList = detailreturList;
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
        hash += (iDBarang != null ? iDBarang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stokdigudang)) {
            return false;
        }
        Stokdigudang other = (Stokdigudang) object;
        if ((this.iDBarang == null && other.iDBarang != null) || (this.iDBarang != null && !this.iDBarang.equals(other.iDBarang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return iDBarang + "";
    }
    
}

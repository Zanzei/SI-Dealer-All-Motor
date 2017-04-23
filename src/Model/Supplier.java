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
@Table(name = "supplier")
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSupplier")
    private Integer iDSupplier;
    @Basic(optional = false)
    @Column(name = "NamaPerusahaan")
    private String namaPerusahaan;
    @Basic(optional = false)
    @Column(name = "NamaDepan")
    private String namaDepan;
    @Basic(optional = false)
    @Column(name = "NamaBelakang")
    private String namaBelakang;
    @Basic(optional = false)
    @Column(name = "Alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "NoTelepon")
    private int noTelepon;
    @Basic(optional = false)
    @Column(name = "Catatan")
    private String catatan;
    @Basic(optional = false)
    @Column(name = "TanggalMulai")
    @Temporal(TemporalType.DATE)
    private Date tanggalMulai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDSupplier")
    private List<Pembelian> pembelianList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDSupplier")
    private List<Stokdigudang> stokdigudangList;

    public Supplier() {
    }

    public Supplier(Integer iDSupplier) {
        this.iDSupplier = iDSupplier;
    }

    public Supplier(Integer iDSupplier, String namaPerusahaan, String namaDepan, String namaBelakang, String alamat, int noTelepon, String catatan, Date tanggalMulai) {
        this.iDSupplier = iDSupplier;
        this.namaPerusahaan = namaPerusahaan;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.catatan = catatan;
        this.tanggalMulai = tanggalMulai;
    }

    public Integer getIDSupplier() {
        return iDSupplier;
    }

    public void setIDSupplier(Integer iDSupplier) {
        this.iDSupplier = iDSupplier;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(int noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public List<Pembelian> getPembelianList() {
        return pembelianList;
    }

    public void setPembelianList(List<Pembelian> pembelianList) {
        this.pembelianList = pembelianList;
    }

    public List<Stokdigudang> getStokdigudangList() {
        return stokdigudangList;
    }

    public void setStokdigudangList(List<Stokdigudang> stokdigudangList) {
        this.stokdigudangList = stokdigudangList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDSupplier != null ? iDSupplier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.iDSupplier == null && other.iDSupplier != null) || (this.iDSupplier != null && !this.iDSupplier.equals(other.iDSupplier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIDSupplier() + " - " + getNamaPerusahaan();
    }
    
}

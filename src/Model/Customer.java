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
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")})
public class Customer implements Serializable {

    @Basic(optional = false)
    @Column(name = "NoTelepon")
    private String noTelepon;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCustomer")
    private Integer iDCustomer;
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
    @Column(name = "Catatan")
    private String catatan;
    @Basic(optional = false)
    @Column(name = "TanggalMulai")
    @Temporal(TemporalType.DATE)
    private Date tanggalMulai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDCustomer")
    private List<Penjualan> penjualanList;

    public Customer() {
    }

    public Customer(Integer iDCustomer) {
        this.iDCustomer = iDCustomer;
    }

    public Customer(Integer iDCustomer, String namaPerusahaan, String namaDepan, String namaBelakang, String alamat, String noTelepon, String catatan, Date tanggalMulai) {
        this.iDCustomer = iDCustomer;
        this.namaPerusahaan = namaPerusahaan;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.catatan = catatan;
        this.tanggalMulai = tanggalMulai;
    }

    public Integer getIDCustomer() {
        return iDCustomer;
    }

    public void setIDCustomer(Integer iDCustomer) {
        this.iDCustomer = iDCustomer;
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

    public List<Penjualan> getPenjualanList() {
        return penjualanList;
    }

    public void setPenjualanList(List<Penjualan> penjualanList) {
        this.penjualanList = penjualanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCustomer != null ? iDCustomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.iDCustomer == null && other.iDCustomer != null) || (this.iDCustomer != null && !this.iDCustomer.equals(other.iDCustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return iDCustomer + "";
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    
}

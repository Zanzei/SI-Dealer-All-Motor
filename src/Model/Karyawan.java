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
import javax.persistence.Lob;
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
@Table(name = "karyawan")
@NamedQueries({
    @NamedQuery(name = "Karyawan.findAll", query = "SELECT k FROM Karyawan k")})
public class Karyawan implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "NoTelepon")
    private String noTelepon;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKaryawan")
    private Integer iDKaryawan;
    @Basic(optional = false)
    @Column(name = "NamaDepan")
    private String namaDepan;
    @Basic(optional = false)
    @Column(name = "NamaBelakang")
    private String namaBelakang;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "Alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "Catatan")
    private String catatan;
    @Basic(optional = false)
    @Column(name = "Supervisor")
    private int supervisor;
    @Basic(optional = false)
    @Column(name = "TanggalMulai")
    @Temporal(TemporalType.DATE)
    private Date tanggalMulai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDKaryawan")
    private List<Penjualan> penjualanList;
    @JoinColumn(name = "Jabatan", referencedColumnName = "Jabatan")
    @ManyToOne(optional = false)
    private Gaji jabatan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDKaryawan")
    private List<Feedback> feedbackList;

    public Karyawan() {
    }

    public Karyawan(Integer iDKaryawan) {
        this.iDKaryawan = iDKaryawan;
    }

    public Karyawan(Integer iDKaryawan, String namaDepan, String namaBelakang) {
        this.iDKaryawan = iDKaryawan;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
    }
    
    public Karyawan(Integer iDKaryawan, String namaDepan, String namaBelakang, String password, String alamat, String noTelepon, String catatan, int supervisor, Date tanggalMulai) {
        this.iDKaryawan = iDKaryawan;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.password = password;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.catatan = catatan;
        this.supervisor = supervisor;
        this.tanggalMulai = tanggalMulai;
    }

    public Integer getIDKaryawan() {
        return iDKaryawan;
    }

    public void setIDKaryawan(Integer iDKaryawan) {
        this.iDKaryawan = iDKaryawan;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
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

    public Gaji getJabatan() {
        return jabatan;
    }

    public void setJabatan(Gaji jabatan) {
        this.jabatan = jabatan;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDKaryawan != null ? iDKaryawan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Karyawan)) {
            return false;
        }
        Karyawan other = (Karyawan) object;
        if ((this.iDKaryawan == null && other.iDKaryawan != null) || (this.iDKaryawan != null && !this.iDKaryawan.equals(other.iDKaryawan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return iDKaryawan + "";
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    
}

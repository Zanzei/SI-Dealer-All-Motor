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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ANDRE
 */
@Entity
@Table(name = "gaji")
@NamedQueries({
    @NamedQuery(name = "Gaji.findAll", query = "SELECT g FROM Gaji g")})
public class Gaji implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Jabatan")
    private String jabatan;
    @Basic(optional = false)
    @Column(name = "Gaji")
    private int gaji;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jabatan")
    private List<Karyawan> karyawanList;

    public Gaji() {
    }

    public Gaji(String jabatan) {
        this.jabatan = jabatan;
    }

    public Gaji(String jabatan, int gaji) {
        this.jabatan = jabatan;
        this.gaji = gaji;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }

    public List<Karyawan> getKaryawanList() {
        return karyawanList;
    }

    public void setKaryawanList(List<Karyawan> karyawanList) {
        this.karyawanList = karyawanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jabatan != null ? jabatan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gaji)) {
            return false;
        }
        Gaji other = (Gaji) object;
        if ((this.jabatan == null && other.jabatan != null) || (this.jabatan != null && !this.jabatan.equals(other.jabatan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return jabatan + "";
    }
    
}

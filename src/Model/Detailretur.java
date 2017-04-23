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
@Table(name = "detailretur")
@NamedQueries({
    @NamedQuery(name = "Detailretur.findAll", query = "SELECT d FROM Detailretur d")})
public class Detailretur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDReturDetails")
    private Integer iDReturDetails;
    @Basic(optional = false)
    @Column(name = "Keterangan")
    private String keterangan;
    @Basic(optional = false)
    @Column(name = "Disetujui")
    private boolean disetujui;
    @JoinColumn(name = "IDRetur", referencedColumnName = "IDRetur")
    @ManyToOne(optional = false)
    private Retur iDRetur;
    @JoinColumn(name = "IDProduk", referencedColumnName = "IDBarang")
    @ManyToOne(optional = false)
    private Stokdigudang iDProduk;

    public Detailretur() {
    }

    public Detailretur(Integer iDReturDetails) {
        this.iDReturDetails = iDReturDetails;
    }

    public Detailretur(Integer iDReturDetails, String keterangan, boolean disetujui) {
        this.iDReturDetails = iDReturDetails;
        this.keterangan = keterangan;
        this.disetujui = disetujui;
    }

    public Integer getIDReturDetails() {
        return iDReturDetails;
    }

    public void setIDReturDetails(Integer iDReturDetails) {
        this.iDReturDetails = iDReturDetails;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public boolean getDisetujui() {
        return disetujui;
    }

    public void setDisetujui(boolean disetujui) {
        this.disetujui = disetujui;
    }

    public Retur getIDRetur() {
        return iDRetur;
    }

    public void setIDRetur(Retur iDRetur) {
        this.iDRetur = iDRetur;
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
        hash += (iDReturDetails != null ? iDReturDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailretur)) {
            return false;
        }
        Detailretur other = (Detailretur) object;
        if ((this.iDReturDetails == null && other.iDReturDetails != null) || (this.iDReturDetails != null && !this.iDReturDetails.equals(other.iDReturDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return iDReturDetails + "";
    }
    
}

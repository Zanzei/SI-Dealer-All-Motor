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
@Table(name = "feedback")
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFeedback")
    private Integer iDFeedback;
    @Basic(optional = false)
    @Column(name = "Keterangan")
    private String keterangan;
    @JoinColumn(name = "IDKaryawan", referencedColumnName = "IDKaryawan")
    @ManyToOne(optional = false)
    private Karyawan iDKaryawan;

    public Feedback() {
    }

    public Feedback(Integer iDFeedback) {
        this.iDFeedback = iDFeedback;
    }

    public Feedback(Integer iDFeedback, String keterangan) {
        this.iDFeedback = iDFeedback;
        this.keterangan = keterangan;
    }

    public Integer getIDFeedback() {
        return iDFeedback;
    }

    public void setIDFeedback(Integer iDFeedback) {
        this.iDFeedback = iDFeedback;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        hash += (iDFeedback != null ? iDFeedback.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.iDFeedback == null && other.iDFeedback != null) || (this.iDFeedback != null && !this.iDFeedback.equals(other.iDFeedback))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Feedback[ iDFeedback=" + iDFeedback + " ]";
    }
    
}

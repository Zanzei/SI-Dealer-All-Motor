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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "kategorimotor")
@NamedQueries({
    @NamedQuery(name = "Kategorimotor.findAll", query = "SELECT k FROM Kategorimotor k")})
public class Kategorimotor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDKategori")
    private Integer iDKategori;
    @Basic(optional = false)
    @Column(name = "NamaKategori")
    private String namaKategori;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDKategori")
    private List<Stokdigudang> stokdigudangList;

    public Kategorimotor() {
    }

    public Kategorimotor(Integer iDKategori) {
        this.iDKategori = iDKategori;
    }

    public Kategorimotor(Integer iDKategori, String namaKategori) {
        this.iDKategori = iDKategori;
        this.namaKategori = namaKategori;
    }

    public Integer getIDKategori() {
        return iDKategori;
    }

    public void setIDKategori(Integer iDKategori) {
        this.iDKategori = iDKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
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
        hash += (iDKategori != null ? iDKategori.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorimotor)) {
            return false;
        }
        Kategorimotor other = (Kategorimotor) object;
        if ((this.iDKategori == null && other.iDKategori != null) || (this.iDKategori != null && !this.iDKategori.equals(other.iDKategori))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getIDKategori() + " - " + getNamaKategori();
    }
    
}

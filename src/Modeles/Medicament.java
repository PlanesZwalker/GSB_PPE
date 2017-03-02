/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author passpass
 */
@MappedSuperclass
@Table(catalog = "gsb_ppe", schema = "")
@XmlRootElement
public class Medicament implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idmedicament;
    @Column(length = 255)
    private String denomination;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicament")
    private Collection<Compose> composeCollection;

    public Medicament() {
    }

    public Medicament(Integer idmedicament) {
        this.idmedicament = idmedicament;
    }

    public Integer getIdmedicament() {
        return idmedicament;
    }

    public void setIdmedicament(Integer idmedicament) {
        this.idmedicament = idmedicament;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @XmlTransient
    public Collection<Compose> getComposeCollection() {
        return composeCollection;
    }

    public void setComposeCollection(Collection<Compose> composeCollection) {
        this.composeCollection = composeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicament != null ? idmedicament.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicament)) {
            return false;
        }
        Medicament other = (Medicament) object;
        if ((this.idmedicament == null && other.idmedicament != null) || (this.idmedicament != null && !this.idmedicament.equals(other.idmedicament))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modeles.Medicament[ idmedicament=" + idmedicament + " ]";
    }
    
}

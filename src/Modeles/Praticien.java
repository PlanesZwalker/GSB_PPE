/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
public class Praticien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idpraticien;
    @Column(length = 255)
    private String nom;
    @Column(length = 25)
    private String prenom;
    @OneToMany(mappedBy = "idpraticien")
    private Collection<Rapportdevisite> rapportdevisiteCollection;

    public Praticien() {
    }

    public Praticien(Integer idpraticien) {
        this.idpraticien = idpraticien;
    }

    public Integer getIdpraticien() {
        return idpraticien;
    }

    public void setIdpraticien(Integer idpraticien) {
        this.idpraticien = idpraticien;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @XmlTransient
    public Collection<Rapportdevisite> getRapportdevisiteCollection() {
        return rapportdevisiteCollection;
    }

    public void setRapportdevisiteCollection(Collection<Rapportdevisite> rapportdevisiteCollection) {
        this.rapportdevisiteCollection = rapportdevisiteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpraticien != null ? idpraticien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Praticien)) {
            return false;
        }
        Praticien other = (Praticien) object;
        if ((this.idpraticien == null && other.idpraticien != null) || (this.idpraticien != null && !this.idpraticien.equals(other.idpraticien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modeles.Praticien[ idpraticien=" + idpraticien + " ]";
    }
    
}

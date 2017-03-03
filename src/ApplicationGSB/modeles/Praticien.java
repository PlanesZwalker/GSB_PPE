/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.modeles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author passpass
 */
@Entity
@Table(name = "praticien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Praticien.findAll", query = "SELECT p FROM Praticien p")
    , @NamedQuery(name = "Praticien.findByIdpraticien", query = "SELECT p FROM Praticien p WHERE p.idpraticien = :idpraticien")
    , @NamedQuery(name = "Praticien.findByNom", query = "SELECT p FROM Praticien p WHERE p.nom = :nom")
    , @NamedQuery(name = "Praticien.findByPrenom", query = "SELECT p FROM Praticien p WHERE p.prenom = :prenom")})
public class Praticien implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpraticien")
    private Integer idpraticien;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @OneToMany(mappedBy = "praticien")
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
        Integer oldIdpraticien = this.idpraticien;
        this.idpraticien = idpraticien;
        changeSupport.firePropertyChange("idpraticien", oldIdpraticien, idpraticien);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String oldNom = this.nom;
        this.nom = nom;
        changeSupport.firePropertyChange("nom", oldNom, nom);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        String oldPrenom = this.prenom;
        this.prenom = prenom;
        changeSupport.firePropertyChange("prenom", oldPrenom, prenom);
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
        return "ApplicationGSB.modeles.Praticien[ idpraticien=" + idpraticien + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

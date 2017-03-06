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
@Table(name = "visiteurmedical")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visiteurmedical.findAll", query = "SELECT v FROM Visiteurmedical v")
    , @NamedQuery(name = "Visiteurmedical.findByIdvisiteur", query = "SELECT v FROM Visiteurmedical v WHERE v.idvisiteur = :idvisiteur")
    , @NamedQuery(name = "Visiteurmedical.findByNom", query = "SELECT v FROM Visiteurmedical v WHERE v.nom = :nom")
    , @NamedQuery(name = "Visiteurmedical.findByMotdepasse", query = "SELECT v FROM Visiteurmedical v WHERE v.motdepasse = :motdepasse")
    , @NamedQuery(name = "Visiteurmedical.findByPrenom", query = "SELECT v FROM Visiteurmedical v WHERE v.prenom = :prenom")
    , @NamedQuery(name = "Visiteurmedical.findByAdresse", query = "SELECT v FROM Visiteurmedical v WHERE v.adresse = :adresse")
    , @NamedQuery(name = "Visiteurmedical.findByVille", query = "SELECT v FROM Visiteurmedical v WHERE v.ville = :ville")
    , @NamedQuery(name = "Visiteurmedical.findBySecteur", query = "SELECT v FROM Visiteurmedical v WHERE v.secteur = :secteur")
    , @NamedQuery(name = "Visiteurmedical.findByLabo", query = "SELECT v FROM Visiteurmedical v WHERE v.labo = :labo")
    , @NamedQuery(name = "Visiteurmedical.findByEmail", query = "SELECT v FROM Visiteurmedical v WHERE v.email = :email")})
public class Visiteurmedical implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idvisiteur")
    private Integer idvisiteur;
    @Column(name = "nom")
    private String nom;
    @Column(name = "motdepasse")
    private String motdepasse;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "ville")
    private String ville;
    @Column(name = "secteur")
    private String secteur;
    @Column(name = "labo")
    private String labo;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "visiteurmedical")
    private Collection<Rapportdevisite> rapportdevisiteCollection;

    public Visiteurmedical() {
    }

    public Visiteurmedical(Integer idvisiteur) {
        this.idvisiteur = idvisiteur;
    }

    public Integer getIdvisiteur() {
        return idvisiteur;
    }

    public void setIdvisiteur(Integer idvisiteur) {
        Integer oldIdvisiteur = this.idvisiteur;
        this.idvisiteur = idvisiteur;
        changeSupport.firePropertyChange("idvisiteur", oldIdvisiteur, idvisiteur);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String oldNom = this.nom;
        this.nom = nom;
        changeSupport.firePropertyChange("nom", oldNom, nom);
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        String oldMotdepasse = this.motdepasse;
        this.motdepasse = motdepasse;
        changeSupport.firePropertyChange("motdepasse", oldMotdepasse, motdepasse);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        String oldPrenom = this.prenom;
        this.prenom = prenom;
        changeSupport.firePropertyChange("prenom", oldPrenom, prenom);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        String oldAdresse = this.adresse;
        this.adresse = adresse;
        changeSupport.firePropertyChange("adresse", oldAdresse, adresse);
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        String oldVille = this.ville;
        this.ville = ville;
        changeSupport.firePropertyChange("ville", oldVille, ville);
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        String oldSecteur = this.secteur;
        this.secteur = secteur;
        changeSupport.firePropertyChange("secteur", oldSecteur, secteur);
    }

    public String getLabo() {
        return labo;
    }

    public void setLabo(String labo) {
        String oldLabo = this.labo;
        this.labo = labo;
        changeSupport.firePropertyChange("labo", oldLabo, labo);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
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
        hash += (idvisiteur != null ? idvisiteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visiteurmedical)) {
            return false;
        }
        Visiteurmedical other = (Visiteurmedical) object;
        if ((this.idvisiteur == null && other.idvisiteur != null) || (this.idvisiteur != null && !this.idvisiteur.equals(other.idvisiteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationGSB.modeles.Visiteurmedical[ idvisiteur=" + idvisiteur + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

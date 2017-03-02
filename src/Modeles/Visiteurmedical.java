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
public class Visiteurmedical implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idvisiteur;
    @Column(length = 255)
    private String nom;
    @Column(length = 255)
    private String motdepasse;
    @Column(length = 25)
    private String prenom;
    @Column(length = 255)
    private String adresse;
    @Column(length = 255)
    private String ville;
    @Column(length = 255)
    private String secteur;
    @Column(length = 25)
    private String labo;
    @Column(length = 25)
    private String email;
    @OneToMany(mappedBy = "idvisiteur")
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
        this.idvisiteur = idvisiteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getLabo() {
        return labo;
    }

    public void setLabo(String labo) {
        this.labo = labo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Modeles.Visiteurmedical[ idvisiteur=" + idvisiteur + " ]";
    }
    
}

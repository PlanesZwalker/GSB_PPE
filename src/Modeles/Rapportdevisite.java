/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author passpass
 */
@MappedSuperclass
@Table(catalog = "gsb_ppe", schema = "")
@XmlRootElement
public class Rapportdevisite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idrapport;
    @Column(length = 255)
    private String motifvisite;
    @Lob
    @Column(length = 65535)
    private String bilan;
    @Temporal(TemporalType.DATE)
    private Date daterapport;
    @JoinColumn(name = "idechantillon", referencedColumnName = "idechantillon")
    @ManyToOne
    private Echantillon idechantillon;
    @JoinColumn(name = "idpraticien", referencedColumnName = "idpraticien")
    @ManyToOne
    private Praticien idpraticien;
    @JoinColumn(name = "idvisiteur", referencedColumnName = "idvisiteur")
    @ManyToOne
    private Visiteurmedical idvisiteur;

    public Rapportdevisite() {
    }

    public Rapportdevisite(Integer idrapport) {
        this.idrapport = idrapport;
    }

    public Integer getIdrapport() {
        return idrapport;
    }

    public void setIdrapport(Integer idrapport) {
        this.idrapport = idrapport;
    }

    public String getMotifvisite() {
        return motifvisite;
    }

    public void setMotifvisite(String motifvisite) {
        this.motifvisite = motifvisite;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public Date getDaterapport() {
        return daterapport;
    }

    public void setDaterapport(Date daterapport) {
        this.daterapport = daterapport;
    }

    public Echantillon getIdechantillon() {
        return idechantillon;
    }

    public void setIdechantillon(Echantillon idechantillon) {
        this.idechantillon = idechantillon;
    }

    public Praticien getIdpraticien() {
        return idpraticien;
    }

    public void setIdpraticien(Praticien idpraticien) {
        this.idpraticien = idpraticien;
    }

    public Visiteurmedical getIdvisiteur() {
        return idvisiteur;
    }

    public void setIdvisiteur(Visiteurmedical idvisiteur) {
        this.idvisiteur = idvisiteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrapport != null ? idrapport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rapportdevisite)) {
            return false;
        }
        Rapportdevisite other = (Rapportdevisite) object;
        if ((this.idrapport == null && other.idrapport != null) || (this.idrapport != null && !this.idrapport.equals(other.idrapport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modeles.Rapportdevisite[ idrapport=" + idrapport + " ]";
    }
    
}

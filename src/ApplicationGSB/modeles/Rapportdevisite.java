/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.modeles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author passpass
 */
@Entity
@Table(name = "rapportdevisite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rapportdevisite.findAll", query = "SELECT r FROM Rapportdevisite r")
    , @NamedQuery(name = "Rapportdevisite.findByIdrapport", query = "SELECT r FROM Rapportdevisite r WHERE r.idrapport = :idrapport")
    , @NamedQuery(name = "Rapportdevisite.findByMotifvisite", query = "SELECT r FROM Rapportdevisite r WHERE r.motifvisite = :motifvisite")
    , @NamedQuery(name = "Rapportdevisite.findByDaterapport", query = "SELECT r FROM Rapportdevisite r WHERE r.daterapport = :daterapport")})
public class Rapportdevisite implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idrapport")
    private Integer idrapport;
    @Column(name = "motifvisite")
    private String motifvisite;
    @Lob
    @Column(name = "bilan")
    private String bilan;
    @Column(name = "daterapport")
    @Temporal(TemporalType.DATE)
    private Date daterapport;
    @JoinColumn(name = "idechantillon", referencedColumnName = "idechantillon")
    @ManyToOne
    private Echantillon echantillon;
    @JoinColumn(name = "idpraticien", referencedColumnName = "idpraticien")
    @ManyToOne
    private Praticien praticien;
    @JoinColumn(name = "idvisiteur", referencedColumnName = "idvisiteur")
    @ManyToOne
    private Visiteurmedical visiteurmedical;

    public Rapportdevisite() {
    }

    public Rapportdevisite(Integer idrapport) {
        this.idrapport = idrapport;
    }

    public Integer getIdrapport() {
        return idrapport;
    }

    public void setIdrapport(Integer idrapport) {
        Integer oldIdrapport = this.idrapport;
        this.idrapport = idrapport;
        changeSupport.firePropertyChange("idrapport", oldIdrapport, idrapport);
    }

    public String getMotifvisite() {
        return motifvisite;
    }

    public void setMotifvisite(String motifvisite) {
        String oldMotifvisite = this.motifvisite;
        this.motifvisite = motifvisite;
        changeSupport.firePropertyChange("motifvisite", oldMotifvisite, motifvisite);
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        String oldBilan = this.bilan;
        this.bilan = bilan;
        changeSupport.firePropertyChange("bilan", oldBilan, bilan);
    }

    public Date getDaterapport() {
        return daterapport;
    }

    public void setDaterapport(Date daterapport) {
        Date oldDaterapport = this.daterapport;
        this.daterapport = daterapport;
        changeSupport.firePropertyChange("daterapport", oldDaterapport, daterapport);
    }

    public Echantillon getEchantillon() {
        return echantillon;
    }

    public void setEchantillon(Echantillon echantillon) {
        Echantillon oldEchantillon = this.echantillon;
        this.echantillon = echantillon;
        changeSupport.firePropertyChange("echantillon", oldEchantillon, echantillon);
    }

    public Praticien getPraticien() {
        return praticien;
    }

    public void setPraticien(Praticien praticien) {
        Praticien oldPraticien = this.praticien;
        this.praticien = praticien;
        changeSupport.firePropertyChange("praticien", oldPraticien, praticien);
    }

    public Visiteurmedical getVisiteurmedical() {
        return visiteurmedical;
    }

    public void setVisiteurmedical(Visiteurmedical visiteurmedical) {
        Visiteurmedical oldVisiteurmedical = this.visiteurmedical;
        this.visiteurmedical = visiteurmedical;
        changeSupport.firePropertyChange("visiteurmedical", oldVisiteurmedical, visiteurmedical);
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
        return "ApplicationGSB.modeles.Rapportdevisite[ idrapport=" + idrapport + " ]";
    }

    public void setIdpraticien(Praticien p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

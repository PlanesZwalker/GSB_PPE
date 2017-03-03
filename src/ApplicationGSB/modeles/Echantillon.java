/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.modeles;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author passpass
 */
@Entity
@Table(name = "echantillon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Echantillon.findAll", query = "SELECT e FROM Echantillon e")
    , @NamedQuery(name = "Echantillon.findByIdechantillon", query = "SELECT e FROM Echantillon e WHERE e.idechantillon = :idechantillon")})
public class Echantillon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idechantillon")
    private Integer idechantillon;
    @OneToMany(mappedBy = "echantillon")
    private Collection<Rapportdevisite> rapportdevisiteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "echantillon")
    private Collection<Compose> composeCollection;

    public Echantillon() {
    }

    public Echantillon(Integer idechantillon) {
        this.idechantillon = idechantillon;
    }

    public Integer getIdechantillon() {
        return idechantillon;
    }

    public void setIdechantillon(Integer idechantillon) {
        this.idechantillon = idechantillon;
    }

    @XmlTransient
    public Collection<Rapportdevisite> getRapportdevisiteCollection() {
        return rapportdevisiteCollection;
    }

    public void setRapportdevisiteCollection(Collection<Rapportdevisite> rapportdevisiteCollection) {
        this.rapportdevisiteCollection = rapportdevisiteCollection;
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
        hash += (idechantillon != null ? idechantillon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Echantillon)) {
            return false;
        }
        Echantillon other = (Echantillon) object;
        if ((this.idechantillon == null && other.idechantillon != null) || (this.idechantillon != null && !this.idechantillon.equals(other.idechantillon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationGSB.modeles.Echantillon[ idechantillon=" + idechantillon + " ]";
    }
    
}

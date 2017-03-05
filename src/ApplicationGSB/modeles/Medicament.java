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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "medicament")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicament.findAll", query = "SELECT m FROM Medicament m")
    , @NamedQuery(name = "Medicament.findByIdmedicament", query = "SELECT m FROM Medicament m WHERE m.idmedicament = :idmedicament")
    , @NamedQuery(name = "Medicament.findByDenomination", query = "SELECT m FROM Medicament m WHERE m.denomination = :denomination")})
public class Medicament implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmedicament")
    private Integer idmedicament;
    @Column(name = "denomination")
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
        Integer oldIdmedicament = this.idmedicament;
        this.idmedicament = idmedicament;
        changeSupport.firePropertyChange("idmedicament", oldIdmedicament, idmedicament);
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        String oldDenomination = this.denomination;
        this.denomination = denomination;
        changeSupport.firePropertyChange("denomination", oldDenomination, denomination);
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
        return "ApplicationGSB.modeles.Medicament[ idmedicament=" + idmedicament + " ]";
    }

    public Collection<Compose> getComposeList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setComposeList(List list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

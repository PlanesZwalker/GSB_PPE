/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.modeles;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author passpass
 */
@Entity
@Table(name = "compose")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compose.findAll", query = "SELECT c FROM Compose c")
    , @NamedQuery(name = "Compose.findByQuantite", query = "SELECT c FROM Compose c WHERE c.quantite = :quantite")
    , @NamedQuery(name = "Compose.findByIdmedicament", query = "SELECT c FROM Compose c WHERE c.composePK.idmedicament = :idmedicament")
    , @NamedQuery(name = "Compose.findByIdechantillon", query = "SELECT c FROM Compose c WHERE c.composePK.idechantillon = :idechantillon")})
public class Compose implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComposePK composePK;
    @Column(name = "quantite")
    private Integer quantite;
    @JoinColumn(name = "idechantillon", referencedColumnName = "idechantillon", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Echantillon echantillon;
    @JoinColumn(name = "idmedicament", referencedColumnName = "idmedicament", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medicament medicament;

    public Compose() {
    }

    public Compose(ComposePK composePK) {
        this.composePK = composePK;
    }

    public Compose(int idmedicament, int idechantillon) {
        this.composePK = new ComposePK(idmedicament, idechantillon);
    }

    public ComposePK getComposePK() {
        return composePK;
    }

    public void setComposePK(ComposePK composePK) {
        this.composePK = composePK;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Echantillon getEchantillon() {
        return echantillon;
    }

    public void setEchantillon(Echantillon echantillon) {
        this.echantillon = echantillon;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (composePK != null ? composePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compose)) {
            return false;
        }
        Compose other = (Compose) object;
        if ((this.composePK == null && other.composePK != null) || (this.composePK != null && !this.composePK.equals(other.composePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationGSB.modeles.Compose[ composePK=" + composePK + " ]";
    }
    
}

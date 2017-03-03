/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationGSB.modeles;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author passpass
 */
@Embeddable
public class ComposePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idmedicament")
    private int idmedicament;
    @Basic(optional = false)
    @Column(name = "idechantillon")
    private int idechantillon;

    public ComposePK() {
    }

    public ComposePK(int idmedicament, int idechantillon) {
        this.idmedicament = idmedicament;
        this.idechantillon = idechantillon;
    }

    public int getIdmedicament() {
        return idmedicament;
    }

    public void setIdmedicament(int idmedicament) {
        this.idmedicament = idmedicament;
    }

    public int getIdechantillon() {
        return idechantillon;
    }

    public void setIdechantillon(int idechantillon) {
        this.idechantillon = idechantillon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmedicament;
        hash += (int) idechantillon;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComposePK)) {
            return false;
        }
        ComposePK other = (ComposePK) object;
        if (this.idmedicament != other.idmedicament) {
            return false;
        }
        if (this.idechantillon != other.idechantillon) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ApplicationGSB.modeles.ComposePK[ idmedicament=" + idmedicament + ", idechantillon=" + idechantillon + " ]";
    }
    
}

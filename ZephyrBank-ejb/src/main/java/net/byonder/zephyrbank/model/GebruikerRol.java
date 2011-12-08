/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.byonder.zephyrbank.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Column;

import Listener.Auditor;

/**
 *
 * @author jvdgriendt
 */
@Entity
@EntityListeners(Auditor.class)
public class GebruikerRol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "rollen")
    private List<Gebruiker> gebruikers;

    @Column(length=25, unique=true, nullable=false)
    private String rolNaam;

    @Column(length=255, unique=true, nullable=false)
    private String rolOmschrijving;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    /**
     * @return the gebruikers
     */
    public List<Gebruiker> getGebruikers() {
        return gebruikers;
    }

    /**
     * @param gebruikers the gebruikers to set
     */
    public void setGebruikers(List<Gebruiker> gebruikers) {
        this.gebruikers = gebruikers;
    }

    /**
     * @return the rolNaam
     */
    public String getRolNaam() {
        return rolNaam;
    }

    /**
     * @param rolNaam the rolNaam to set
     */
    public void setRolNaam(String rolNaam) {
        this.rolNaam = rolNaam;
    }

    /**
     * @return the rolOmschrijving
     */
    public String getRolOmschrijving() {
        return rolOmschrijving;
    }

    /**
     * @param rolOmschrijving the rolOmschrijving to set
     */
    public void setRolOmschrijving(String rolOmschrijving) {
        this.rolOmschrijving = rolOmschrijving;
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GebruikerRol)) {
            return false;
        }
        GebruikerRol other = (GebruikerRol) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.byonder.zephyrbank.model.GebruikerRol[id=" + getId() + "]";
    }
}

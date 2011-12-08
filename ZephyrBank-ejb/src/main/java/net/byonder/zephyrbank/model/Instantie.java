/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.byonder.zephyrbank.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;

import Listener.Auditor;

/**
 *
 * @author jvdgriendt
 */
@Entity
@EntityListeners(Auditor.class)
public class Instantie implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=255, nullable=false, unique=true)
    private String naam;

    @OneToOne
    private Gebruiker standaardGebruiker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Gebruiker getStandaardGebruiker() {
		return standaardGebruiker;
	}

	public void setStandaardGebruiker(Gebruiker standaardGebruiker) {
		this.standaardGebruiker = standaardGebruiker;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Instantie)) {
            return false;
        }
        Instantie other = (Instantie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.byonder.zephyrbank.model.Instantie[id=" + id + "]";
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.byonder.zephyrbank.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import Listener.Auditor;

/**
 *
 * @author jvdgriendt
 */
@Entity
@EntityListeners(Auditor.class)
public class Gebruiker implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=55, nullable=false)
    private String voorNaam;

    @Column(length=10, nullable=true)
    private String tussenVoegsel;

    @Column(length=55, nullable=false)
    private String achterNaam;

    @OneToOne(mappedBy = "standaardGebruiker")
    private Instantie instantie;

    @ManyToMany
    private Set<GebruikerRol> rollen;

    @OneToMany(mappedBy = "eigenaar")
    private Set<Rekening> rekeningen;

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
     * @return the voorNaam
     */
    public String getVoorNaam() {
        return voorNaam;
    }

    /**
     * @param voorNaam the voorNaam to set
     */
    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    /**
     * @return the tussenVoegsel
     */
    public String getTussenVoegsel() {
        return tussenVoegsel;
    }

    /**
     * @param tussenVoegsel the tussenVoegsel to set
     */
    public void setTussenVoegsel(String tussenVoegsel) {
        this.tussenVoegsel = tussenVoegsel;
    }

    /**
     * @return the achterNaam
     */
    public String getAchterNaam() {
        return achterNaam;
    }

    /**
     * @param achterNaam the achterNaam to set
     */
    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public Instantie getInstantie() {
        return instantie;
    }

    public void setInstantie(Instantie instantie) {
        this.instantie = instantie;
    }

    public void wijsRolToe(GebruikerRol rol){
        rollen.add(rol);
    }

    public boolean neemRolAf(GebruikerRol rol){
        if(rollen.contains(rol)){
            return rollen.remove(rol);
        } else {
            return false;
        }
    }

    public Set<Rekening> getRekeningen(){
      return new HashSet<Rekening>(this.rekeningen);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Gebruiker)) {
            return false;
        }
        Gebruiker other = (Gebruiker) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 5;
		return "Gebruiker [id=" + id + ", voorNaam=" + voorNaam + ", tussenVoegsel=" + tussenVoegsel + ", achterNaam=" + achterNaam + ", instantie=" + instantie + ", rollen="
				+ (rollen != null ? toString(rollen, maxLen) : null) + ", rekeningen=" + (rekeningen != null ? toString(rekeningen, maxLen) : null) + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

    
}

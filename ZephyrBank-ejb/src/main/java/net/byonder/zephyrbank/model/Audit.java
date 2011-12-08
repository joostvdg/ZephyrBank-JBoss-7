package net.byonder.zephyrbank.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author jvdgriendt
 *
 */
@Entity
public class Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String actie;
	
	private String gebruiker;
	
	private String rol;
	
	private String omschrijving;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	
	public Long getId(){
		return id;
	}
	
	/**
	 * @return the actie
	 */
	public String getActie() {
		return actie;
	}

	/**
	 * @param actie the actie to set
	 */
	public void setActie(String actie) {
		this.actie = actie;
	}

	/**
	 * @return the gebruiker
	 */
	public String getGebruiker() {
		return gebruiker;
	}

	/**
	 * @param gebruiker the gebruiker to set
	 */
	public void setGebruiker(String gebruiker) {
		this.gebruiker = gebruiker;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the omschrijving
	 */
	public String getOmschrijving() {
		return omschrijving;
	}

	/**
	 * @param omschrijving the omschrijving to set
	 */
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
    *
    * @return
    */
   @Override
   public int hashCode() {
       int hash = 0;
       hash += (id != null ? id.hashCode() : 0);
       return hash;
   }

   /**
    *
    * @param object
    * @return
    */
   @Override
   public boolean equals(Object object) {
       if (!(object instanceof Audit)) {
           return false;
       }
       Audit other = (Audit) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   /**
   *
   * @return
   */
  @Override
  public String toString() {
      return "net.byonder.zephyrbank.model.Audit[id=" + id + "]";
  }
}

package net.byonder.zephyrbank.value;

/**
 * Placeholder class zodat de values gebind kunnen worden in de facelet voordat er een valide gebruiker kan worden gemaakt.
 * 
 * @author jvdgriendt
 *
 */
public class GebruikerNaam{
	
	private String voorNaam;
	private String achterNaam;
	private String tussenVoegsel;
	
	public GebruikerNaam(){
		super();
	}
	
	/**
	 * @param voorNaam
	 * @param achterNaam
	 * @param tussenVoegsel
	 */
	public GebruikerNaam(String voorNaam, String achterNaam, String tussenVoegsel){
		this();
		this.voorNaam = voorNaam;
		this.achterNaam = achterNaam;
		this.tussenVoegsel =  getAchterNaam();
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
	
	
}
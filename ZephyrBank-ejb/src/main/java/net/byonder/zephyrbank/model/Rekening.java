package net.byonder.zephyrbank.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Listener.Auditor;
import net.byonder.zephyrbank.exceptions.OnvoldoendeSaldoExceptie;

/**
 *
 * @author jvdgriendt
 */
@Entity
@EntityListeners(Auditor.class)
public abstract class Rekening implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float saldo;

    @ManyToOne(cascade=CascadeType.ALL)
    private Gebruiker eigenaar;

    @OneToMany(mappedBy = "rekeningVan", cascade=CascadeType.ALL)
    private Set<Transactie> mutaties;

    public Rekening(float saldo){
        super();
        this.saldo = saldo;
    }

    public Rekening(){
        this(0.00f);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the saldo
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * @return the eigenaar
     */
    public Gebruiker getEigenaar() {
        return eigenaar;
    }

    /**
     * @param eigenaar the eigenaar to set
     */
    public void setEigenaar(Gebruiker eigenaar) {
        this.eigenaar = eigenaar;
    }

    public Set<Transactie> getMutaties(){
        return new HashSet<Transactie>(this.mutaties);
    }
    
    public boolean voegMutatieToe(Transactie transactie){
    	return this.mutaties.add(transactie);
    }
 
    public String getType(){
    	return this.getClass().getSimpleName();
    }
   
    public boolean muteerSaldo(float mutatie) throws OnvoldoendeSaldoExceptie{
        if(( saldo + mutatie) < 0 ){
            throw new OnvoldoendeSaldoExceptie("Er is niet voldoende saldo beschikbaar.");
        } else {
            saldo =  (saldo + mutatie);
            Transactie transactie = new Transactie(this, null, mutatie);
            this.mutaties.add(transactie);
            return true;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rekening)) {
            return false;
        }
        Rekening other = (Rekening) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.byonder.zephyrbank.model.Rekening[id=" + getId() + "]";
    }
}

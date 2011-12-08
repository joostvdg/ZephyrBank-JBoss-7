/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.byonder.zephyrbank.model;

import java.io.Serializable;
import javax.persistence.Entity;

import net.byonder.zephyrbank.exceptions.OnvoldoendeSaldoExceptie;

/**
 *
 * @author jvdgriendt
 */
@Entity
public class KredietRekening extends Rekening implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private float krediet;

    public KredietRekening(){
        super();
    }

    public KredietRekening(float saldo, float krediet){
        super(saldo);
        if( (saldo < 0.00) || (krediet < 0.00) ){
            throw new IllegalStateException("Saldo en krediet moeten beide neutraal of positief beginnen.");
        }
        this.krediet = krediet;
    }

    public float getKrediet(){
        return krediet;
    }

    @Override
    public boolean muteerSaldo(float mutatie) throws OnvoldoendeSaldoExceptie{
        float teBestedenBedrag = getSaldo() + krediet;
        if(( teBestedenBedrag + mutatie) < 0 ){
            throw new OnvoldoendeSaldoExceptie("Er is niet voldoende saldo beschikbaar.");
        } else {
            return super.muteerSaldo(mutatie);
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
        if (!(object instanceof KredietRekening)) {
            return false;
        }
        KredietRekening other = (KredietRekening) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.byonder.zephyrbank.model.KredietRekening[id=" + getId() + "]";
    }

}

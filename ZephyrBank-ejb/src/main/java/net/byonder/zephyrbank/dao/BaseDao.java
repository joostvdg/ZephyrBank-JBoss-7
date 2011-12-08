/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.byonder.zephyrbank.dao;

import java.util.List;
import net.byonder.zephyrbank.dao.impl.PagingInstructions;

/**
 *
 * @author jvdgriendt
 */
public interface BaseDao <T> {
    
    /**
	 * Haalt een entiteit op, op basis van zijn id.
	 *
	 * @param id het id van de entiteit om op te halen.
	 * @return de entiteit die bij het id hoort.
	 */
	T haalOp(long id);

	/**
	 * Bewaart een instantie.
	 *
	 * @param t de instantie om op te slaan.
	 * @return true als gelukt is, false indien de entiteit al bestond.
	 */
	boolean bewaar(T t);

	/**
	 * Update een instantie
	 * 
	 * @param t de instantie om te updaten
	 * @return true als gelukt is
	 */
	boolean update(T t);
	
	/**
	 * @return een lijst met alle objecten.
	 */
	List<T> vindAlle();

    /**
     * @param pagingInstructions pagineer instructies
	 * @return een lijst met alle objecten.
	 */
	List<T> vindAllePaged(PagingInstructions pagingInstructions);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.byonder.zephyrbank.dao.impl;

/**
 *
 * @author jvdgriendt
 */
public class PagingInstructions {
	
	enum SortOrder{
		ASC, DESC
	}
	
	private String sortAttribute;
	
	private int offSet;
	
	private int limit;
	
	private SortOrder sortOrder;
	
	/**
	 * Default constructor.
	 * 
	 * @param offset de start rij van de resultaten die je terug wilt.
	 * @param limit het maximum aantal rijen dat je terug wilt ontvangen
	 * @param sortAttribute het attribute waarop gesorteerd moet worden
	 * @param sortOrder de manier waarop gesorteerd moet worden
	 */
	public PagingInstructions(int offset, int limit, String sortAttribute, SortOrder sortOrder){
		super();
		this.limit = limit;
		this.offSet = offset;
		this.sortAttribute = sortAttribute;
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the sortAttribute
	 */
	public String getSortAttribute() {
		return sortAttribute;
	}

	/**
	 * @param sortAttribute the sortAttribute to set
	 */
	public void setSortAttribute(String sortAttribute) {
		this.sortAttribute = sortAttribute;
	}

	/**
	 * @return the offSet
	 */
	public int getOffSet() {
		return offSet;
	}

	/**
	 * @param offSet the offSet to set
	 */
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the sortOrder
	 */
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

}

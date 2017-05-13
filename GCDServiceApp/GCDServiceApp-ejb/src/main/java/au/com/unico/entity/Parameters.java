package au.com.unico.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * @author - Sunny Singh
 * Parameter Persistence class
 */

@Entity
public class Parameters {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int parameter1;
	private int parameter2;

	public Parameters(){
		
	}
	
	public Parameters(int parameter1, int parameter2){
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the parameter1
	 */
	public int getParameter1() {
		return parameter1;
	}
	/**
	 * @param parameter1 the parameter1 to set
	 */
	public void setParameter1(int parameter1) {
		this.parameter1 = parameter1;
	}
	/**
	 * @return the parameter2
	 */
	public int getParameter2() {
		return parameter2;
	}
	/**
	 * @param parameter2 the parameter2 to set
	 */
	public void setParameter2(int parameter2) {
		this.parameter2 = parameter2;
	}
	
	
}
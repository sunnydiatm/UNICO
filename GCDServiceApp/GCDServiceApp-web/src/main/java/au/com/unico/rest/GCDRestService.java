package au.com.unico.rest;

import java.util.List;

/**
 * @author Sunny Singh
 * RestPushService interface
 *   
 */
public interface GCDRestService {

	/**
	 * It takes two parameters which are added to the JMS queue and message is
	 * returned for the status of the request.
	 * 
	 * @param parameter1
	 * @param parameter2
	 * @return String
	 */
	public String push(int parameter1, int parameter2);

	/**
	 * It returns the list of all the elements which were added to JMS queue.
	 * @return List<Integer>
	 * 
	 */
	public List<Integer> getList();

}
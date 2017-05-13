package au.com.unico.soap;

import java.security.GeneralSecurityException;
import java.util.List;
import javax.jms.JMSException;
import javax.jws.WebService;
import au.com.unico.dao.exception.GCDDaoException;

/**
 * @author Sunny Singh
 * GCDSoapService interface
 *   
 */
@WebService
public interface GCDSoapService {

	/**
	 * Calculate GCD for two numbers which are retrieved from JMS queue. If any of the retrieve number is 
	 * Null (as queue does not have two number/messages) then return null.
	 * @return GCD of two numbers.
	 */
	public Integer gcd() throws GeneralSecurityException, JMSException, GCDDaoException;

	/**
	 * List of all the GCD's which have been calculated and saved in the database.
	 * @return List of GCS's calculated till this service call.
	 */
	public List<Integer> gcdList() throws GeneralSecurityException;

	/**
	 * Sum of all the GCD's which have been calculated and saved in the database.
	 * @return Sum of all calculated GCS's saved till this service call.
	 */
	public int gcdSum() throws GeneralSecurityException;

}
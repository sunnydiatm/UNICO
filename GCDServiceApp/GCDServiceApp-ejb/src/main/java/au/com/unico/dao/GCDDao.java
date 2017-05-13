package au.com.unico.dao;

import java.util.Collection;
import java.util.List;
import au.com.unico.entity.GCD;
import au.com.unico.dao.exception.GCDDaoException;

/**
 * @author Sunny Singh
 * GCD Dao service interface
 *   
 */
public interface GCDDao {

	/**
	 * Will add GCD to the List of integers in DB.
	 * 
	 * @param gcd
	 * 
	 */
	public void saveGCD(Integer gcd) throws GCDDaoException;
	
	/**
	 * Fetches the saved GCDs from DB
	 * @return Collection<GCD>
	 */
	public Collection<GCD> getGCDList();
	
	/**
	 * Gets sum of all GCDs saved till date
	 * @return Integer
	 */
	public Integer getSumOfGCDs();
}

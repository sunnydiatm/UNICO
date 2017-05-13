package au.com.unico.dao;

import java.util.Collection;
import java.util.List;
import au.com.unico.entity.Parameters;
import au.com.unico.dao.exception.GCDDaoException;

public interface ParameterDao {

	/**
	 *@author Sunny Singh
	 * Add input parameters to DB.
	 * @parameter i
	 * @parameter j
	 * @throws GCDDaoException
	 */
	public void saveParameters(Integer i, Integer j) throws GCDDaoException;

	/**
	 * fetch the list of parameters from DB.
	 * 
	 */
	public Collection<Parameters> fetchParameterList();

}

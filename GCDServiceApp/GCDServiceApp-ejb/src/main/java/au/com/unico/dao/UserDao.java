package au.com.unico.dao;

import java.security.GeneralSecurityException;

import au.com.unico.entity.User;

/**
 * @author Sunny Singh
 * User Dao service interface
 *   
 */

public interface UserDao {
	
	public User getUser(String userName, String password);
}

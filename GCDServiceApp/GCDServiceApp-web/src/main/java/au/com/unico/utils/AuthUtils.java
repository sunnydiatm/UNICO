package au.com.unico.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import au.com.unico.dao.UserDao;

/**
 * This class handles Utility methods for authentication
 * @author Sunny Singh
 */

public class AuthUtils {

	/**
	 * String to hold name of the encryption algorithm.
	 */
	public static final String ALGORITHM = "RSA";

	/**
	 * String to hold the name of the private key file.
	 */
	public static final String PRIVATE_KEY_FILE = "/private.key";

	public static void authenticate(UserDao userDao, Map<String, List<String>> httpHeaders)
			throws GeneralSecurityException, FileNotFoundException, IOException, ClassNotFoundException {

		String username = null;
		String password = null;

		List<String> userList = (List<String>) httpHeaders.get("Username");
		List<String> passList = (List<String>) httpHeaders.get("Password");
		if (userList != null && userList.size() > 0) {
			username = userList.get(0).toString();
		}

		if (passList != null && passList.size() > 0) {
			password = passList.get(0).toString();
			byte[] valueDecoded = java.util.Base64.getDecoder().decode(password);

			// Decrypt the cipher text using the private key.
			ObjectInputStream inputStream = new ObjectInputStream(
					(AuthUtils.class.getResourceAsStream(PRIVATE_KEY_FILE)));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			String decryptedPassword = decrypt(valueDecoded, privateKey);
			if (userDao.getUser(username, decryptedPassword) == null) {
				throw new GeneralSecurityException("Invalid Username or password !");
			}
		} else {
			throw new GeneralSecurityException("Missing Username and/or password !");
		}

	}

	/**
	 * Decrypt text using private key.
	 * 
	 * @param text
	 *            :encrypted text
	 * @param key
	 *            :The private key
	 * @return plain text
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws java.lang.Exception
	 */
	private static String decrypt(byte[] text, PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] dectyptedText = null;

		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(ALGORITHM);

		// decrypt the text using the private key
		cipher.init(Cipher.DECRYPT_MODE, key);
		dectyptedText = cipher.doFinal(text);
		return new String(dectyptedText);
	}

}

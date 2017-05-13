package au.com.unico.dao;

import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import au.com.unico.entity.GCD;
import au.com.unico.entity.Parameters;
import au.com.unico.dao.exception.GCDDaoException;

/**
 * @author Sunny Singh
 * GCD Dao service implementation
 *   
 */
@Stateless
public class GCDDaoImpl implements GCDDao {

	@PersistenceContext(unitName = "com.unico.mysql")
	EntityManager em;
	
	/* (non-Javadoc)
	 * @see au.com.unico.dao.GCDDao#saveGCD(java.lang.Integer)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveGCD(Integer gcd) throws GCDDaoException {
		GCD gcdEntityObj = new GCD();
		gcdEntityObj.setComputedGCD(gcd);
		persist(gcdEntityObj);
	}

	private void persist(Object entityObject) throws GCDDaoException {
		try {
			em.persist(entityObject);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GCDDaoException(ex.getMessage());
		} 
		
	}

	/* (non-Javadoc)
	 * @see au.com.unico.dao.GCDDao#getGCDList()
	 */
	@Override
	public Collection<GCD> getGCDList() {
		 Query query = em.createQuery("SELECT g FROM GCD g");
		 return (Collection<GCD>) query.getResultList();
	}

	/* (non-Javadoc)
	 * @see au.com.unico.dao.GCDDao#getSumOfGCDs()
	 */
	@Override
	public Integer getSumOfGCDs() {
		 Query query = em.createQuery("SELECT sum(g.computedGCD) FROM GCD g");
		 return ((Long) query.getSingleResult()).intValue();
	}
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}

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
import au.com.unico.entity.Parameters;
import au.com.unico.dao.exception.GCDDaoException;

@Stateless
public class ParameterDaoImpl implements ParameterDao {

	@PersistenceContext(unitName = "com.unico.mysql")
	EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveParameters(Integer parameter1, Integer parameter2) throws GCDDaoException {
		Parameters parameters = new Parameters();
		parameters.setParameter1(parameter1);
		parameters.setParameter2(parameter2);
		
		try {
			em.persist(parameters);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GCDDaoException(e.getMessage());
		} 
	}


	@Override
	public Collection<Parameters> fetchParameterList() {
		 Query query = em.createQuery("SELECT p FROM Params p");
		 return (Collection<Parameters>) query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}

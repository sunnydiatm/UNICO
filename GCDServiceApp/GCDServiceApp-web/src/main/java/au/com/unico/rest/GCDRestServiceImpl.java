package au.com.unico.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import au.com.unico.dao.ParameterDao;
import au.com.unico.dao.UserDao;
import au.com.unico.entity.Parameters;
import au.com.unico.jms.MessageProducer;
import au.com.unico.jms.Queues;
import au.com.unico.json.JSONMapper;


/**
 * RestPushServiceImpl exposes two GET REST methods push and list.
 * @author Sunny Singh
 *
 */
@Path("/gcd")
public class GCDRestServiceImpl implements GCDRestService {
	
	private Logger logger = LogManager.getLogger(GCDRestServiceImpl.class);
	String name = null;
	
	@Inject
	ParameterDao parameterDao;
	
	@Inject
	UserDao userDao;
	
	@Inject
	MessageProducer producer;
	
	@Inject
	JSONMapper jsonMapper;
	
	@Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
	ConnectionFactory jmsConnectionFactory;

	@Inject 
	Queues queues;
	
	public GCDRestServiceImpl() {

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unico.services.rest.PushService#push(int, int)
	 */
	@GET
	@Path("/push/{parameter1}/{parameter2}")
	@Produces("text/plain")
	public String push(@PathParam("parameter1") int parameter1, @PathParam("parameter2") int parameter2) {
	
		logger.debug("\n parameter1 = " + parameter1 + " .. parameter2 = " + parameter2);
		try {
			producer.send(jmsConnectionFactory, queues.getParamQueue(), parameter1 + "");
			producer.send(jmsConnectionFactory, queues.getParamQueue(), parameter2 + "");
			parameterDao.saveParameters(parameter1, parameter2);
		} catch (Exception e) {
			e.printStackTrace();
			return "Not able to send input to JMS queue: " + e.getMessage();
		} 
		return "Recieved parameters :" + parameter1 + " and " + parameter2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unico.services.rest.PushService#list()
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getList() {
		List<Integer> retList = new ArrayList<>();
		Collection<Parameters> allSavedItems = parameterDao.fetchParameterList();
		for(Parameters parameter : allSavedItems){
			retList.add(parameter.getParameter1());
			retList.add(parameter.getParameter2());
		}
		return retList;
	}
}

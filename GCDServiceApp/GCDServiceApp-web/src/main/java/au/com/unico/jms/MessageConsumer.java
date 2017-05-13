package au.com.unico.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class handles the message consumption from JMS Queue
 * @author Sunny Singh
 */
public class MessageConsumer {
	private Logger logger = LogManager.getLogger(MessageConsumer.class);
	
	/**
	 * This method receives a parameter for a JMS Queue and returns it to the caller.
	 * @param jmsConnectionFactory
	 * @param queue
	 * @return Integer
	 * @exception JMS Exception
	 */

	public Integer receive(ConnectionFactory jmsConnectionFactory, Queue queue) throws JMSException {
		JMSContext context = null;
		try {
			context = jmsConnectionFactory.createContext();
			context.start();
			String param = context.createConsumer(queue).receiveBody(String.class, 1000);
			logger.debug("Received from JMS Queue = " + param);
			if(param != null) {
				return Integer.valueOf(param);
			}
			throw new JMSException("JMS Queue is empty !!");
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}
}

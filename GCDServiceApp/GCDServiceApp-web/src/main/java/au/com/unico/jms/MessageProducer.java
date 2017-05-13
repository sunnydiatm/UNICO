package au.com.unico.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * This class handles the posting of message to JMS Queue
 * @author Sunny Singh
 */
public class MessageProducer {

	/**
	 * Method to post messages to JMS Queue
	 * @param jmsConnectionFactory
	 * @param queue
	 * @param param
	 */
	public void send(ConnectionFactory jmsConnectionFactory, Queue queue, String param) {
		JMSContext context = null;
		try {
			context = jmsConnectionFactory.createContext();
			context.start();
			context.createProducer().send(queue, param);
		} finally {
			if(context != null) {
				context.close();
			}
		}
	}
}

package au.com.unico.jms;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * This class handles the JMS Queues
 * @author Sunny Singh
 */
public class Queues {
	
	@Resource(lookup = "queue/unicoGCDQueue")
	private Queue paramQueue;

	/**
	 * @return
	 */
	public Queue getParamQueue() {
		return paramQueue;
	}

	/**
	 * @param paramQueue
	 */
	public void setParamQueue(Queue paramQueue) {
		this.paramQueue = paramQueue;
	}

	
}

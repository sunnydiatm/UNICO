package au.com.unico.soap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import au.com.unico.SpringWithJNDIRunner;
import au.com.unico.dao.GCDDao;
import au.com.unico.dao.UserDao;
import au.com.unico.entity.GCD;
import au.com.unico.entity.User;
import au.com.unico.jms.MessageConsumer;
import junit.framework.AssertionFailedError;

@RunWith(SpringWithJNDIRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class GCDSoapServiceImplTest {

	
	@Autowired
	MessageConsumer consumer;
	
	@Autowired
	ConnectionFactory jmsConnectionFactory;
	
	@Autowired 
	Queue queue;
	
	@Autowired
	GCDSoapService gcdSoapService;
	
	@Autowired
	GCDDao gcdDao;
	
	@Autowired
	UserDao userDao;
	
	
	@Before
	public void init() throws JMSException {
		
		
		doReturn(new User()).when(userDao).getUser(any(String.class), any(String.class));
		
		Collection<GCD> gcdCollection = new ArrayList<GCD>();
		gcdCollection.add(new GCD(4));
		gcdCollection.add(new GCD(8));
		gcdCollection.add(new GCD(16));
		gcdCollection.add(new GCD(20));
		
		doReturn(gcdCollection).when(gcdDao).getGCDList();
		doAnswer(new Answer<Integer>() {
			private int count = 16;

			public Integer answer(InvocationOnMock invocation) {
				int temp = count;
				count = count + 4;
				return temp;
			}
		}).when(consumer).receive(any(ConnectionFactory.class), any(Queue.class));
		doReturn(new Integer(100)).when(gcdDao).getSumOfGCDs();
	}
	
	@Test
	public void testGCD() {
		try {
			Integer gcd = gcdSoapService.gcd();
			assertEquals(new Integer(4), gcd);
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testGcdList() {
		List<Integer> list;
		try {
			list = gcdSoapService.gcdList();
			assertEquals(new Integer(4), list.get(0));
			assertEquals(new Integer(8), list.get(1));
			assertEquals(new Integer(16), list.get(2));
			assertEquals(new Integer(20), list.get(3));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
	}

	@Test
	public void testGcdSum() {
		try {
			assertEquals(100, gcdSoapService.gcdSum());
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}

package au.com.unico;
import javax.naming.NamingException;
import org.junit.runners.model.InitializationError;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * This class adds the JNDI capabilities to the SpringJUnit4ClassRunner.
 * @author Sunny Singh
 * 
 */
public class SpringWithJNDIRunner extends SpringJUnit4ClassRunner {
    public static boolean isJNDIactive;
    /**
     * JNDI is activated with this constructor.
     * 
     * @param klass
     * @throws InitializationError
     * @throws NamingException
     * @throws IllegalStateException
     */
    public SpringWithJNDIRunner(Class<?> klass) throws InitializationError,
            IllegalStateException, NamingException {
        super(klass);
        synchronized (SpringWithJNDIRunner.class) {
            if (!isJNDIactive) {
                ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test-context.xml");
                SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
                builder.bind("java:comp/DefaultJMSConnectionFactory", applicationContext.getBean("jmsConnectionFactory"));
                builder.bind("queue/unicoGCDQueue", applicationContext.getBean("queue"));
                builder.bind("webServiceContext", applicationContext.getBean("webServiceContext"));
                builder.activate();
                isJNDIactive = true;
            }
        }
    }
}
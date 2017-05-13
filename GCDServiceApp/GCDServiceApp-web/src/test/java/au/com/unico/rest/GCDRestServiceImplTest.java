/**
 * 
 */
package au.com.unico.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import au.com.unico.SpringWithJNDIRunner;
import au.com.unico.dao.GCDDao;
import au.com.unico.dao.ParameterDao;
import au.com.unico.entity.Parameters;

/**
 * @author Sunny Singh
 *
 */
@RunWith(SpringWithJNDIRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class GCDRestServiceImplTest {

	@Autowired
	GCDRestService restService;
	
	@Autowired
	ParameterDao parameterDao;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBeforeClass() throws Exception {
		Collection<Parameters> parameterList = new ArrayList<Parameters>();
		parameterList.add( new Parameters(20, 40));
		parameterList.add( new Parameters(60, 80));
		parameterList.add( new Parameters(80, 90));
		doReturn(parameterList).when(parameterDao).fetchParameterList();
	}

	/**
	 * Test method for {@link au.com.unico.rest.GCDRestServiceImpl#push(int, int)}.
	 */
	@Test
	public void testPush() {
		assertEquals("Recieved parameters :20 and 16",restService.push(20, 16));
	}

	/**
	 * Test method for {@link au.com.unico.rest.GCDRestServiceImpl#getList()}.
	 */
	@Test
	public void testGetList() {
		List<Integer> paramList = restService.getList();
		assertEquals(new Integer(20), paramList.get(0));
		assertEquals(new Integer(40), paramList.get(1));
		assertEquals(new Integer(60), paramList.get(2));
		assertEquals(new Integer(80), paramList.get(3));
		assertEquals(new Integer(80), paramList.get(4));
		assertEquals(new Integer(90), paramList.get(5));
		
	}

}

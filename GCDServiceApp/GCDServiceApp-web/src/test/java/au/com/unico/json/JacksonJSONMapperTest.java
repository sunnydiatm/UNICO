package au.com.unico.json;

import static org.junit.Assert.*;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import au.com.unico.SpringWithJNDIRunner;
import au.com.unico.entity.Parameters;

@RunWith(SpringWithJNDIRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class JacksonJSONMapperTest {

	@Autowired
	JSONMapper jsonMapper;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testToJsonString() {
		try {
			assertEquals("{\"id\":0,\"parameter1\":25,\"parameter2\":50}", jsonMapper.toJsonString(new Parameters(25, 50)));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

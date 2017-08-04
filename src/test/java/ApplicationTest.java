import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;

import com.gurupv.simple.search.GoogleSearch;
import com.gurupv.simple.search.SearchResults;

public class ApplicationTest {
	
	@Test
	public void testGoogleSearch(){
		
		GoogleSearch searchGoogle = GoogleSearch.initialize();
		assertNotNull(searchGoogle);
		
		try {
			SearchResults result = searchGoogle.search("test with space");
			
			assertNotNull(result);
			assertNotNull(result.getItems());
			assertThat(result.getItems(),IsCollectionWithSize.hasSize(10));
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception during search");
		}
		
		
		
	}

}

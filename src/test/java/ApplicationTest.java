import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;

import com.gurupv.simple.pattern.JSLibrarySearcher;
import com.gurupv.simple.search.GoogleSearch;
import com.gurupv.simple.search.SearchResults;

public class ApplicationTest {

	@Test
	public void testGoogleSearch() {

		GoogleSearch searchGoogle = GoogleSearch.initialize();
		assertNotNull(searchGoogle);

		try {
			SearchResults result = searchGoogle.search("test with space");

			assertNotNull(result);
			assertNotNull(result.getItems());
			assertThat(result.getItems(), IsCollectionWithSize.hasSize(10));

		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception during search");
		}
	}

	@Test
	public void testJSLibrarySearcher() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("Test1.html").getFile());

		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Could not read the file");
		}
		String page = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));

		JSLibrarySearcher searcher = new JSLibrarySearcher(page);
		Set<String> jsLinks = searcher.searchJSLinks();

		assertNotNull(jsLinks);
		assertThat(jsLinks, IsCollectionWithSize.hasSize(1));

		File file2 = new File(classLoader.getResource("Test2.html").getFile());

		FileReader reader2 = null;
		try {
			reader2 = new FileReader(file2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Could not read the file");
		}
		String page2 = new BufferedReader(reader2).lines().collect(Collectors.joining("\n"));

		JSLibrarySearcher searcher2 = new JSLibrarySearcher(page2);
		Set<String> jsLinks2 = searcher2.searchJSLinks();

		assertNotNull(jsLinks2);
		assertThat(jsLinks2, IsCollectionWithSize.hasSize(13));

		File file3 = new File(classLoader.getResource("Test3.html").getFile());

		FileReader reader3 = null;
		try {
			reader3 = new FileReader(file3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Could not read the file");
		}
		String page3 = new BufferedReader(reader3).lines().collect(Collectors.joining("\n"));

		JSLibrarySearcher searcher3 = new JSLibrarySearcher(page3);
		Set<String> jsLinks3 = searcher3.searchJSLinks();

		assertNotNull(jsLinks3);
		assertThat(jsLinks3, IsCollectionWithSize.hasSize(1));

		
	}

}

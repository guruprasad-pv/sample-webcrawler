package com.gurupv.simple.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import com.gurupv.simple.pattern.JSLibrarySearcher;

/**
 * 
 * Crawl task- connects to the provided link and searches through the HTML for
 * the JS libraries implements the {@link Callable} interface to be able to run
 * as a thread
 */
public class CrawlTask implements Callable<Set<String>> {

	private URL urltoCrawl;

	/**
	 * 
	 * @param url
	 *            to which the task will connect and searches through the HTML
	 *            page
	 */
	public CrawlTask(String url) {
		try {
			urltoCrawl = new URL(url);
		} catch (MalformedURLException e) {
			// In case the Crawl task cannot be instantiated, the executor
			// service should not fail
			e.printStackTrace();
		}

	}

	@Override
	/**
	 * implementation of the Callable interface, does the main job of crawling
	 * 
	 * @return returns a set of JS libraries found in the pages
	 */
	public Set<String> call() {

		System.out.println("About to Connect to page: " + urltoCrawl);
		Reader reader;
		try {
			reader = new InputStreamReader(urltoCrawl.openStream(), "UTF-8");
			String page = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
			System.out.println("Successfully Obtained the page: " + urltoCrawl);

			JSLibrarySearcher searcher = new JSLibrarySearcher(page);
			return searcher.searchJSLinks();

		} catch (IOException e) {
			// In case of the URLs cannot be read, some urls wont allow
			// anonymous crawlers to connect, The executor service should
			// continue processing the next task.
			// Hence returning an empty result
			e.printStackTrace();
			return new HashSet<String>();
		}
	}

}

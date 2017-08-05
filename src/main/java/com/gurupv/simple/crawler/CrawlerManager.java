package com.gurupv.simple.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.gurupv.simple.search.GoogleSearch;
import com.gurupv.simple.search.SearchResults;

/**
 * 
 * This class spawns of threads to crawl the urls given by the google search
 *
 */
public class CrawlerManager {

	//Spawns 10 threads
	private static int NUMBER_OF_THREADS = 10;

	private ExecutorService service;

	private List<String> resultList;

	private String searchValue;

	/**
	 * 
	 * @param search value. This will be submitted to google search to get the links
	 */
	public CrawlerManager(String search) {
		service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
		resultList = new ArrayList<String>();
		searchValue = search;
	}

	/**
	 * This methods crawls through the links given by google and returns the JS libraries found as a list
	 * @return returns a list of JS libraries found in the pages
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public List<String> startCrawling() throws IOException, InterruptedException {
		
		GoogleSearch googleSearch = GoogleSearch.initialize();
		SearchResults result = googleSearch.search(searchValue);
		
		/*
		 * Convert each result into a List containing of type CrawlTask
		 */
		List<CrawlTask> tasks = result.getItems().stream().map(item -> new CrawlTask(item.getLink()))
				.collect(Collectors.toList());

		/*
		 * Execute all of the Crawlers simultaneously as threads
		 * Collect the results and add them to the final list to be returned
		 */
		service.invokeAll(tasks).stream().map(future -> {
			try {
				return future.get();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}).forEach(response -> resultList.addAll(response));

		return resultList;
	}

}

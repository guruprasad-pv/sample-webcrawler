package com.gurupv.simple.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.gurupv.simple.search.GoogleSearch;
import com.gurupv.simple.search.SearchResults;

public class CrawlerManager {

	private static int NUMBER_OF_THREADS = 10;

	private ExecutorService service;

	private List<String> resultList;

	private String searchValue;

	public CrawlerManager(String search) {
		service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
		resultList = new ArrayList<String>();
		searchValue = search;
	}

	public List<String> startCrawling() throws IOException, InterruptedException {
		GoogleSearch googleSearch = GoogleSearch.initialize();
		SearchResults result = googleSearch.search(searchValue);

		List<CrawlTask> tasks = result.getItems().stream().map(item -> new CrawlTask(item.getLink()))
				.collect(Collectors.toList());

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

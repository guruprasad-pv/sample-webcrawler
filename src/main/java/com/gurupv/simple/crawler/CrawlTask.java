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

public class CrawlTask implements Callable<Set<String>> {

	private URL urltoCrawl;

	public CrawlTask(String url) {
		try {
			urltoCrawl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Override
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
			e.printStackTrace();
			return new HashSet<String>();
		}
	}

}

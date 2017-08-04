package com.gurupv.simple.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class GoogleSearch {

	private static String GOOGLE_API_KEY_NAME = "key";
	private static String CUSTOM_SEARCH_CONTEXT_KEY_NAME = "cx";
	private static String NUM_OF_RESULTS_KEY_NAME = "num";
	private static String SEARCH_KEY_NAME = "q";

	private static String API_KEY = "AIzaSyALBTk39NZlRee-B7SrOyNceg6pMulCADY";
	private static String CUSTOMSEARCH_KEY = "004048710312991882226:_edgi5jm1uu";
	private static String NUM_OF_RESULTS = "10";

	private static String GOOGLE_URL = "https://www.googleapis.com/customsearch/v1?";

	private String searchURL;

	public static GoogleSearch initialize() {

		StringBuffer buff = new StringBuffer();
		buff.append(GOOGLE_URL).append(GOOGLE_API_KEY_NAME).append("=");
		buff.append(API_KEY);
		buff.append("&");
		buff.append(CUSTOM_SEARCH_CONTEXT_KEY_NAME).append("=");
		buff.append(CUSTOMSEARCH_KEY);
		buff.append("&");
		buff.append(NUM_OF_RESULTS_KEY_NAME).append("=");
		buff.append(NUM_OF_RESULTS);
		buff.append("&");
		buff.append(SEARCH_KEY_NAME).append("=");
		GoogleSearch myself = new GoogleSearch();
		myself.setSearchURL(buff.toString());
		return myself;
	}

	public SearchResults search(String searchValue) throws IOException {

		searchURL += URLEncoder.encode(searchValue, "UTF-8");
		
	
		System.out.println("Search URL: "+searchURL);
		
		URL url = new URL(searchURL);
		Reader reader = new InputStreamReader(url.openStream(), "UTF-8");
		String googleResult = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));
		SearchResults result = new Gson().fromJson(googleResult, SearchResults.class);
		return result;

	}

	public String getSearchURL() {
		return searchURL;
	}

	public void setSearchURL(String searchURL) {
		this.searchURL = searchURL;
	}

}

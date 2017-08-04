package com.gurupv.simple.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class SimpleCrawler {

	public void simpleTest() {

		String google = "https://www.googleapis.com/customsearch/v1?key=AIzaSyALBTk39NZlRee-B7SrOyNceg6pMulCADY&cx=004048710312991882226:_edgi5jm1uu&q=wealth&num=2";
		String search = "stackoverflow";
		String charset = "UTF-8";

		URL url = null;
		try {
			url = new URL(google);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reader reader = null;
		try {
			reader = new InputStreamReader(url.openStream(), charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = new BufferedReader(reader).lines().collect(Collectors.joining("\n"));

		System.out.println(result);

		SearchResults results = new Gson().fromJson(result, SearchResults.class);

		results.getItems().forEach(item -> {

			URL pageUrl = null;
			String urlString = item.getFormattedUrl();
			try {
				pageUrl = new URL(urlString);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(pageUrl.openStream()));

				String pageResult = in.lines().collect(Collectors.joining("\n"));
				
				Pattern p = Pattern.compile("<script[^>]*?>");
				Matcher m = p.matcher(pageResult);
				while (m.find()) {
					String matches = m.group();
					System.out.println(matches);
					System.out.println("\n");
					Pattern p1 = Pattern.compile("src=\"([^\"]*)\"?|src='([^']*)'?");
					Matcher m2 = p1.matcher(matches);
					while(m2.find()){
						
						String matches2 = m2.group(2);
						System.out.println(matches2);
						System.out.println("\n");
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(item.getFormattedUrl());

		});

	}

	public static void main(String[] args) {
		new SimpleCrawler().simpleTest();
	}

}

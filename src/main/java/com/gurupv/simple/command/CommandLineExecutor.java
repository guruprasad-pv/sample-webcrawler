package com.gurupv.simple.command;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.gurupv.simple.crawler.CrawlerManager;

/**
 * This class contains the main method. Prompts for the user action in command
 * line for a search value The results are aggregated. Look for aggregation
 * comments
 */
public class CommandLineExecutor {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			String searchValue = "";
			/*
			 * Wait for the user prompt
			 */
			while (true) {
				System.out.print("Enter the search value to crawl for:");
				searchValue = scanner.nextLine();
				if (searchValue.length() > 0)
					break;
			}

			CrawlerManager manager = new CrawlerManager(searchValue);
			List<String> jsLibs;
			try {
				jsLibs = manager.startCrawling();

				/*
				 * The obtained Js libraries are transformed into a map A Map
				 * containing library and the number of times it occurs
				 * 
				 */
				Map<String, Long> jsLibsWithCount = jsLibs.stream()
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

				Map<String, Long> finalAggregation = new LinkedHashMap<>();

				/*
				 * The obtained Map with library and count is further sorted and
				 * only top 6 libraries are collected
				 * 
				 */
				jsLibsWithCount.entrySet().stream().sorted(Map.Entry.<String, Long> comparingByValue().reversed())
						.limit(6).forEachOrdered(entry -> finalAggregation.put(entry.getKey(), entry.getValue()));

				System.out.println("###########TOP Libraries Used#############");

				finalAggregation.forEach((key, value) -> {
					System.out.println("JS Lib: " + key + " ----> used: " + value);

				});

				System.out.println("##########################################");

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
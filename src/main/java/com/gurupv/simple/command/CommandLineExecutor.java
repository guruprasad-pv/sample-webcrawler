package com.gurupv.simple.command;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.gurupv.simple.crawler.CrawlerManager;

public class CommandLineExecutor {

	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			String searchValue = "";
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
				
				Map<String, Long> jsLibsWithCount = jsLibs.stream()
						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

				Map<String, Long> finalAggregation = new LinkedHashMap<>();

				// Sort a map and add to finalMap
				jsLibsWithCount.entrySet().stream().sorted(Map.Entry.<String, Long> comparingByValue().reversed()).limit(6)
						.forEachOrdered(entry -> finalAggregation.put(entry.getKey(), entry.getValue()));

				System.out.println("###########TOP Libraries Used#############");
				
				finalAggregation.forEach((key,value)->{
					System.out.println("JS Lib: "+key+" ----> used: "+value);
					
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
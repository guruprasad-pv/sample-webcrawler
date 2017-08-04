package com.gurupv.simple.pattern;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSLibrarySearcher {

	private static String scriptSearchPattern = "<script[^>]*?>";
	private static String srcTagSearchPattern = "src=\"([^\"]*)\"?|src='([^']*)'?";

	private String htmlText;

	private Set<String> jsLibs;

	public JSLibrarySearcher(String html) {
		this.htmlText = html;
		jsLibs = new HashSet<String>();
	}

	public Set<String> searchJSLinks() {

		Pattern p = Pattern.compile(scriptSearchPattern);
		Matcher m = p.matcher(htmlText);

		while (m.find()) {
			String matches = m.group();
			System.out.println(matches);
			System.out.println("\n");
			Pattern p1 = Pattern.compile(srcTagSearchPattern);
			Matcher m2 = p1.matcher(matches);
			while (m2.find()) {
				int cnt = m2.groupCount();
				System.out.println("Group Count:"+cnt);
				for (int i = 1; i <= cnt; i++) {
					String matches2 = m2.group(i);
					if(matches2!=null){
						int indx1 = matches2.lastIndexOf("/");
						String jslib = matches2.substring(indx1+1);	
						System.out.println(jslib);
						String jslibRefined = jslib.substring(0,jslib.lastIndexOf("."));
						System.out.println(jslibRefined);
						jsLibs.add(jslibRefined);
					}
					System.out.println(matches2);
					System.out.println("\n");

				}
			}
		}
		return jsLibs;
	}
}

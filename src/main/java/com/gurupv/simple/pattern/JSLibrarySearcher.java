package com.gurupv.simple.pattern;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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
		System.out.println("Searching JS libs for page....");
		Pattern p = Pattern.compile(scriptSearchPattern);
		Matcher m = p.matcher(htmlText);

		while (m.find()) {
			String matches = m.group();
			Pattern p1 = Pattern.compile(srcTagSearchPattern);
			Matcher m2 = p1.matcher(matches);
			while (m2.find()) {
				int cnt = m2.groupCount();
				
				IntStream.range(1, cnt+1).forEach(i->{
					String matches2 = m2.group(i);
					if(matches2!=null){
						int indx1 = matches2.lastIndexOf("/");
						String jslib = matches2.substring(indx1+1);	
						
						String jslibRefined = "";
						if(jslib.contains("?")){
							jslibRefined = jslib.substring(0,jslib.lastIndexOf("?"));
						}else if(jslib.contains(".js")){
							jslibRefined = jslib.substring(0,jslib.lastIndexOf("."));
						}else{
							jslibRefined = jslib;
						}
						//System.out.println(jslibRefined);
						jsLibs.add(jslibRefined);
					}

				});
				
			}
		}
		return jsLibs;
	}
}

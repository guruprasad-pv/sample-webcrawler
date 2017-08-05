package com.gurupv.simple.search;

/**
 * 
 * Class used for holding the returned JSON from google search
 *
 */
public class SearchItem {

	private String formattedUrl;
	private String snippet;
	private String link;

	public String getFormattedUrl() {
		return formattedUrl;
	}

	public void setFormattedUrl(String formattedUrl) {
		this.formattedUrl = formattedUrl;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}

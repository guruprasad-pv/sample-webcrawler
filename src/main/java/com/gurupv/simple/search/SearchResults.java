package com.gurupv.simple.search;

import java.util.List;

/**
 * 
 * Class used for holding the returned JSON from google search
 *
 */
public class SearchResults {

	private List<SearchItem> items;

	public List<SearchItem> getItems() {
		return items;
	}

	public void setItems(List<SearchItem> items) {
		this.items = items;
	}

}

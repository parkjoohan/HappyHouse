package com.ssafy.happyhouse.model;

import java.util.Date;

public class NewsDto {
	private int display;
	private Item[] items;
	
	static class Item {
		private String title;
		private String originallink;
		private String link;
		private String description;
		private Date pubDate;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getOriginallink() {
			return originallink;
		}
		public void setOriginallink(String originallink) {
			this.originallink = originallink;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getPubDate() {
			return pubDate;
		}
		public void setPubDate(Date pubDate) {
			this.pubDate = pubDate;
		}
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}
}
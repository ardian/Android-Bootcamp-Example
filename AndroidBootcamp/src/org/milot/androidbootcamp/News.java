package org.milot.androidbootcamp;

import android.graphics.drawable.Drawable;



public class News {
	private String m_Title;
	private String m_Description;
	private String m_PublishedDate;
	private String m_Link;
	private String m_ImageUrl;
	private String m_thumbnailUrl;
	private Drawable m_image;
	
	public News(String title, String description, String publishedDate, String link, String imageUrl, String thumbnailUrl) {
		m_Title = title;
		m_Description = description;
		m_PublishedDate = publishedDate;
		m_Link = link;
		m_ImageUrl = imageUrl;
		m_thumbnailUrl = thumbnailUrl;
	}
	
	public News(String title, String description, String publishedDate, String link, String imageUrl, Drawable image) {
		m_Title = title;
		m_Description = description;
		m_PublishedDate = publishedDate;
		m_Link = link;
		m_ImageUrl = imageUrl;
		m_image = image;
	}
	
	public News() {
		
	}
	
	public Drawable getImage() {
		return m_image;
	}
	
	public void setImage(Drawable image) {
		m_image = image;
	}
	
	public void setImageUrl(String imageUrl) {
		m_ImageUrl = imageUrl;
	}
	
	public void setTitle(String title) {		
		m_Title = title;
	}
	
	public void setDescription(String description) {
		m_Description = description;
	}
	
	public void setPublishedDate(String publishedDate) {
		m_PublishedDate = publishedDate;
	}
	
	public void setLink(String link) {
		m_Link = link;
	}
	
	public void setThumbnailUrl(String thumbnailUrl)
	{
		m_thumbnailUrl = thumbnailUrl;
	}
	
	public String title() {
		return m_Title;
	}
	
	public String imageUrl() {
		return m_ImageUrl;
	}
	
	public String description() {
		return m_Description;
	}
	
	public String publishedDate() {
		return m_PublishedDate;
	}
	
	public String link() {
		return m_Link;
	}
	
	public String thumbnailUrl() {
		return m_thumbnailUrl;
	}
}

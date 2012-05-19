package org.milot.androidbootcamp;


import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.HttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class Parser {
	Context m_Context;
	HttpClient client = null;
	SQLiteDatabase db;
	
	public Parser(Context context) {
		m_Context = context;		
	}
	
	public List<News> fillList(String raw, int sourceId) throws Exception {			
	    	List<News> newsList = new ArrayList<News>();
	    	
	    	if(!newsList.isEmpty()) {
	    		newsList.clear();
	    	}
	    	
	    	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    	Document doc = builder.parse(new InputSource(new StringReader(raw)));
	    	Element root = doc.getDocumentElement();
	    	NodeList items = root.getElementsByTagName("item");
	    		    	
	    	for(int i = 0; i < items.getLength(); i++) {
	    		News newsInstance = new News();
	    		Node item = items.item(i);
	    		NodeList properties = item.getChildNodes();
	    		    		
	    		for(int j = 0; j < properties.getLength(); j++) {
	    			Node property = properties.item(j);
	    			
	    			String name = property.getNodeName();
	    			
	    			if(name.equalsIgnoreCase("title")) {
	    				newsInstance.setTitle(property.getFirstChild().getNodeValue());
	    			} 
	    			else if (name.equalsIgnoreCase("description")) {
	    				newsInstance.setDescription(property.getFirstChild().getNodeValue());
	    			}
	    			else if(name.equalsIgnoreCase("pubDate")) {	    				
	    				newsInstance.setPublishedDate(property.getFirstChild().getNodeValue());
	    			}
	    			else if(name.equalsIgnoreCase("link")) {
	    				newsInstance.setLink(property.getFirstChild().getNodeValue());
	    			}
	    			else if(name.equalsIgnoreCase("imageUrl")) {
	    				newsInstance.setImageUrl(property.getFirstChild().getNodeValue());
	    			}
	    			
	    			else if(name.equalsIgnoreCase("thumbUrl")) {
	    				newsInstance.setImage(getImageFromUrl(property.getFirstChild().getNodeValue()));
	    			}
	    		}
	    		
	    		newsList.add(newsInstance);
	    	}
	    	
	    	return newsList;	
	}
	
	public Drawable getImageFromUrl(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable tmpDrawable = Drawable.createFromStream(is, "image1");
			return tmpDrawable;
		} catch (Exception e) {
			return null;
		}
	}
}

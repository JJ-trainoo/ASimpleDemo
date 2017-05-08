package com.trainoo.spider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class Spider_UrlManager {

	private List<String> new_urls =  new ArrayList<String>();
	private List<String> old_urls =  new ArrayList<String>();
	
	public void add_new_url(String url){
		if(StringUtils.isNotBlank(url)){
			if(!new_urls.contains(url) && !old_urls.contains(url)){
				new_urls.add(url);
			}
		}
	}
	
	public void add_new_urls(List<String> urls){
		if(urls != null && urls.size() > 0){
			for(String url : urls){
				add_new_url(url);
			}
		}
	}
	
	public boolean has_new_url(){
		return new_urls.size() > 0;
	}
	
	public String get_new_url(){
		String new_url = new_urls.get(0);
		new_urls.remove(0);
		old_urls.add(new_url);
		return new_url;
	}
}

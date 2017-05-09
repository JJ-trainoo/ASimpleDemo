package com.trainoo.spider_mutiThread;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 管理需要抓取的url
 * @author zhoutao
 * @date 2017年5月8日
 */
public class Spider_UrlManager {
	
	// 保存未抓取的url
	private List<String> new_urls =  new ArrayList<String>();
	// 保存抓取过了的url
	private List<String> old_urls =  new ArrayList<String>();
	
	/**
	 * 新增一个url,新增的url必须是没有被抓取过的，防止重复
	 * @authod zhoutao
	 * @param url
	 */
	public void add_new_url(String url){
		if(StringUtils.isNotBlank(url)){
			if(!new_urls.contains(url) && !old_urls.contains(url) && url.indexOf(".html") != -1){
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

	public int getNewUrlSize(){
		return new_urls.size();
	}
}

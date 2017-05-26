package com.trainoo.test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;

public class Test_HttpClient {

	public static void main(String[] args) {

		PostMethod method = null;

		try {

			method = new PostMethod("http://www.baidu.com");
			JSONObject jsObj = new JSONObject();
			jsObj.put("blog", "http://www.itblog.com");
			jsObj.put("author", "itblog");

			String transJson = jsObj.toString();
			RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
			method.setRequestEntity(se);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30 * 1000);

			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(method);

			int statuscode = method.getStatusCode();
			if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY) || 
			(statuscode ==HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
			// 读取新的 URL 地址 
			   Header header=method.getResponseHeader("location");
			   if (header!=null){
			      String newuri=header.getValue();
			      if((newuri==null)||(newuri.equals("")))
			         newuri="/";
			         GetMethod redirect=new GetMethod(newuri);
			         client.executeMethod(redirect);
			         System.out.println("Redirect:"+redirect.getStatusLine().toString());
			         redirect.releaseConnection();
			   }else 
			    System.out.println("Invalid redirect");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mm(){
		System.out.println("haha, you got me");
	}
}

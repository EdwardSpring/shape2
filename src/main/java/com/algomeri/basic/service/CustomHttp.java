package com.algomeri.basic.service;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.algomeri.basic.dto.CustomListResponse;
import com.algomeri.basic.dto.CustomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomHttp {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	public <T> CustomResponse<T> fetch(String url) {
		try {
			HttpGet request = new HttpGet(url);
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200) return new CustomResponse<>(false, "unable to make call");
			
			HttpEntity entity = response.getEntity();
			@SuppressWarnings("unchecked")
			CustomResponse<T> dto = new ObjectMapper().readValue(entity.getContent(), CustomResponse.class);
			
			return dto;
		} 
		catch (Exception e) {
			log.error("Could not fetch 5 day weather because: " + e.getMessage());
			return null;
		}
	}
	
	public <T> CustomListResponse<T> fetchList(String url) {
		try {
			HttpGet request = new HttpGet(url);
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode() != 200) return new CustomListResponse<>(false, "too many requests");
			
			HttpEntity entity = response.getEntity();
			@SuppressWarnings("unchecked")
			CustomListResponse<T> dto =  new ObjectMapper().readValue(entity.getContent(), CustomListResponse.class);
			
			return dto;
		} 
		catch (Exception e) {
			log.error("Could not fetch 5 day weather because: " + e.getMessage());
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public <T> CustomResponse<T> fetch(String url) {
//		try {
//			HttpGet request = new HttpGet(url);
//			CloseableHttpClient client = HttpClients.createDefault();
//			CloseableHttpResponse response = client.execute(request);
//			
//			if(response.getStatusLine().getStatusCode() != 200) return new CustomResponse<>(false, "error");
//			
//			HttpEntity entity = response.getEntity();
//			@SuppressWarnings("unchecked")
//			CustomResponse<T> dto = new ObjectMapper().readValue(entity.getContent(), CustomResponse.class);
//			return dto;
//		} 
//		catch (Exception e) {
//			log.warn(e.getMessage());
//			return new CustomResponse<>(false, e.getMessage());
//		}
//	}

}

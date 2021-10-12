package com.algomeri.basic.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algomeri.basic.dto.CustomListResponse;
import com.algomeri.basic.dto.CustomResponse;
import com.algomeri.basic.dto.NextDayResponse;
import com.algomeri.basic.dto.TemperaturePayload;

@Service
public class WeatherService {
	
	private CustomHttp http;
	
	public WeatherService(CustomHttp http) {
		this.http = http;
	}

	public Object get5DaysTemperature(String cityId) {
		try {
			String url = String.format("http://localhost:8002/internal/weather/1/5-day-temperature/%s", cityId);
			CustomResponse<TemperaturePayload> res = http.fetch(url);
			return res;
//			return res != null ? ResponseEntity.ok(res) : ResponseEntity.status(500).body(res);
		} 
		catch (Exception e) {
			return new CustomResponse<>(false, e.getMessage());
		}
	}
	
	public Object getNextDayTemperaturesByLocations(String unit, String cityIds){
		try {
			String url = String.format("http://localhost:8002/internal/weather/summary?units=%s&cityIds=%s", unit, cityIds);
			CustomListResponse<NextDayResponse> ndr = http.fetchList(url);
			return ndr;
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body(new CustomResponse<>(false, e.getMessage()));
		}
	}

}

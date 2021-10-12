package com.algomeri.basic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algomeri.basic.dto.CustomResponse;
import com.algomeri.basic.service.WeatherService;

@RequestMapping("/v1/weather")
@RestController
public class Weather {
	
	private WeatherService service;
	
	public Weather(WeatherService service) {
		this.service = service;
	}

	@GetMapping("summary")
	public  ResponseEntity<Object> getNextDayTemp(@RequestParam("unit") String unit, @RequestParam("locations") String locations) {
		try {
			return ResponseEntity.ok(service.getNextDayTemperaturesByLocations(unit, locations));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new CustomResponse<>(false, "error"));
		}
	}
	
	@GetMapping("locations/{cityId}")
	public  ResponseEntity<Object> get5DayTemp(@PathVariable("cityId") String cityId) {
		return ResponseEntity.ok(service.get5DaysTemperature(cityId));
	}

}




///weather/summary?unit=<celsius|fahrenheit>&locations=<comma separated list of location ids>
///weather/locations/<location_id>

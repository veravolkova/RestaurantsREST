package com.example.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurants.domain.Location;
import com.example.restaurants.domain.LocationRepository;

@RestController
public class LocationController {
	@Autowired
	private LocationRepository repository;

	@RequestMapping("/locations")
	public Iterable<Location> getLocations() {
		return repository.findAll();
	}
}

package com.example.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurants.domain.Restaurant;
import com.example.restaurants.domain.RestaurantRepository;

@RestController
public class RestaurantController {
	@Autowired
	private RestaurantRepository repository;

	@RequestMapping("/restaurants")
	public Iterable<Restaurant> getRestaurants() {
		return repository.findAll();
	}
}

package com.example.restaurants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurants.domain.Tag;
import com.example.restaurants.domain.TagRepository;

@RestController
public class TagController {
	@Autowired
	private TagRepository repository;

	@RequestMapping("/tags")
	public Iterable<Tag> getTags() {
		return repository.findAll();
	}
}

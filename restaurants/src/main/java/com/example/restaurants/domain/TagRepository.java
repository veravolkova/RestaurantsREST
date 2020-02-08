package com.example.restaurants.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends CrudRepository<Tag, Long> {

	List<Tag> findByName(@Param("name") String name);
}
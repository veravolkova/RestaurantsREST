package com.example.restaurants.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends CrudRepository<Location, Long> {

	List<Location> findByLongitudeAndLatitude(@Param("longitude") double longitude, @Param("latitude") double latitude);

}
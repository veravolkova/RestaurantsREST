package com.example.restaurants.domain;

import java.util.List;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	List<Restaurant> findById(long id);

	String distance = "(6371 * acos(cos(radians(:latitude)) * cos(radians(l.latitude)) * cos(radians(l.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(l.latitude)))) ";

	@Query("SELECT r FROM Restaurant r join fetch r.tags t join fetch r.locations l WHERE (lower(r.name) LIKE lower(concat('%', :q,'%')) OR lower(r.description) LIKE lower(concat('%', :q,'%'))" 
			+ "OR lower(t.name) LIKE lower(concat('%', :q,'%'))) AND ( "
			+ distance + " < 3)")
	List<Restaurant> findByNameOrDescriptionOrTagsNameNearby(@Param("q") @Size(min = 1) String q,
			@Param("longitude") double longitude, @Param("latitude") double latitude);

}
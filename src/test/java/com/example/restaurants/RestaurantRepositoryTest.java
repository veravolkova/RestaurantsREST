package com.example.restaurants;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.restaurants.domain.Location;
import com.example.restaurants.domain.LocationRepository;
import com.example.restaurants.domain.Restaurant;
import com.example.restaurants.domain.RestaurantRepository;
import com.example.restaurants.domain.Tag;
import com.example.restaurants.domain.TagRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RestaurantRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RestaurantRepository repository;

	@Autowired
	private TagRepository trepository;

	@Autowired
	private LocationRepository lrepository;

	Restaurant restaurant = new Restaurant("3I8lGUw;;Pu00X;4ygP0Jci:j;I", "Helsinki", "EUR", 390, "Asenneburgeri",
			"https://prod-wolt-venue-images-cdn.wolt.com/5b348b31fe8992000bbec771/2be8c7738b220df2f9a0974da5c90d90",
			"Social Burgerjoint Citycenter", false);

	@Test
	public void saveRestaurant() {

		entityManager.persistAndFlush(restaurant);
		assertThat(restaurant.getId()).isNotNull();
	}

	@Test
	public void deleteRestaurants() {

		entityManager.persistAndFlush(restaurant);
		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}

	@Test
	public void myTest() throws Exception {

		Tag tag1 = new Tag("hamburger");
		Tag tag2 = new Tag("fries");

		double a = 24.941325187683105;
		double b = 60.169938852212965;
		Location location1 = new Location(a, b);

		restaurant.getTags().add(tag1);
		restaurant.getTags().add(tag2);
		restaurant.getLocations().add(location1);

		tag1.getRestaurants().add(restaurant);
		tag2.getRestaurants().add(restaurant);

		location1.getRestaurants().add(restaurant);

		trepository.save(tag1);
		trepository.save(tag2);
		lrepository.save(location1);

		repository.save(restaurant);

		List<?> queryResult = repository.findByNameOrDescriptionOrTagsNameNearby("fries", 24.941325187683105,
				60.169938852212965);

		assertFalse(queryResult.isEmpty());
		assertNotNull(queryResult.get(0));
	}

}

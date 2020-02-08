package com.example.restaurants;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.restaurants.domain.Location;
import com.example.restaurants.domain.LocationRepository;
import com.example.restaurants.domain.Restaurant;
import com.example.restaurants.domain.RestaurantRepository;
import com.example.restaurants.domain.Tag;
import com.example.restaurants.domain.TagRepository;

import java.io.BufferedReader;

@SpringBootApplication
public class RestaurantsApplication {
	@Autowired
	private RestaurantRepository repository;

	@Autowired
	private TagRepository trepository;

	@Autowired
	private LocationRepository lrepository;

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/api");
		SpringApplication.run(RestaurantsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			// find the file in src/main/resources/json
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/restaurants.json");
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}
			}

			JSONObject obj = new JSONObject(stringBuilder.toString());
			JSONArray restaurants = obj.getJSONArray("restaurants");

			for (int i = 0; i < restaurants.length(); i++) {

				String blurhash = restaurants.getJSONObject(i).getString("blurhash");
				String city = restaurants.getJSONObject(i).getString("city");
				String currency = restaurants.getJSONObject(i).getString("currency");
				int deliveryPrice = restaurants.getJSONObject(i).getInt("delivery_price");
				String description = restaurants.getJSONObject(i).getString("description");
				String image = restaurants.getJSONObject(i).getString("image");
				String name = restaurants.getJSONObject(i).getString("name");
				boolean online = restaurants.getJSONObject(i).getBoolean("online");

				Restaurant restaurant = new Restaurant(blurhash, city, currency, deliveryPrice, description, image,
						name, online);

				JSONObject subObj = restaurants.getJSONObject(i);

				// get location (long, lat) within restaurant object
				JSONArray locationArray = subObj.getJSONArray("location");

				double longitude = locationArray.getDouble(0);
				double latitude = locationArray.getDouble(1);
				Location myLocation = new Location(longitude, latitude);

				restaurant.getLocations().add(myLocation);
				myLocation.getRestaurants().add(restaurant);

				// get tags within restaurant object
				JSONArray tagsArray = subObj.getJSONArray("tags");
				ArrayList<Tag> tagList = new ArrayList<Tag>();

				for (int k = 0; k < tagsArray.length(); k++) {

					String value = tagsArray.getString(k);
					Tag tag = new Tag(value);
					tagList.add(tag);

				}

				// one restr. object has 3 tags to the max
				if (tagList.size() > 0 && tagList.size() < 4) {
					
					for (Tag tag : tagList) {
						restaurant.getTags().add(tag);
						
					}
				} else {
					
					restaurant.getTags().add(null);
					
				}		
				
				
				for (Tag tag : tagList) {
					
					tag.getRestaurants().add(restaurant);
					trepository.save(tag);
					
				}

				lrepository.save(myLocation);
				repository.save(restaurant);
			}

		};
	}
}
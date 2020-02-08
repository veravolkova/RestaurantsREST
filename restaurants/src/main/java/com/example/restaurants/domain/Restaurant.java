package com.example.restaurants.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.JoinColumn;

@Entity
public class Restaurant {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)	
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	private long id;
	private String blurhash, city, currency, description, image, name;
	private int delivery_price;
	boolean online;

	// One restr. may have several locations (chains) and there may be several
	// restr. within one location (for ex., in "Tripla")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "restaurant_location", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "locid") })
	private Set<Location> locations = new HashSet<>();

	// One restrt. may have several tags, one tag may refer to many restr.
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "restaurant_tag", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "tagid") })
	private Set<Tag> tags = new HashSet<>();

	public Restaurant() {
	}

	public Restaurant(String blurhash, String city, String currency, int delivery_price, String description,
			String image, String name, boolean online) {
		super();
		this.blurhash = blurhash;
		this.city = city;
		this.currency = currency;
		this.delivery_price = delivery_price;
		this.description = description;
		this.image = image;
		this.name = name;
		this.online = online;

	}

	public Restaurant(String blurhash, String city, String currency, int delivery_price, String description,
			String image, String name, boolean online, Set<Location> locations, Set<Tag> tags) {
		super();
		this.blurhash = blurhash;
		this.city = city;
		this.currency = currency;
		this.delivery_price = delivery_price;
		this.description = description;
		this.image = image;
		this.locations = locations;
		this.name = name;
		this.online = online;
		this.tags = tags;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBlurhash() {
		return blurhash;
	}

	public void setBlurhash(String blurhash) {
		this.blurhash = blurhash;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getDelivery_Price() {
		return delivery_price;
	}

	public void setDelivery_Price(int delivery_price) {
		this.delivery_price = delivery_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

}

package hello;

import java.util.ArrayList;

public class HotelResponse {
	
	 private String property_code;
	 private String property_name;
	 Location LocationObject;
	 Address AddressObject;
	 Total_price Total_priceObject;
	 Min_daily_rate Min_daily_rateObject;
	 ArrayList < Contact > contacts = new ArrayList < Contact > ();
	 ArrayList < Room > rooms = new ArrayList < Room > ();
	 _links _linksObject;


	 // Getter Methods 

	 public String getProperty_code() {
	  return property_code;
	 }

	 public String getProperty_name() {
	  return property_name;
	 }

	 public Location getLocation() {
	  return LocationObject;
	 }

	 public Address getAddress() {
	  return AddressObject;
	 }

	 public Total_price getTotal_price() {
	  return Total_priceObject;
	 }

	 public Min_daily_rate getMin_daily_rate() {
	  return Min_daily_rateObject;
	 }

	 public _links get_links() {
	  return _linksObject;
	 }

	 // Setter Methods 

	 public void setProperty_code(String property_code) {
	  this.property_code = property_code;
	 }

	 public void setProperty_name(String property_name) {
	  this.property_name = property_name;
	 }

	 public void setLocation(Location locationObject) {
	  this.LocationObject = locationObject;
	 }

	 public void setAddress(Address addressObject) {
	  this.AddressObject = addressObject;
	 }

	 public void setTotal_price(Total_price total_priceObject) {
	  this.Total_priceObject = total_priceObject;
	 }

	 public void setMin_daily_rate(Min_daily_rate min_daily_rateObject) {
	  this.Min_daily_rateObject = min_daily_rateObject;
	 }

	 public void set_links(_links _linksObject) {
	  this._linksObject = _linksObject;
	 }
	}

	class _links {
	 More_rooms_at_this_hotel More_rooms_at_this_hotelObject;


	 // Getter Methods 

	 public More_rooms_at_this_hotel getMore_rooms_at_this_hotel() {
	  return More_rooms_at_this_hotelObject;
	 }

	 // Setter Methods 

	 public void setMore_rooms_at_this_hotel(More_rooms_at_this_hotel more_rooms_at_this_hotelObject) {
	  this.More_rooms_at_this_hotelObject = more_rooms_at_this_hotelObject;
	 }
	}
	
	class More_rooms_at_this_hotel {
	 private String href;


	 // Getter Methods 

	 public String getHref() {
	  return href;
	 }

	 // Setter Methods 

	 public void setHref(String href) {
	  this.href = href;
	 }
	}
	
	class Min_daily_rate {
	 private String amount;
	 private String currency;


	 // Getter Methods 

	 public String getAmount() {
	  return amount;
	 }

	 public String getCurrency() {
	  return currency;
	 }

	 // Setter Methods 

	 public void setAmount(String amount) {
	  this.amount = amount;
	 }

	 public void setCurrency(String currency) {
	  this.currency = currency;
	 }
	}
	
	class Total_price {
	 private String amount;
	 private String currency;


	 // Getter Methods 

	 public String getAmount() {
	  return amount;
	 }

	 public String getCurrency() {
	  return currency;
	 }

	 // Setter Methods 

	 public void setAmount(String amount) {
	  this.amount = amount;
	 }

	 public void setCurrency(String currency) {
	  this.currency = currency;
	 }
	}
	
	class Address {
	 private String line1;
	 private String city;
	 private String region;
	 private String postal_code;
	 private String country;


	 // Getter Methods 

	 public String getLine1() {
	  return line1;
	 }

	 public String getCity() {
	  return city;
	 }

	 public String getRegion() {
	  return region;
	 }

	 public String getPostal_code() {
	  return postal_code;
	 }

	 public String getCountry() {
	  return country;
	 }

	 // Setter Methods 

	 public void setLine1(String line1) {
	  this.line1 = line1;
	 }

	 public void setCity(String city) {
	  this.city = city;
	 }

	 public void setRegion(String region) {
	  this.region = region;
	 }

	 public void setPostal_code(String postal_code) {
	  this.postal_code = postal_code;
	 }

	 public void setCountry(String country) {
	  this.country = country;
	 }
	}
	
	class Location {
	 private float latitude;
	 private float longitude;


	 // Getter Methods 

	 public float getLatitude() {
	  return latitude;
	 }

	 public float getLongitude() {
	  return longitude;
	 }

	 // Setter Methods 

	 public void setLatitude(float latitude) {
	  this.latitude = latitude;
	 }

	 public void setLongitude(float longitude) {
	  this.longitude = longitude;
	 }
}

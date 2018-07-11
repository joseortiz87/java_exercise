package hello;

import java.util.ArrayList;

public class Room {
	 private String booking_code;
	 private String room_type_code;
	 private String rate_plan_code;
	 Total_amount Total_amountObject;
	 ArrayList < Rate > rates = new ArrayList < Rate > ();
	 ArrayList < String > descriptions = new ArrayList < String > ();
	 Room_type_info Room_type_infoObject;
	 private String rate_type_code;

	 // Getter Methods 

	 public String getBooking_code() {
	  return booking_code;
	 }

	 public String getRoom_type_code() {
	  return room_type_code;
	 }

	 public String getRate_plan_code() {
	  return rate_plan_code;
	 }

	 public Total_amount getTotal_amount() {
	  return Total_amountObject;
	 }

	 public Room_type_info getRoom_type_info() {
	  return Room_type_infoObject;
	 }

	 public String getRate_type_code() {
	  return rate_type_code;
	 }

	 // Setter Methods 

	 public void setBooking_code(String booking_code) {
	  this.booking_code = booking_code;
	 }

	 public void setRoom_type_code(String room_type_code) {
	  this.room_type_code = room_type_code;
	 }

	 public void setRate_plan_code(String rate_plan_code) {
	  this.rate_plan_code = rate_plan_code;
	 }

	 public void setTotal_amount(Total_amount total_amountObject) {
	  this.Total_amountObject = total_amountObject;
	 }

	 public void setRoom_type_info(Room_type_info room_type_infoObject) {
	  this.Room_type_infoObject = room_type_infoObject;
	 }

	 public void setRate_type_code(String rate_type_code) {
	  this.rate_type_code = rate_type_code;
	 }
	}
	class Room_type_info {


	 // Getter Methods 



	 // Setter Methods 


	}
	class Total_amount {
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

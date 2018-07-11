package hello;

public class Rate {
	 private String start_date;
	 private String end_date;
	 private String currency_code;
	 private float price;


	 // Getter Methods 

	 public String getStart_date() {
	  return start_date;
	 }

	 public String getEnd_date() {
	  return end_date;
	 }

	 public String getCurrency_code() {
	  return currency_code;
	 }

	 public float getPrice() {
	  return price;
	 }

	 // Setter Methods 

	 public void setStart_date(String start_date) {
	  this.start_date = start_date;
	 }

	 public void setEnd_date(String end_date) {
	  this.end_date = end_date;
	 }

	 public void setCurrency_code(String currency_code) {
	  this.currency_code = currency_code;
	 }

	 public void setPrice(float price) {
	  this.price = price;
	 }
}

package review.model;

public class Restaurants {
  protected int restaurantId;//surrogate key
  protected String restaurantName;//1
  protected String description;//2
  protected String menu;//3
  protected String hours;//4
  protected boolean active;//5
  protected Cuisine cuisine;//6
  protected String street1;//7
  protected String street2;//8
  protected String city;//9
  protected String state;//10
  protected int zip;//11
  protected Companies company;//12

  public enum Cuisine{
    AFRICAN, AMERICAN, ASIAN, EUROPEAN, HISPANIC
  }

  public Restaurants(int restaurantId, String restaurantName, String description, String menu,
      String hours, boolean active, Cuisine cuisine, String street1, String street2, String city,
      String state, int zip, Companies company) {
    this.restaurantId = restaurantId;
    this.restaurantName = restaurantName;
    this.description = description;
    this.menu = menu;
    this.hours = hours;
    this.active = active;
    this.cuisine = cuisine;
    this.street1 = street1;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.company=company;
  }

  public Restaurants(String restaurantName, String description, String menu, String hours,
      boolean active, Cuisine cuisine, String street1, String street2, String city, String state,
      int zip, Companies company) {
    this.restaurantName = restaurantName;
    this.description = description;
    this.menu = menu;
    this.hours = hours;
    this.active = active;
    this.cuisine = cuisine;
    this.street1 = street1;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.company = company;
  }

  public Restaurants(int restaurantId) {
    this.restaurantId = restaurantId;
  }

  public Restaurants(int restaurantId, Companies company) {
    this.restaurantId = restaurantId;
    this.company=company;
  }

  public int getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(int restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

  public String getHours() {
    return hours;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Cuisine getCuisine() {
    return cuisine;
  }

  public void setCuisine(Cuisine cuisine) {
    this.cuisine = cuisine;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public int getZip() {
    return zip;
  }

  public void setZip(int zip) {
    this.zip = zip;
  }

  public Companies getCompany() {
    return company;
  }

  public void setCompany(Companies company) {
    this.company = company;
  }
}

package review.model;

public class SitDownRestaurants extends Restaurants{
  protected int capacity;

  public SitDownRestaurants(int restaurantId, String restaurantName, String description,
      String menu,
      String hours, boolean active, Cuisine cuisine, String street1, String street2, String city,
      String state, int zip, Companies company, int capacity) {
    super(restaurantId, restaurantName, description, menu, hours, active, cuisine, street1, street2,
        city, state, zip, company);
    this.capacity = capacity;
  }

  public SitDownRestaurants(String restaurantName, String description, String menu, String hours,
      boolean active, Cuisine cuisine, String street1, String street2, String city, String state,
      int zip, Companies company, int capacity) {
    super(restaurantName, description, menu, hours, active, cuisine, street1, street2, city, state,
        zip, company);
    this.capacity = capacity;
  }

  public SitDownRestaurants(int restaurantId, Companies company, int capacity) {
    super(restaurantId, company);
    this.capacity = capacity;
  }

  public SitDownRestaurants(int restaurantId, int capacity) {
    super(restaurantId);
    this.capacity = capacity;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}

package review.model;

public class TakeOutRestaurants extends Restaurants{
  protected int maxWaitTime;

  public TakeOutRestaurants(int restaurantId, String restaurantName, String description,
      String menu,
      String hours, boolean active, Cuisine cuisine, String street1, String street2, String city,
      String state, int zip, Companies company, int maxWaitTime) {
    super(restaurantId, restaurantName, description, menu, hours, active, cuisine, street1, street2,
        city, state, zip, company);
    this.maxWaitTime = maxWaitTime;
  }

  public TakeOutRestaurants(String restaurantName, String description, String menu, String hours,
      boolean active, Cuisine cuisine, String street1, String street2, String city, String state,
      int zip, Companies company, int maxWaitTime) {
    super(restaurantName, description, menu, hours, active, cuisine, street1, street2, city, state,
        zip, company);
    this.maxWaitTime = maxWaitTime;
  }

  public TakeOutRestaurants(int restaurantId, Companies company, int maxWaitTime) {
    super(restaurantId, company);
    this.maxWaitTime = maxWaitTime;
  }

  public TakeOutRestaurants(int restaurantId, int maxWaitTime) {
    super(restaurantId);
    this.maxWaitTime = maxWaitTime;
  }

  public int getMaxWaitTime() {
    return maxWaitTime;
  }

  public void setMaxWaitTime(int maxWaitTime) {
    this.maxWaitTime = maxWaitTime;
  }

}

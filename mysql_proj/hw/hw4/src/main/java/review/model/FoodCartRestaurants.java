package review.model;

public class FoodCartRestaurants extends Restaurants{
  protected boolean licensed;

  public FoodCartRestaurants(int restaurantId, String restaurantName, String description,
      String menu,
      String hours, boolean active, Cuisine cuisine, String street1, String street2, String city,
      String state, int zip, Companies company, boolean licensed) {
    super(restaurantId, restaurantName, description, menu, hours, active, cuisine, street1, street2,
        city, state, zip, company);
    this.licensed = licensed;
  }

  public FoodCartRestaurants(String restaurantName, String description, String menu, String hours,
      boolean active, Cuisine cuisine, String street1, String street2, String city, String state,
      int zip, Companies company, boolean licensed) {
    super(restaurantName, description, menu, hours, active, cuisine, street1, street2, city, state,
        zip, company);
    this.licensed = licensed;
  }

  public FoodCartRestaurants(int restaurantId, Companies company, boolean licensed) {
    super(restaurantId, company);
    this.licensed = licensed;
  }

  public FoodCartRestaurants(int restaurantId, boolean licensed) {
    super(restaurantId);
    this.licensed = licensed;
  }

  public boolean isLicensed() {
    return licensed;
  }

  public void setLicensed(boolean licensed) {
    this.licensed = licensed;
  }
}

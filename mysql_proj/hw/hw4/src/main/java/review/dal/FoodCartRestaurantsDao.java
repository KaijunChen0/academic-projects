package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import review.model.Companies;
import review.model.FoodCartRestaurants;
import review.model.Restaurants;

public class FoodCartRestaurantsDao extends RestaurantsDao{
//  protected ConnectionManager connectionManager;//no need because it will call super class's connectionManager everytime call connection.
  private static FoodCartRestaurantsDao instance = null;
  protected FoodCartRestaurantsDao() {
    super();
  }
  public static FoodCartRestaurantsDao getInstance() {
    if(instance == null) {
      instance = new FoodCartRestaurantsDao();
    }
    return instance;
  }

//  1. create
  public FoodCartRestaurants create(FoodCartRestaurants foodCartRestaurant) throws SQLException{
    Restaurants createdRestaurant=create(new Restaurants(foodCartRestaurant.getRestaurantName(),
        foodCartRestaurant.getDescription(), foodCartRestaurant.getMenu(), foodCartRestaurant.getHours(),
        foodCartRestaurant.isActive(), foodCartRestaurant.getCuisine(), foodCartRestaurant.getStreet1(),
        foodCartRestaurant.getStreet2(), foodCartRestaurant.getCity(), foodCartRestaurant.getState(),
        foodCartRestaurant.getZip(), foodCartRestaurant.getCompany()));

    String insertFoodCart="INSERT INTO FoodCartRestaurant(RestaurantId,Licensed) VALUES(?,?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;

    try{
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertFoodCart);

      insertStmt.setInt(1, createdRestaurant.getRestaurantId());
      insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());
      insertStmt.executeUpdate();
      foodCartRestaurant.setRestaurantId(createdRestaurant.getRestaurantId());
      return foodCartRestaurant;

    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally {
      if(connection!=null){
        connection.close();
      }if(insertStmt!=null){
        insertStmt.close();
      }
    }
  }

//  2. get by id
  public FoodCartRestaurants getFoodCartRestaurantById(int foodCartRestaurantId) throws SQLException{
    String selectFoodCart="SELECT FoodCartRestaurant.RestaurantId "
        + "AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,Licensed "
        + "FROM FoodCartRestaurant INNER JOIN Restaurants "
        + "ON FoodCartRestaurant.RestaurantId=Restaurants.RestaurantId "
        + "WHERE FoodCartRestaurant.RestaurantId=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectFoodCart);

      selectStmt.setInt(1, foodCartRestaurantId);
      results=selectStmt.executeQuery();
      CompaniesDao companiesDao=new CompaniesDao();

      if(results.next()){
        int resultRestaurantId=results.getInt("RestaurantId");
        String name=results.getString("Name");
        String description=results.getString("Description");
        String menu=results.getString("Menu");
        String hours=results.getString("Hours");
        boolean active=results.getBoolean("Active");
        Restaurants.Cuisine cuisineType=Restaurants.Cuisine.valueOf(results.getString("CuisineType"));//enum type
        String street1=results.getString("Street1");
        String street2=results.getString("Street2");
        String city=results.getString("City");
        String state=results.getString("State");
        int zip=results.getInt("Zip");
        String companyName=results.getString("CompanyName");
        Companies company=companiesDao.getCompanyByCompanyName(companyName);

        boolean licensed=results.getBoolean("Licensed");
        FoodCartRestaurants foodCartRestaurant=new FoodCartRestaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company, licensed);
        return foodCartRestaurant;
      }

    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

//  3. get by comapny name
  public List<FoodCartRestaurants> getFoodCartRestaurantsByCompanyName(String companyName) throws SQLException{
    String selectFoodCart="SELECT \n"
        + "  FoodCartRestaurant.RestaurantId,\n"
        + "  Restaurants.Name,\n"
        + "  Restaurants.Description, \n"
        + "  Restaurants.Menu,\n"
        + "  Restaurants.Hours,\n"
        + "  Restaurants.Active,\n"
        + "  Restaurants.CuisineType, \n"
        + "  Restaurants.Street1,\n"
        + "  Restaurants.Street2,\n"
        + "  Restaurants.City,\n"
        + "  Restaurants.State, \n"
        + "  Restaurants.Zip,\n"
        + "  Restaurants.CompanyName,\n"
        + "  FoodCartRestaurant.Licensed \n"
        + "FROM \n"
        + "  FoodCartRestaurant \n"
        + "INNER JOIN \n"
        + "  Restaurants \n"
        + "ON \n"
        + "  FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId\n"
        + "WHERE \n"
        + "  Restaurants.CompanyName = ?;\n";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;
    List<FoodCartRestaurants> foodCartRestaurants=new ArrayList<>();

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectFoodCart);

      selectStmt.setString(1, companyName);
      results=selectStmt.executeQuery();
      CompaniesDao companiesDao=new CompaniesDao();

      while(results.next()){
        int resultRestaurantId=results.getInt("RestaurantId");
        String name=results.getString("Name");
        String description=results.getString("Description");
        String menu=results.getString("Menu");
        String hours=results.getString("Hours");
        boolean active=results.getBoolean("Active");
        Restaurants.Cuisine cuisineType=Restaurants.Cuisine.valueOf(results.getString("CuisineType"));//enum type
        String street1=results.getString("Street1");
        String street2=results.getString("Street2");
        String city=results.getString("City");
        String state=results.getString("State");
        int zip=results.getInt("Zip");
        String companyName1=results.getString("CompanyName");
        Companies company=companiesDao.getCompanyByCompanyName(companyName1);
        boolean licensed=results.getBoolean("Licensed");
        FoodCartRestaurants foodCartRestaurant=new FoodCartRestaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company, licensed);
        foodCartRestaurants.add(foodCartRestaurant);
      }

    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return foodCartRestaurants;
  }

//  4. delete
  public FoodCartRestaurants delete(FoodCartRestaurants foodCartRestaurant) throws SQLException{
    String deleteFoodCart="DELETE FROM FoodCartRestaurant WHERE RestaurantId=?;";
    Connection connection=null;
    PreparedStatement deleteStmt=null;

    try{
      connection=connectionManager.getConnection();
      deleteStmt=connection.prepareStatement(deleteFoodCart);
      deleteStmt.setInt(1, foodCartRestaurant.getRestaurantId());

      deleteStmt.executeUpdate();

      // Then also delete from the superclass.
      // Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
      // super.delete() without even needing to delete from Administrators first.
      super.delete(foodCartRestaurant);

      return null;
    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally{
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }


}

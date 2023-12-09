package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import review.model.Companies;
import review.model.Restaurants;

public class RestaurantsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static RestaurantsDao instance = null;
  protected RestaurantsDao() {
    connectionManager = new ConnectionManager();
  }
  public static RestaurantsDao getInstance() {
    if(instance == null) {
      instance = new RestaurantsDao();
    }
    return instance;
  }

//  1. create
  public Restaurants create(Restaurants restaurant) throws SQLException{
    String insertRestaurant="INSERT INTO Restaurants(Name,Description,Menu,Hours,Active,CuisineType,"
        + "Street1,Street2,City,State,Zip,CompanyName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;
    ResultSet resultKey = null;

    try{
      connection=connectionManager.getConnection();
      insertStmt=connection.prepareStatement(insertRestaurant, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, restaurant.getRestaurantName());
      insertStmt.setString(2, restaurant.getDescription());
      insertStmt.setString(3, restaurant.getMenu());
      insertStmt.setString(4, restaurant.getHours());
      insertStmt.setBoolean(5, restaurant.isActive());
      insertStmt.setString(6, restaurant.getCuisine().name());//get enum type
      insertStmt.setString(7, restaurant.getStreet1());
      insertStmt.setString(8, restaurant.getStreet2());
      insertStmt.setString(9, restaurant.getCity());
      insertStmt.setString(10, restaurant.getState());
      insertStmt.setInt(11, restaurant.getZip());
      insertStmt.setString(12, restaurant.getCompany().getCompanyName());

      insertStmt.executeUpdate();

      //retrieve the auto-generated key and set it
      resultKey=insertStmt.getGeneratedKeys();
      int restaurantId=-1;
      if(resultKey.next()){
        restaurantId=resultKey.getInt(1);//If there is a result, this line retrieves the first column's value from resultKey, which is the auto-generated comment ID, and assigns it to commentId.
      }else{
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      restaurant.setRestaurantId(restaurantId);//The commentId retrieved from the generated keys is set back on the blogComment object
      return restaurant;//the updated blogComment object (now containing the generated ID) is returned

    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }

//  2. get restaurant by id
  public Restaurants getRestaurantById(int restaurantId) throws SQLException{
    String selectRestaurant="SELECT RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,"
        + "Street1,Street2,City,State,Zip,CompanyName "
        + "FROM Restaurants "
        + "WHERE RestaurantId=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try {
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectRestaurant);
      selectStmt.setInt(1, restaurantId);

      results=selectStmt.executeQuery();

      CompaniesDao companiesDao=CompaniesDao.getInstance();

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
        Restaurants restaurant=new Restaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company);
        return restaurant;
      }

    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally{
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

//  3. get restaurant by cuisine type
  public List<Restaurants> getRestaurantsByCuisine(Restaurants.Cuisine cuisine) throws SQLException{
    List<Restaurants> restaurants=new ArrayList<>();
    String selectRestaurant="SELECT RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,"
        + "Street1,Street2,City,State,Zip,CompanyName "
        + "FROM Restaurants "
        + "WHERE CuisineType=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectRestaurant);
      selectStmt.setString(1, cuisine.name());

      results=selectStmt.executeQuery();

      CompaniesDao companiesDao=CompaniesDao.getInstance();

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
        String companyName=results.getString("CompanyName");
        Companies company=companiesDao.getCompanyByCompanyName(companyName);
        Restaurants restaurant=new Restaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company);
        restaurants.add(restaurant);
      }

    }catch (SQLException e){
      e.printStackTrace();
      throw e;
    }finally{
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
    return restaurants;
  }

//  4. get restaurant by company name
  public List<Restaurants> getRestaurantsByCompanyName(String companyName) throws SQLException{
    List<Restaurants> restaurants=new ArrayList<>();
    String selectRestaurant="SELECT RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,"
        + "Street1,Street2,City,State,Zip,CompanyName "
        + "FROM Restaurants "
        + "WHERE CompanyName=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectRestaurant);
      selectStmt.setString(1, companyName);

      results=selectStmt.executeQuery();

      CompaniesDao companiesDao=CompaniesDao.getInstance();

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
        String resultCompanyName=results.getString("CompanyName");
        Companies company=companiesDao.getCompanyByCompanyName(resultCompanyName);

        Restaurants restaurant=new Restaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company);
        restaurants.add(restaurant);
      }
    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally{
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
    return restaurants;
  }

//  5. delete
  public Restaurants delete(Restaurants restaurant) throws SQLException{
    String deleteRestaurant="DELETE FROM Restaurants WHERE RestaurantId=?;";
    Connection connection=null;
    PreparedStatement deleteStmt=null;

    try{
      connection=connectionManager.getConnection();
      deleteStmt=connection.prepareStatement(deleteRestaurant);

      deleteStmt.setInt(1, restaurant.getRestaurantId());
      deleteStmt.executeUpdate();
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

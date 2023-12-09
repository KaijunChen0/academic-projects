package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import review.model.Companies;
import review.model.Restaurants;
import review.model.SitDownRestaurants;
import review.model.TakeOutRestaurants;

public class TakeOutRestaurantsDao extends RestaurantsDao {
//  protected ConnectionManager connectionManager;//no need because it will call super class's connectionManager everytime call connection.
  private static TakeOutRestaurantsDao instance = null;
  protected TakeOutRestaurantsDao() {
    super();
  }
  public static TakeOutRestaurantsDao getInstance() {
    if(instance == null) {
      instance = new TakeOutRestaurantsDao();
    }
    return instance;
  }

//  1. create
  public TakeOutRestaurants create(TakeOutRestaurants takeOutRestaurant) throws SQLException{
    Restaurants createdRestaurant=create(new Restaurants(takeOutRestaurant.getRestaurantName(),
        takeOutRestaurant.getDescription(), takeOutRestaurant.getMenu(), takeOutRestaurant.getHours(),
        takeOutRestaurant.isActive(), takeOutRestaurant.getCuisine(), takeOutRestaurant.getStreet1(),
        takeOutRestaurant.getStreet2(), takeOutRestaurant.getCity(), takeOutRestaurant.getState(),
        takeOutRestaurant.getZip(), takeOutRestaurant.getCompany()));

    String insertTakeOut="INSERT INTO TakeOutRestaurant(RestaurantId,MaxWaitTime) VALUES(?,?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;

    try{
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertTakeOut);

      insertStmt.setInt(1, createdRestaurant.getRestaurantId());
      insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
      insertStmt.executeUpdate();
      takeOutRestaurant.setRestaurantId(createdRestaurant.getRestaurantId());
      return takeOutRestaurant;
    }catch (SQLException e){
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
  public TakeOutRestaurants getTakeOutRestaurantById(int takeOutRestaurantId) throws SQLException{
    String selectTakeOut="SELECT TakeOutRestaurant.RestaurantId "
        + "AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,MaxWaitTime "
        + "FROM TakeOutRestaurant INNER JOIN Restaurants "
        + "ON TakeOutRestaurant.RestaurantId=Restaurants.RestaurantId "
        + "WHERE TakeOutRestaurant.RestaurantId=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectTakeOut);

      selectStmt.setInt(1, takeOutRestaurantId);
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

        int maxWaitTime=results.getInt("MaxWaitTime");
        TakeOutRestaurants TakeOutRestaurant=new TakeOutRestaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company, maxWaitTime);
        return TakeOutRestaurant;
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

//  3. get by company name
  public List<TakeOutRestaurants> getTakeOutRestaurantsByCompanyName(String companyName) throws SQLException{
    String selectTakeOut="SELECT \n"
        + "  TakeOutRestaurant.RestaurantId,\n"
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
        + "  TakeOutRestaurant.MaxWaitTime \n"
        + "FROM \n"
        + "  TakeOutRestaurant \n"
        + "INNER JOIN \n"
        + "  Restaurants \n"
        + "ON \n"
        + "  TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId\n"
        + "WHERE \n"
        + "  Restaurants.CompanyName = ?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;
    List<TakeOutRestaurants> takeOutRestaurants=new ArrayList<>();

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectTakeOut);

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
        int maxWaitTime=results.getInt("MaxWaitTime");
        TakeOutRestaurants takeOutRestaurant=new TakeOutRestaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company, maxWaitTime);
        takeOutRestaurants.add(takeOutRestaurant);
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
    return takeOutRestaurants;
  }


//  4. delete
  public TakeOutRestaurants delete(TakeOutRestaurants takeOutRestaurant) throws SQLException{
    String deleteSitDown="DELETE FROM SitDownRestaurant WHERE RestaurantId=?;";
    Connection connection=null;
    PreparedStatement deleteStmt=null;

    try{
      connection=connectionManager.getConnection();
      deleteStmt=connection.prepareStatement(deleteSitDown);
      deleteStmt.setInt(1, takeOutRestaurant.getRestaurantId());

      deleteStmt.executeUpdate();

      // Then also delete from the superclass.
      // Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
      // super.delete() without even needing to delete from Administrators first.
      super.delete(takeOutRestaurant);

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

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
import review.model.SitDownRestaurants;

public class SitDownRestaurantsDao extends RestaurantsDao{
//  protected ConnectionManager connectionManager;//no need because it will call super class's connectionManager everytime call connection.
  private static SitDownRestaurantsDao instance = null;
  protected SitDownRestaurantsDao() {
    super();
  }
  public static SitDownRestaurantsDao getInstance() {
    if(instance == null) {
      instance = new SitDownRestaurantsDao();
    }
    return instance;
  }

//  1.create
  public SitDownRestaurants create(SitDownRestaurants sitDownRestaurant) throws SQLException {
    Restaurants createdRestaurant =create(new Restaurants(sitDownRestaurant.getRestaurantName(),
        sitDownRestaurant.getDescription(), sitDownRestaurant.getMenu(), sitDownRestaurant.getHours(),
        sitDownRestaurant.isActive(), sitDownRestaurant.getCuisine(), sitDownRestaurant.getStreet1(),
        sitDownRestaurant.getStreet2(), sitDownRestaurant.getCity(), sitDownRestaurant.getState(),
        sitDownRestaurant.getZip(), sitDownRestaurant.getCompany()));

    String insertSitDownRestaurant="INSERT INTO SitDownRestaurant(RestaurantId,Capacity) VALUES(?,?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;


    try{
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertSitDownRestaurant);

      insertStmt.setInt(1, createdRestaurant.getRestaurantId());
      insertStmt.setInt(2, sitDownRestaurant.getCapacity());
      insertStmt.executeUpdate();

      sitDownRestaurant.setRestaurantId(createdRestaurant.getRestaurantId());

      return sitDownRestaurant;

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
  public SitDownRestaurants getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException{
    String selectSitDown="SELECT SitDownRestaurant.RestaurantId "
        + "AS RestaurantId,Name,Description,Menu,Hours,Active,CuisineType,Street1,Street2,City,State,Zip,CompanyName,Capacity "
        + "FROM SitDownRestaurant INNER JOIN Restaurants "
        + "ON SitDownRestaurant.RestaurantId=Restaurants.RestaurantId "
        + "WHERE SitDownRestaurant.RestaurantId=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectSitDown);

      selectStmt.setInt(1, sitDownRestaurantId);
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

        int capacity=results.getInt("Capacity");
        SitDownRestaurants sitDownRestaurant=new SitDownRestaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company, capacity);
        return sitDownRestaurant;
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
  public List<SitDownRestaurants> getSitDownRestaurantsByCompanyName(String companyName)throws SQLException{
    String selectSitDown="SELECT \n"
        + "  SitDownRestaurant.RestaurantId,\n"
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
        + "  SitDownRestaurant.Capacity \n"
        + "FROM \n"
        + "  SitDownRestaurant \n"
        + "INNER JOIN \n"
        + "  Restaurants \n"
        + "ON \n"
        + "  SitDownRestaurant.RestaurantId = Restaurants.RestaurantId\n"
        + "WHERE \n"
        + "  Restaurants.CompanyName = ?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;
    List<SitDownRestaurants> sitDownRestaurants=new ArrayList<>();

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectSitDown);

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
        int capacity=results.getInt("Capacity");
        SitDownRestaurants sitDownRestaurant=new SitDownRestaurants(resultRestaurantId, name, description, menu, hours, active,
            cuisineType, street1,street2, city,state,zip, company, capacity);
        sitDownRestaurants.add(sitDownRestaurant);
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
    return sitDownRestaurants;
  }

//  4. delete
  public SitDownRestaurants delete(SitDownRestaurants sitDownRestaurant) throws SQLException{
    String deleteSitDown="DELETE FROM SitDownRestaurant WHERE RestaurantId=?;";
    Connection connection=null;
    PreparedStatement deleteStmt=null;

    try{
      connection=connectionManager.getConnection();
      deleteStmt=connection.prepareStatement(deleteSitDown);
      deleteStmt.setInt(1, sitDownRestaurant.getRestaurantId());

      deleteStmt.executeUpdate();

      // Then also delete from the superclass.
      // Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
      // super.delete() without even needing to delete from Administrators first.
      super.delete(sitDownRestaurant);

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

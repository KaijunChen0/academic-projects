package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import review.model.Recommendations;
import review.model.Restaurants;
import review.model.Reviews;
import review.model.Users;

public class RecommendationsDao {
  protected ConnectionManager connectionManager;
  private static RecommendationsDao instance = null;
  protected RecommendationsDao() {
    connectionManager = new ConnectionManager();
  }
  public static RecommendationsDao getInstance() {
    if(instance == null) {
      instance = new RecommendationsDao();
    }
    return instance;
  }

//  1. create
  public Recommendations create(Recommendations recommendation) throws SQLException{
    String insertRecommend="INSERT INTO Recommendations(UserName,RestaurantId) VALUES(?,?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;
    ResultSet resultKey=null;

    try{
      connection=connectionManager.getConnection();
      insertStmt=connection.prepareStatement(insertRecommend, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, recommendation.getUser().getUserName());
      insertStmt.setInt(2, recommendation.getRestaurant().getRestaurantId());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int recommendationId = -1;
      if(resultKey.next()) {
        recommendationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      recommendation.setRecommendationId(recommendationId);
      return recommendation;

    }catch (SQLException e){
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

//  2. get by id
  public Recommendations getRecommendationById(int recommendationId) throws SQLException{
    String selectRecommendation="SELECT RecommendationId,UserName,RestaurantId "
        + "FROM Recommendations "
        + "WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);

      selectStmt.setInt(1, recommendationId);
      results = selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      RestaurantsDao restaurantsDao=new RestaurantsDao();

      if(results.next()) {
        int resultRecommendationId=results.getInt("RecommendationId");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        Restaurants restaurant=restaurantsDao.getRestaurantById(results.getInt("RestaurantId"));
        Recommendations recommendation=new Recommendations(resultRecommendationId, user, restaurant);
        return recommendation;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
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

//  3. get by username
  public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException{
    String selectRecommend="SELECT Recommendations.RecommendationId, Recommendations.UserName, Recommendations.RestaurantId\n"
        + "FROM Recommendations\n"
        + "INNER JOIN Users\n"
        + "ON Recommendations.UserName=Users.UserName\n"
        + "WHERE Users.UserName=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    List<Recommendations> recommendations=new ArrayList<>();
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectRecommend);

      selectStmt.setString(1, userName);
      results=selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      RestaurantsDao restaurantsDao= new RestaurantsDao();

      while(results.next()){
        int resultRecommendationId=results.getInt("RecommendationId");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        Restaurants restaurant=restaurantsDao.getRestaurantById(results.getInt("RestaurantId"));

        Recommendations recommendation=new Recommendations(resultRecommendationId, user, restaurant);
        recommendations.add(recommendation);
      }

    }catch (SQLException e){
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
    return recommendations;
  }

//  4. get by restaurant id
  public List<Recommendations> getRecommendationsByRestaurantId(int restaurantId) throws SQLException{
    String selectRecommend="SELECT Recommendations.RecommendationId, Recommendations.UserName, Recommendations.RestaurantId\n"
        + "FROM Recommendations\n"
        + "INNER JOIN Restaurants\n"
        + "ON Recommendations.RestaurantId=Restaurants.RestaurantId\n"
        + "WHERE Restaurants.RestaurantId=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    List<Recommendations> recommendations=new ArrayList<>();
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectRecommend);

      selectStmt.setInt(1, restaurantId);
      results=selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      RestaurantsDao restaurantsDao= new RestaurantsDao();

      while(results.next()){
        int resultRecommendationId=results.getInt("RecommendationId");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        Restaurants restaurant=restaurantsDao.getRestaurantById(results.getInt("RestaurantId"));

        Recommendations recommendation=new Recommendations(resultRecommendationId, user, restaurant);
        recommendations.add(recommendation);
      }

    }catch (SQLException e){
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
    return recommendations;
  }

//  5. delete
  public Recommendations delete(Recommendations recommendation) throws SQLException{
    String deleteRecommend="DELETE FROM Recommendations WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRecommend);
      deleteStmt.setInt(1, recommendation.getRecommendationId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Persons instance.
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

}

package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import review.model.Restaurants;
import review.model.Reviews;
import review.model.Users;
import java.util.Date;

public class ReviewsDao {
  protected ConnectionManager connectionManager;
  private static ReviewsDao instance = null;
  protected ReviewsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ReviewsDao getInstance() {
    if(instance == null) {
      instance = new ReviewsDao();
    }
    return instance;
  }

//  1.create
  public Reviews create(Reviews review) throws SQLException{
    String insertReview = "INSERT INTO Reviews(Created, Content,Rating,UserName,RestaurantId) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReview,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
      insertStmt.setString(2, review.getContent());
      insertStmt.setDouble(3, review.getRating());
      insertStmt.setString(4, review.getUser().getUserName());
      insertStmt.setInt(5, review.getRestaurant().getRestaurantId());
      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int reviewId = -1;
      if(resultKey.next()) {
        reviewId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      review.setReviewId(reviewId);
      return review;

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
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
  public Reviews getReviewById(int reviewId) throws SQLException{
    String selectReview =
        "SELECT ReviewId,Created,Content,Rating,UserName,RestaurantId " +
            "FROM Reviews " +
            "WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);

      selectStmt.setInt(1, reviewId);
      results = selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      RestaurantsDao restaurantsDao=new RestaurantsDao();

      if(results.next()) {
        int resultReviewId=results.getInt("ReviewId");
        Date created=results.getDate("Created");
        String content=results.getString("Content");
        double rating=results.getDouble("Rating");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        Restaurants restaurant=restaurantsDao.getRestaurantById(results.getInt("RestaurantId"));
        Reviews review = new Reviews(resultReviewId, created, content, rating, user,restaurant);
        return review;
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
  public List<Reviews> getReviewsByUserName(String userName) throws SQLException{
    String selectReview="SELECT \n"
        + "    Reviews.ReviewId AS ReviewId,\n"
        + "    Reviews.Created AS Created,\n"
        + "    Reviews.Content AS Content,\n"
        + "    Reviews.Rating AS Rating,\n"
        + "    Reviews.UserName AS UserName,\n"
        + "    Reviews.RestaurantId AS RestaurantId\n"
        + "FROM \n"
        + "    Reviews \n"
        + "INNER JOIN \n"
        + "    Users \n"
        + "ON \n"
        + "    Reviews.UserName = Users.UserName\n"
        + "WHERE \n"
        + "    Users.UserName = ?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;
    List<Reviews> reviews=new ArrayList<>();

    try {
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectReview);

      selectStmt.setString(1, userName);
      results=selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      RestaurantsDao restaurantsDao= new RestaurantsDao();

      while(results.next()){
        int resultReviewId=results.getInt("ReviewId");
        Date created=new Date(results.getTimestamp("Created").getTime());
        String content=results.getString("Content");
        double rating=results.getDouble("Rating");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        Restaurants restaurant=restaurantsDao.getRestaurantById(results.getInt("RestaurantId"));

        Reviews review=new Reviews(resultReviewId, created, content, rating, user, restaurant);
        reviews.add(review);
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
    return reviews;
  }

//  4. get by restaurant id
  public List<Reviews> getReviewsByRestaurantId(int restaurantId) throws SQLException{
    String selectReview="SELECT \n"
        + "    Reviews.ReviewId AS ReviewId,\n"
        + "    Reviews.Created AS Created,\n"
        + "    Reviews.Content AS Content,\n"
        + "    Reviews.Rating AS Rating,\n"
        + "    Reviews.UserName AS UserName,\n"
        + "    Reviews.RestaurantId AS RestaurantId \n"
        + "FROM \n"
        + "    Reviews \n"
        + "INNER JOIN \n"
        + "    Restaurants \n"
        + "ON \n"
        + "    Reviews.RestaurantId = Restaurants.RestaurantId\n"
        + "WHERE \n"
        + "    Reviews.RestaurantId = ?;";
    List<Reviews> reviews=new ArrayList<>();
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try {
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectReview);

      selectStmt.setInt(1, restaurantId);
      results=selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      RestaurantsDao restaurantsDao= new RestaurantsDao();

      while(results.next()){
        int resultReviewId=results.getInt("ReviewId");
        Date created=new Date(results.getTimestamp("Created").getTime());
        String content=results.getString("Content");
        double rating=results.getDouble("Rating");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        Restaurants restaurant=restaurantsDao.getRestaurantById(results.getInt("RestaurantId"));

        Reviews review=new Reviews(resultReviewId, created, content, rating, user, restaurant);
        reviews.add(review);
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
    return reviews;

  }

//  5. delete
  public Reviews delete(Reviews review) throws SQLException {
    String deleteReview="DELETE FROM Reviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReview);
      deleteStmt.setInt(1, review.getReviewId());
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

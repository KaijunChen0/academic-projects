package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import review.model.Reservations;
import review.model.Restaurants;
import review.model.Reviews;
import review.model.SitDownRestaurants;
import review.model.Users;

public class ReservationsDao {
  protected ConnectionManager connectionManager;

  private static ReservationsDao instance = null;
  protected ReservationsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ReservationsDao getInstance() {
    if(instance == null) {
      instance = new ReservationsDao();
    }
    return instance;
  }

//  1. create
  public Reservations create(Reservations reservation) throws SQLException{
    String insertReservation="INSERT INTO Reservations(Start,End,Size,UserName,RestaurantId) "
        + "VALUES(?,?,?,?,?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;
    ResultSet resultKey=null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReservation,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setTimestamp(1, new Timestamp(reservation.getStart().getTime()));
      insertStmt.setTimestamp(2,new Timestamp(reservation.getEnd().getTime()));
      insertStmt.setInt(3, reservation.getSize());
      insertStmt.setString(4, reservation.getUser().getUserName());
      insertStmt.setInt(5, reservation.getSitDownRestaurant().getRestaurantId());
      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int reservationId = -1;
      if(resultKey.next()) {
        reservationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      reservation.setReservationId(reservationId);
      return reservation;

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
  public Reservations getReservationById(int reservationId) throws SQLException{
    String selectReservation="SELECT ReservationId, Start, End, Size, UserName, RestaurantId\n"
        + "FROM Reservations\n"
        + "WHERE ReservationId=?;";
    Connection connection=null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReservation);

      selectStmt.setInt(1, reservationId);
      results = selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      SitDownRestaurantsDao sitDownRestaurantsDao=new SitDownRestaurantsDao();

      if(results.next()) {
        int resultReservationId=results.getInt("ReservationId");
        Date start=results.getDate("Start");
        Date end=results.getDate("End");
        int size=results.getInt("Size");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        SitDownRestaurants sitDownRestaurant=sitDownRestaurantsDao.getSitDownRestaurantById(results.getInt("RestaurantId"));

        Reservations reservation=new Reservations(resultReservationId, start,end, size,user,sitDownRestaurant);
        return reservation;
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
  public List<Reservations> getReservationsByUserName(String userName) throws SQLException{
    String selectReservation="SELECT Reservations.ReservationId, Reservations.Start, Reservations.End, Reservations.Size, Reservations.UserName, Reservations.RestaurantId\n"
        + "FROM Reservations\n"
        + "INNER JOIN Users\n"
        + "ON Reservations.UserName=Users.UserName\n"
        + "WHERE Users.UserName=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    List<Reservations> reservations=new ArrayList<>();
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectReservation);
      selectStmt.setString(1, userName);
      results=selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      SitDownRestaurantsDao sitDownRestaurantsDao=new SitDownRestaurantsDao();

      while(results.next()){
        int resultReservationId=results.getInt("ReservationId");
        Date start=results.getDate("Start");
        Date end=results.getDate("End");
        int size=results.getInt("Size");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        SitDownRestaurants sitDownRestaurant=sitDownRestaurantsDao.getSitDownRestaurantById(results.getInt("RestaurantId"));

        Reservations reservation=new Reservations(resultReservationId, start,end, size,user,sitDownRestaurant);
        reservations.add(reservation);
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
    return reservations;
  }

//  4. get by sit-down-restaurant id
  public List<Reservations> getReservationsBySitDownRestaurantId(int sitDownRestaurantId) throws SQLException{
    String selectReservation="SELECT Reservations.ReservationId, Reservations.Start, Reservations.End, Reservations.Size, Reservations.UserName, Reservations.RestaurantId\n"
        + "FROM Reservations\n"
        + "INNER JOIN Restaurants\n"
        + "ON Reservations.RestaurantId=Restaurants.RestaurantId\n"
        + "WHERE Restaurants.RestaurantId=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    List<Reservations> reservations=new ArrayList<>();
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectReservation);
      selectStmt.setInt(1, sitDownRestaurantId);
      results=selectStmt.executeQuery();

      UsersDao usersDao=new UsersDao();
      SitDownRestaurantsDao sitDownRestaurantsDao=new SitDownRestaurantsDao();

      while(results.next()){
        int resultReservationId=results.getInt("ReservationId");
        Date start=results.getDate("Start");
        Date end=results.getDate("End");
        int size=results.getInt("Size");
        Users user=usersDao.getUserByUserName(results.getString("UserName"));
        SitDownRestaurants sitDownRestaurant=sitDownRestaurantsDao.getSitDownRestaurantById(results.getInt("RestaurantId"));

        Reservations reservation=new Reservations(resultReservationId, start,end, size,user,sitDownRestaurant);
        reservations.add(reservation);
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
    return reservations;
  }

//  5. delete
  public Reservations delete(Reservations reservation)throws SQLException{
    String deleteReservation="DELETE FROM Reservations\n"
        + "WHERE Reservations.ReservationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReservation);
      deleteStmt.setInt(1, reservation.getReservationId());
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

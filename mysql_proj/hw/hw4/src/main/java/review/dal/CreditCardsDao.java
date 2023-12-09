package review.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import review.model.CreditCards;
import review.model.Users;

public class CreditCardsDao {
  protected ConnectionManager connectionManager;
  private static CreditCardsDao instance=null;
  protected CreditCardsDao(){
    connectionManager=new ConnectionManager();
  }
  public static CreditCardsDao getInstance(){
    if (instance==null){
      instance=new CreditCardsDao();
    }
    return instance;
  }

  // 1. Create
  public CreditCards create(CreditCards creditCard) throws SQLException {
    String insertCreditCard="INSERT INTO CreditCards (CardNumber, Expiration, UserName) "
        + "VALUES (?, ?, ?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;

    try{
      connection=connectionManager.getConnection();
      insertStmt=connection.prepareStatement(insertCreditCard);

      insertStmt.setLong(1, creditCard.getCardNumber());
      insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
      insertStmt.setString(3, creditCard.getUser().getUserName());
      insertStmt.executeUpdate();
      return creditCard;

    }catch (SQLException e){
      e.printStackTrace();
      throw e;
    } finally {
      if(connection!=null){
        connection.close();
      }
      if (insertStmt!=null){
        insertStmt.close();
      }
    }
  }

  // 2. Get credit card by card number
  public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException{
    String selectCreditCard="SELECT CardNumber,Expiration,UserName FROM CreditCards "
        + "WHERE CardNumber=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectCreditCard);
      selectStmt.setLong(1, cardNumber);

      results=selectStmt.executeQuery();

      UsersDao usersDao=UsersDao.getInstance();

      if(results.next()){
        long resultCardNumber=results.getLong("CardNumber");
        Date exp=results.getDate("Expiration");
        String userName=results.getString("UserName");
        Users user=usersDao.getUserByUserName(userName);
        CreditCards creditCard=new CreditCards(resultCardNumber, exp, user);
        return creditCard;
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
    return null;
  }

  // 3. Get credit card by username
  public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException{
    List<CreditCards> creditCards=new ArrayList<>();

    String selectCreditCards="SELECT CardNumber, Expiration, UserName FROM CreditCards "
        + "WHERE UserName=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectCreditCards);
      selectStmt.setString(1, userName);

      results=selectStmt.executeQuery();

      UsersDao usersDao=UsersDao.getInstance();

      while(results.next()){
        long resultCardNumber=results.getLong("CardNumber");
        Date exp=results.getDate("Expiration");
        Users user=usersDao.getUserByUserName(userName);
        CreditCards creditCard=new CreditCards(resultCardNumber, exp, user);
        creditCards.add(creditCard);
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
    return creditCards;
  }

  // 4. update expiration
  public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException{
    String updateCreditCard="UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
    Connection connection=null;
    PreparedStatement updateStmt=null;

    try{
      connection=connectionManager.getConnection();
      updateStmt=connection.prepareStatement(updateCreditCard);

      updateStmt.setTimestamp(1, new Timestamp(newExpiration.getTime()));
      updateStmt.setLong(2, creditCard.getCardNumber());

      updateStmt.executeUpdate();

      creditCard.setExpiration(newExpiration);
      return creditCard;
    }catch(SQLException e){
      e.printStackTrace();
      throw e;
    }finally{
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }

  }

  // 5. delete
  public CreditCards delete(CreditCards creditCard) throws SQLException{
    String deleteCreditCard="DELETE FROM CreditCards WHERE CardNumber=?;";
    Connection connection=null;
    PreparedStatement deleteStmt=null;

    try{
      connection=connectionManager.getConnection();
      deleteStmt=connection.prepareStatement(deleteCreditCard);

      deleteStmt.setLong(1, creditCard.getCardNumber());

      deleteStmt.executeUpdate();
      return null;
    }catch (SQLException e){
      e.printStackTrace();
      throw e;
    }finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

}

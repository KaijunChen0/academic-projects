package review.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import review.model.Companies;

public class CompaniesDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static CompaniesDao instance = null;
  protected CompaniesDao() {
    connectionManager = new ConnectionManager();
  }
  public static CompaniesDao getInstance() {
    if(instance == null) {
      instance = new CompaniesDao();
    }
    return instance;
  }

  // 1. create
  public Companies create(Companies company) throws SQLException{
    String insertCompany="INSERT INTO Companies(CompanyName,About) "
        + "VALUES(?,?);";
    Connection connection=null;
    PreparedStatement insertStmt=null;

    try{
      connection=connectionManager.getConnection();
      insertStmt=connection.prepareStatement(insertCompany);
      insertStmt.setString(1, company.getCompanyName());
      insertStmt.setString(2, company.getAbout());

      insertStmt.executeUpdate();
      return company;
    }catch (SQLException e){
      e.printStackTrace();
      throw e;
    }finally{
      if(connection!=null){
        connection.close();
      }
      if(insertStmt!=null){
        insertStmt.close();
      }
    }
  }

//  2. get company by company name
  public Companies getCompanyByCompanyName(String companyName) throws SQLException {
    String selectCompany="SELECT CompanyName,About FROM Companies WHERE CompanyName=?;";
    Connection connection=null;
    PreparedStatement selectStmt=null;
    ResultSet results=null;

    try{
      connection=connectionManager.getConnection();
      selectStmt=connection.prepareStatement(selectCompany);
      selectStmt.setString(1, companyName);
      results=selectStmt.executeQuery();

      if(results.next()){
        String resultCompanyName=results.getString("CompanyName");
        String about=results.getString("About");
        Companies company=new Companies(resultCompanyName, about);
        return company;
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

//  3. update about
  public Companies updateAbout(Companies company, String newAbout) throws SQLException{
    String updateCompany="UPDATE Companies SET About=? WHERE CompanyName=?";
    Connection connection=null;
    PreparedStatement updateStmt=null;

    try{
      connection=connectionManager.getConnection();
      updateStmt=connection.prepareStatement(updateCompany);

      updateStmt.setString(1, newAbout);
      updateStmt.setString(2, company.getCompanyName());
      updateStmt.executeUpdate();

      company.setAbout(newAbout);
      return company;

    }catch (SQLException e){
      e.printStackTrace();
      throw e;
    }finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }

//  4. delete
  public Companies delete(Companies company) throws SQLException{
    String deleteCompany="DELETE FROM Companies WHERE CompanyName=?;";
    Connection connection=null;
    PreparedStatement deleteStmt=null;

    try{
      connection=connectionManager.getConnection();
      deleteStmt=connection.prepareStatement(deleteCompany);

      deleteStmt.setString(1, company.getCompanyName());

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

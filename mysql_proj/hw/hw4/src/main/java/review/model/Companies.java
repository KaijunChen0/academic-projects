package review.model;

public class Companies {
  protected String companyName;
  protected String about;

  public Companies(String companyName, String about) {
    this.companyName = companyName;
    this.about = about;
  }

  public Companies(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }
}

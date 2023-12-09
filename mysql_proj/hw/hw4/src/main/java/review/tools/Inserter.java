package review.tools;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import review.dal.CompaniesDao;
import review.dal.CreditCardsDao;
import review.dal.FoodCartRestaurantsDao;
import review.dal.RecommendationsDao;
import review.dal.ReservationsDao;
import review.dal.RestaurantsDao;
import review.dal.ReviewsDao;
import review.dal.SitDownRestaurantsDao;
import review.dal.TakeOutRestaurantsDao;
import review.dal.UsersDao;
import review.model.Companies;
import review.model.CreditCards;
import review.model.FoodCartRestaurants;
import review.model.Recommendations;
import review.model.Reservations;
import review.model.Restaurants;
import review.model.Restaurants.Cuisine;
import review.model.Reviews;
import review.model.SitDownRestaurants;
import review.model.TakeOutRestaurants;
import review.model.Users;

public class Inserter {

  public static void main(String[] args) throws SQLException{
    //DAO instance
    UsersDao usersDao=UsersDao.getInstance();
    CreditCardsDao creditCardsDao=CreditCardsDao.getInstance();
    CompaniesDao companiesDao=CompaniesDao.getInstance();
    RestaurantsDao restaurantsDao=RestaurantsDao.getInstance();
    SitDownRestaurantsDao sitDownRestaurantsDao=SitDownRestaurantsDao.getInstance();
    TakeOutRestaurantsDao takeOutRestaurantsDao=TakeOutRestaurantsDao.getInstance();
    FoodCartRestaurantsDao foodCartRestaurantsDao=FoodCartRestaurantsDao.getInstance();
    ReviewsDao reviewsDao=ReviewsDao.getInstance();
    RecommendationsDao recommendationsDao=RecommendationsDao.getInstance();
    ReservationsDao reservationsDao=ReservationsDao.getInstance();

    // ----------create------------
    // 1. users
    Users user1=new Users("Bruce","password","Bruce","C","bruce@mail.com","5555555");
    user1=usersDao.create(user1);
    Users user2=new Users("TT","password","Tony","D","tony@mail.com","5555555");
    user2=usersDao.create(user2);
    Users user3=new Users("DK","password","Daniel","K","dan@mail.com","5555555");
    user3=usersDao.create(user3);
    Users user4=new Users("James","password","James","M","james@mail.com","5555555");
    user4=usersDao.create(user4);
    Users user5=new Users("Steve","password","Steve","N","steve@mail.com","5555555");
    user5=usersDao.create(user5);
    // 2. credit cards
    Date date=Date.valueOf("2018-10-01");
    CreditCards creditCard1=new CreditCards(3499432187650987L,date,user1);
    creditCard1=creditCardsDao.create(creditCard1);
    CreditCards creditCard2 = new CreditCards(3488432187650987L, date, user1);
    creditCard2=creditCardsDao.create(creditCard2);
    CreditCards creditCard3 = new CreditCards(3799432187650987L, date, user1);
    creditCard3=creditCardsDao.create(creditCard3);
    CreditCards creditCard4 = new CreditCards(6011432187650987L, date, user2);
    creditCard4=creditCardsDao.create(creditCard4);
    CreditCards creditCard5 = new CreditCards(6011432187650988L, date, user2);
    creditCard5=creditCardsDao.create(creditCard5);
    CreditCards creditCard6 = new CreditCards(6441432187650987L, date, user2);
    creditCard6=creditCardsDao.create(creditCard6);
    CreditCards creditCard7 = new CreditCards(6451432187650987L, date, user3);
    creditCard7=creditCardsDao.create(creditCard7);
    CreditCards creditCard8 = new CreditCards(5199432187650987L, date, user3);
    creditCard8=creditCardsDao.create(creditCard8);
    CreditCards creditCard9 = new CreditCards(5499432187650987L, date, user4);
    creditCard9=creditCardsDao.create(creditCard9);
    CreditCards creditCard10 = new CreditCards(5499432187650988L, date, user4);
    creditCard10=creditCardsDao.create(creditCard10);
    CreditCards creditCard11 = new CreditCards(5499432187650989L, date, user5);
    creditCard11=creditCardsDao.create(creditCard11);
    CreditCards creditCard12 = new CreditCards(4499432187650987L, date, user5);
    creditCard12=creditCardsDao.create(creditCard12);
    CreditCards creditCard13 = new CreditCards(4499432187650989L, date, user5);
    creditCard13=creditCardsDao.create(creditCard13);
    // 3. companies
    Companies company1=new Companies("company1","about company1");
    company1=companiesDao.create(company1);
    Companies company2=new Companies("company2","about company2");
    company2=companiesDao.create(company2);
    Companies company3=new Companies("company3","about company3");
    company3=companiesDao.create(company3);
    // 4. Restaurants
    Restaurants restaurant1=new Restaurants("restaurant1","about restaurant",
        "menu","hours",true, Cuisine.AFRICAN,"street1","street2",
        "seattle","wa",98195,company1);
    restaurant1=restaurantsDao.create(restaurant1);
    Restaurants restaurant2=new Restaurants("restaurant2","about restaurant",
        "menu","hours",true,Cuisine.AMERICAN,"street1","street2",
        "seattle","wa",98195,company1);
    restaurant2=restaurantsDao.create(restaurant2);
    // 5. sit-down-restaurants
    SitDownRestaurants sitDownRestaurant1=new SitDownRestaurants("restaurant3","about restaurant",
        "menu","hours",true,Cuisine.ASIAN,"street1","street2",
        "seattle","wa",98195,company1,100);
    sitDownRestaurant1=sitDownRestaurantsDao.create(sitDownRestaurant1);
    SitDownRestaurants sitDownRestaurant2=new SitDownRestaurants("restaurant4","about restaurant",
        "menu","hours",true,Cuisine.HISPANIC,"street1","street2",
        "seattle","wa",98195,company1,200);
    sitDownRestaurant2=sitDownRestaurantsDao.create(sitDownRestaurant2);
    // 6. take-out restaurants
    TakeOutRestaurants takeOutRestaurant1=new TakeOutRestaurants("restaurant5","about restaurant",
        "menu","hours",true,Cuisine.HISPANIC,"street1","street2",
        "seattle","wa",98107,company1,60);
    takeOutRestaurant1=takeOutRestaurantsDao.create(takeOutRestaurant1);
    // 7. food cart restaurants
    FoodCartRestaurants foodCartRestaurant1=new FoodCartRestaurants("restaurant6","about restaurant",
        "menu","hours",true,Cuisine.EUROPEAN,"street1","street2",
        "seattle","wa",98107,company1,true);
    foodCartRestaurant1=foodCartRestaurantsDao.create(foodCartRestaurant1);
    // 8. reviews
    Reviews review1=new Reviews(date,"Delightful!",5.0,user1,restaurant1);
    review1=reviewsDao.create(review1);
    Reviews review2=new Reviews(date,"Superb!",5.0,user2,restaurant2);
    review2=reviewsDao.create(review2);

    // 9. recommendations
    Recommendations recommendation1=new Recommendations(user1, restaurant1);
    recommendation1=recommendationsDao.create(recommendation1);
    Recommendations recommendation2=new Recommendations(user2, restaurant2);
    recommendation2=recommendationsDao.create(recommendation2);

    // 10. reservations
    Date start=Date.valueOf("2023-05-01");
    Date end=Date.valueOf("2023-12-31");
    Reservations reservation1=new Reservations(start,end, 6, user1, sitDownRestaurant1);
    reservation1=reservationsDao.create(reservation1);
    Reservations reservation2=new Reservations(start,end, 10, user2, sitDownRestaurant2);
    reservation2=reservationsDao.create(reservation2);

    //-------------read-----------
    // 1.users
    Users u1=usersDao.getUserByUserName("Bruce");
    System.out.format("Reading user by username 'Bruce':\n userName:%s, Password:%s, FirstName:%s, LastName:%s, Email:%s, Phone:%s\n",
        u1.getUserName(), u1.getPassword(), u1.getFirstName(),u1.getLastName(),u1.getEmail(),u1.getPhone());

    // 2. credit cards
    CreditCards cc1=creditCardsDao.getCreditCardByCardNumber(3499432187650987L);
    System.out.printf("Reading credit card:\n card number:%s, expiration date:%s, username:%s\n", cc1.getCardNumber(),
        cc1.getExpiration(), cc1.getUser().getUserName());

    List<CreditCards> cards=creditCardsDao.getCreditCardsByUserName("Bruce");
    for(CreditCards cc: cards){
      System.out.printf("Reading looping credit cards by username 'Bruce':\n card number:%s, expiration date:%s, username:%s\n", cc.getCardNumber(),
          cc.getExpiration(), cc.getUser().getUserName());
    }

    // 3. companies
    Companies comp1=companiesDao.getCompanyByCompanyName("company1");
    System.out.printf("Reading company:\n company name:%s, about:%s\n", comp1.getCompanyName(), comp1.getAbout());

    // 4. restaurants
    Restaurants rest1=restaurantsDao.getRestaurantById(1);
    System.out.printf("Reading restaurant by id:\n restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s,"
        + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s\n", rest1.getRestaurantId(),
        rest1.getRestaurantName(), rest1.getDescription(), rest1.getMenu(), rest1.getHours(), rest1.isActive(),
        rest1.getCuisine(), rest1.getStreet1(), rest1.getStreet2(), rest1.getCity(),rest1.getState(),rest1.getZip(),
        rest1.getCompany().getCompanyName());

    List<Restaurants> restaurants1=restaurantsDao.getRestaurantsByCuisine(Cuisine.AFRICAN);
    for(Restaurants r:restaurants1){
      System.out.printf("Reading looping restaurant by cuisine type:\n restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s, "
          + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s\n",r.getRestaurantId(),
          r.getRestaurantName(), r.getDescription(), r.getMenu(), r.getHours(), r.isActive(),
          r.getCuisine(), r.getStreet1(), r.getStreet2(), r.getCity(),r.getState(),r.getZip(),
          r.getCompany().getCompanyName() );
    }

    List<Restaurants> restaurants2=restaurantsDao.getRestaurantsByCompanyName("company1");
    for(Restaurants r:restaurants2){
      System.out.printf("Reading looping restaurant by company name:\n restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s, "
              + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s\n",r.getRestaurantId(),
          r.getRestaurantName(), r.getDescription(), r.getMenu(), r.getHours(), r.isActive(),
          r.getCuisine(), r.getStreet1(), r.getStreet2(), r.getCity(),r.getState(),r.getZip(),
          r.getCompany().getCompanyName());
    }

    // 5. sit down restaurant
    SitDownRestaurants sit1=sitDownRestaurantsDao.getSitDownRestaurantById(3);
    System.out.printf("Reading sit down restaurant by id:\n sit down restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s,"
            + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s, capacity:%s\n", sit1.getRestaurantId(),
        sit1.getRestaurantName(), sit1.getDescription(), sit1.getMenu(), sit1.getHours(), sit1.isActive(),
        sit1.getCuisine(), sit1.getStreet1(), sit1.getStreet2(), sit1.getCity(),sit1.getState(),sit1.getZip(),
        sit1.getCompany().getCompanyName(),sit1.getCapacity());

    List<SitDownRestaurants> sits=sitDownRestaurantsDao.getSitDownRestaurantsByCompanyName("company1");
    for(SitDownRestaurants r:sits){
      System.out.printf("Reading looping sit down restaurant by company name:\n restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s, "
              + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s, capacity:%s\n",r.getRestaurantId(),
          r.getRestaurantName(), r.getDescription(), r.getMenu(), r.getHours(), r.isActive(),
          r.getCuisine(), r.getStreet1(), r.getStreet2(), r.getCity(),r.getState(),r.getZip(),
          r.getCompany().getCompanyName(), r.getCapacity());
    }

    //6. takeout restaurants
    TakeOutRestaurants take=takeOutRestaurantsDao.getTakeOutRestaurantById(5);
    System.out.printf("Reading takeout restaurant by id:\n takeout restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s,"
            + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s, MaxWaitTime:%s\n", take.getRestaurantId(),
        take.getRestaurantName(), take.getDescription(), take.getMenu(), take.getHours(), take.isActive(),
        take.getCuisine(), take.getStreet1(), take.getStreet2(), take.getCity(),take.getState(),take.getZip(),
        take.getCompany().getCompanyName(),take.getMaxWaitTime());

    List<TakeOutRestaurants> takes=takeOutRestaurantsDao.getTakeOutRestaurantsByCompanyName("company1");
    for(TakeOutRestaurants r:takes){
      System.out.printf("Reading looping takeout restaurant by company name:\n restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s, "
              + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s, MaxWaitTime:%s\n",r.getRestaurantId(),
          r.getRestaurantName(), r.getDescription(), r.getMenu(), r.getHours(), r.isActive(),
          r.getCuisine(), r.getStreet1(), r.getStreet2(), r.getCity(),r.getState(),r.getZip(),
          r.getCompany().getCompanyName(), r.getMaxWaitTime());
    }

    //7.food cart restaurants
    FoodCartRestaurants food=foodCartRestaurantsDao.getFoodCartRestaurantById(6);
    System.out.printf("Reading food cart restaurant:\n restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s,"
            + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s, licensed:%s\n", food.getRestaurantId(),
        food.getRestaurantName(), food.getDescription(), food.getMenu(), food.getHours(), food.isActive(),
        food.getCuisine(), food.getStreet1(), food.getStreet2(), food.getCity(),food.getState(),food.getZip(),
        food.getCompany().getCompanyName(),food.isLicensed());

    List<FoodCartRestaurants> foodCarts=foodCartRestaurantsDao.getFoodCartRestaurantsByCompanyName("company1");
    for(FoodCartRestaurants r:foodCarts){
      System.out.printf("Reading looping food cart restaurant by company name:\n restaurantId:%s, name:%s, description:%s, menu:%s, hours:%s, active:%s, "
              + "cuisine type:%s, street1:%s, street2:%s, city:%s, state:%s, zip:%s, company name:%s, licensed:%s\n",r.getRestaurantId(),
          r.getRestaurantName(), r.getDescription(), r.getMenu(), r.getHours(), r.isActive(),
          r.getCuisine(), r.getStreet1(), r.getStreet2(), r.getCity(),r.getState(),r.getZip(),
          r.getCompany().getCompanyName(), r.isLicensed());
    }

    // 8. review
    Reviews reviewById1=reviewsDao.getReviewById(1);
    System.out.printf("Reading review by reviewId:\n reviewId:%s, created:%s, content:%s, rating:%s,"
        + "username:%s, restaurantId:%s\n", reviewById1.getReviewId(),reviewById1.getCreated(), reviewById1.getContent(),
        reviewById1.getRating(), reviewById1.getUser().getUserName(), reviewById1.getRestaurant().getRestaurantId());

    List<Reviews> reviews=reviewsDao.getReviewsByUserName("Bruce");
    for(Reviews r: reviews){
      System.out.printf("Reading looping reviews by username:\n reviewId:%s, created:%s, content:%s, "
          + "rating:%s,username:%s, restaurantId:%s\n", r.getReviewId(),r.getCreated(),
          r.getContent(),r.getRating(), r.getUser().getUserName(), r.getRestaurant().getRestaurantId());
    }

    List<Reviews> reviews1=reviewsDao.getReviewsByRestaurantId(1);
    for(Reviews r: reviews1){
      System.out.printf("Reading looping reviews by restaurant id:\n reviewId:%s, created:%s, content:%s, "
              + "rating:%s,username:%s, restaurantId:%s\n", r.getReviewId(),r.getCreated(),
          r.getContent(),r.getRating(), r.getUser().getUserName(), r.getRestaurant().getRestaurantId());
    }

    // 9.recommendations
    Recommendations recommend=recommendationsDao.getRecommendationById(1);
    System.out.printf("Reading recommendation:\n recommendationId:%s, username:%s, restaurantId:%s\n",
        recommend.getRecommendationId(), recommend.getUser().getUserName(), recommend.getRestaurant().getRestaurantId());

    List<Recommendations> recommendations1=recommendationsDao.getRecommendationsByUserName("Bruce");
    for(Recommendations r:  recommendations1){
      System.out.printf("Reading looping recommendation by username:\n recommendationId:%s, username:%s, restaurantId:%s\n",
          r.getRecommendationId(), r.getUser().getUserName(), r.getRestaurant().getRestaurantId());
    }

    List<Recommendations> recommendations2=recommendationsDao.getRecommendationsByRestaurantId(1);
    for(Recommendations r:  recommendations2){
      System.out.printf("Reading looping recommendation by restaurant id:\n recommendationId:%s, username:%s, restaurantId:%s\n",
          r.getRecommendationId(), r.getUser().getUserName(), r.getRestaurant().getRestaurantId());
    }

    // 10.reservations
    Reservations reservation=reservationsDao.getReservationById(1);
    System.out.printf("Reading reservation:\n ReservationId:%s, Start:%s, End:%s, Size:%s, Username:%s, RestaurantId:%s\n",
        reservation.getReservationId(), reservation.getStart(), reservation.getEnd(), reservation.getSize(),
        reservation.getUser().getUserName(), reservation.getSitDownRestaurant().getRestaurantId());

    List<Reservations> reservations1=reservationsDao.getReservationsByUserName("Bruce");
    for(Reservations r:reservations1){
      System.out.printf("Reading looping reservation:\n ReservationId:%s, Start:%s, End:%s, Size:%s, Username:%s, RestaurantId:%s\n",
          r.getReservationId(), r.getStart(), r.getEnd(), reservation.getSize(),r.getUser().getUserName(),
          r.getSitDownRestaurant().getRestaurantId());
    }

    List<Reservations> reservations2=reservationsDao.getReservationsBySitDownRestaurantId(3);
    for(Reservations r:reservations2){
      System.out.printf("Reading looping reservation:\n ReservationId:%s, Start:%s, End:%s, Size:%s, Username:%s, RestaurantId:%s\n",
          r.getReservationId(), r.getStart(), r.getEnd(),reservation.getSize(), r.getUser().getUserName(),
          r.getSitDownRestaurant().getRestaurantId());
    }

    //update
    // 1. credit cards
    CreditCards updatedCC=creditCardsDao.updateExpiration(creditCard1, Date.valueOf("2030-01-01"));
    System.out.printf("Updating credit card:\n card number:%s, expiration:%s, username:%s\n",
        updatedCC.getCardNumber(),updatedCC.getExpiration(), updatedCC.getUser().getUserName());
    System.out.println("-->Expiration date is updated.");

    // 2. companies
    Companies updatedCompany=companiesDao.updateAbout(company1, "new about for company1");
    System.out.printf("Updating company:\n company name:%s, about:%s\n", updatedCompany.getCompanyName(),
        updatedCompany.getAbout());
    System.out.println("-->Company about is updated.");

    //delete
    usersDao.delete(user1);
    creditCardsDao.delete(creditCard1);
    companiesDao.delete(company1);
    restaurantsDao.delete(restaurant1);
    sitDownRestaurantsDao.delete(sitDownRestaurant1);
    takeOutRestaurantsDao.delete(takeOutRestaurant1);
    foodCartRestaurantsDao.delete(foodCartRestaurant1);
    reviewsDao.delete(review1);
    recommendationsDao.delete(recommendation1);
    reservationsDao.delete(reservation1);
    System.out.println("-->Deleted.");
  }

}

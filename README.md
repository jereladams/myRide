# Jerel Adams Individual Project

Automobile Maintenance Archive.

### Problem Statement

The average age of a light vehicle in the United States is estimated at around 11.6 years in 2016. In light of an aging vehicle fleet and increasing shop rates ($80-$125 /hr.) many car owners have turned into do-it-yourself (DIY) shade tree mechanics.

While maintaining your own vehicle helps save money, it also creates a disconnect between the auto manufacturer and the consumer. Since owners are making fewer visits to the shop or dealership for repairs, there is less information available about the car's history. Even repairs done by a mechanic can be overlooked since each shop has it's own unique system for tracking repairs. This information is seldom shared or accessible by manufacturers or other shops.

I would like to create an Automobile Maintenance Archive website to serve as repository for those looking to track repairs, parts, warranties and recall history for their vehicles.

### Screenshots

* [Main](images/MainScreen.jpg)
* [Login](images/LoginScreen.jpg)
* [Register](images/RegisterScreen.jpg)
* [Cars](images/CarsScreen.jpg)
* [Add Repair](images/AddRepairScreen.jpg)
* [Repairs](images/RepairsScreen.jpg)
* [Recalls](images/RecallsScreen.jpg)


### Project Technologies/Techniques

* Security/Authentication -
  * Tomcat's JDBC Realm Authentication
  * Admin role: delete users
  * User role: save car, repairs and parts, edit data they have entered previously
  * All: anyone can view the vehicle info and recall notices (no login required)
* Database
  * MySQL
  * Store users and roles
  * Store all data for the cars,repairs,parts.
* ORM Framework
  * Hibernate 5
* Dependency Management
  * Maven
* Web ApplicationService consumed using Java
  * https://vpic.nhtsa.dot.gov/api/ to decode VIN
  * https://one.nhtsa.gov/webapi/Default.aspx?Recalls/API/83 to lookup recalls
* CSS
  * Bootstrap
* Data Validation
  * Javascript frontend\Custom validation backend
* Logging
  * Configurable logging using Log4J2. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting.
* Hosting
  * AWS
* Independent Research Topic/s
  * HTML and Bootstrap
* Unit Testing
  * Unit tests to achieve 80%+ code coverage
* IDE: IntelliJ IDEA


### Design

* [Application Flow](DesignDocuments/applicationFlow.md)
* [Main Page](images/Main.png)
* [Sign Up](images/SignUp.png)
* [Login Page](images/Login.png)
* [Logged In User](images/LoggedInUser.png)
* [Search Results](images/SearchResults.png)
* [Add Repair](images/AddRepair.png)

### [Project Plan](ProjectPlan.md)

* [Project Plan](ProjectPlan.md)

### [Development TimeLog](TimeLog.md)

* [TimeLog](TimeLog.md)

### [Demo Video and TimeLine]()

* [Demo Video Time Line](TimeLine.md)
* [Demo Video](https://youtu.be/LbnBbs6DGus)

authentication

### [Code Coverage](Images/CodeCoverage.png)

* [Code Coverage](Images/CodeCoverage.png)

  Controller = none
  Entity = 54%
  Persistence = %100

### Takeaways

I would not have put all the CRUD methods into single servlets. This made routing a nightmare and made testing next to impossible. I started out creating separate servlets for each CRUD and thought I would have a ridiculous number of controllers. I finally realized separating these is probably a better technique and allows each CRUD method to have its own doGET and doPost methods.

I would have used the Session to store my objects rather than using the Request with individual attributes. I had an ah-ha moment about 3 weeks ago when we talked about Global Variables in my world = Session in the web programming.

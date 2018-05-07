# Jerel Adams Individual Project

Automobile Maintenance Archive.

### Problem Statement

The average age of a light vehicle in the United States is estimated at around 11.6 years in 2016. In light of an aging vehicle fleet and increasing shop rates ($80-$125 /hr.) many car owners have turned into do-it-yourself (DIY) shade tree mechanics.

While maintaining your own vehicle helps save money, it also creates a disconnect between the auto manufacturer and the consumer. Since owners are making fewer visits to the shop or dealership for repairs, there is less information available about the car's history. Even repairs done by a mechanic can be overlooked since each shop has it's own unique system for tracking repairs. This information is seldom shared or accessible by manufacturers or other shops.

I would like to create an Automobile Maintenance Archive website to serve as repository for those looking to track repairs, parts, warranties and recall history for their vehicles.

### Screenshots

* [Main](Images/MainScreen.jpg)
* [Login](Images/LoginScreen.jpg)
* [Register](Images/RegisterScreen.jpg)
* [Cars](Images/CarsScreen.jpg)
* [Add Repair](Images/AddRepairScreen.jpg)
* [Repairs](Images/RepairsScreen.jpg)
* [Recalls](Images/RecallsScreen.jpg)


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

* [Screen Design](DesignDocuments/WireFrames/Main.png)
* [Application Flow](DesignDocuments/applicationFlow.md)
* [Main Page](Images/Main.png)
* [Sign Up](Images/SignUp.png)
* [Login Page](Images/Login.png)
* [Logged In User](Images/LoggedInUser.png)
* [Search Results](Images/SearchResults.png)
* [Add Repair](Images/AddRepair.png)

### [Project Plan](ProjectPlan.md)

* [Project Plan](ProjectPlan.md)

### [Development TimeLog](TimeLog.md)

* [TimeLog](TimeLog.md)
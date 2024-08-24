
# *MakersharkService*
  *Version: 3.3.2*
 ## *Project Overview*
 It is a Spring Boot-based RESTful API application designed to help users search for manufacturers based on specific criteria such as location, nature of business, and manufacturing processes. The application also supports JWT-based authentication and provides endpoints for adding suppliers, fetching manufacturers.

 # *Technologies Used*
* Java 17
* Spring Boot
* Spring Security with JWT
* Spring Data JPA
* Oracke Database
* Junit
* Mockito
* Lombhok
* Swagger for API documentation
* Postman for testing

**Note:** Customize the database properties (spring.datasource.*) according to their specific requirements before deploying the application.

# *Testing Application Using PostMan*

  **1. Adding Username and password to the database:**

  Use  *http://localhost:8080/api/supplier/register* endpoint, Method type=POST and select Body as JSON type to add user in database.
  
  *Example:*
  ```
{
    "name": "James",
    "username": "Sawyer",
    "password": "Frekles",
    "role": "ADMIN"
}
```
  **2. Authentication:**

Use  *http://localhost:8080/api/supplier/authenticate* endpoint with Method type=POST to obtain the JWT Token as response.

*Example:*

```
{
    "username": "Sawyer",
    "password": "Frekles"
}  
```
**3. Adding  Supplier:**

Use  *http://localhost:8080/api/supplier/add* endpoint with Method Type=POST to add add Supplier in to the Database and in Authurization Section choose Authentication type as *Bearer Token* 

*Example JSON Body:*   
 
  ```
{
    "company_name": "TATA Industries",
    "website": "http://Tata.com",
    "location": "India",
    "natureOfBusiness": "small_scale",
    "manufacturingProcesses": [
        "three_dimensional_printing",
        "casting"
    ]
}
```
**4. Fetch Manufacturers:**

Use  *http://localhost:8080/api/supplier/query* endpoint with Method Type=POST and in Authurization Section choose Authentication type as *Bearer Token*

*Example JSON Body:*   

```
{
  "location": "India",
  "natureOfBusiness": "small_scale",
   "manufacturingProcesses": "three_dimensional_printing"
}

```
# *Running Application Using Swagger*

Once the application is running, you can access the Swagger UI at
```
http://localhost:8080/swagger-ui/index.html
```

**1.** Select */api/supplier/authenticate* & pass the username & password, it will generate the JWT Token as Response.

**2.** Copy the generated JWT Token & Paste it to the Authorize Section

**3.** Test rest of the Endpoints 

# *Unit Testing*

Unit tests have been written using JUnit and Mockito to ensure the correctness of the application's functionality,if you are using a IDE then Right-click on the test class or method and select "Run" to execute the tests.
  
  

  

 
 

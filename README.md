
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

**1.** Select **/api/supplier/authenticate** & pass the username & password, it will generate the JWT Token as Response.

**2.** Copy the generated JWT Token & Paste it to the Authorize Section

**3.** Test rest of the Endpoints 

# *Testing the API Using curl*

You can use the following curl commands to test the API endpoints:

**1. Add a User:**
To register a new user, use the /api/supplier/register endpoint with a POST request:
```
curl -X POST http://localhost:8080/api/supplier/register \
-H "Content-Type: application/json" \
-d '{
      "name": "James",
      "username": "Sawyer",
      "password": "Frekles",
      "role": "ADMIN"
    }'


```

**2. Authenticate and Obtain JWT Token:**
To authenticate a user and obtain a JWT token, use the /api/supplier/authenticate endpoint with a POST request:
```
curl -X POST http://localhost:8080/api/supplier/authenticate \
-H "Content-Type: application/json" \
-d '{
      "username": "Sawyer",
      "password": "Frekles"
    }'
```
**3. Add a Supplier:**
To add a new supplier, use the /api/supplier/add endpoint with a POST request. Ensure you include the JWT token in the Authorization header:
```
curl -X POST http://localhost:8080/api/supplier/add \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <your-jwt-token>" \
-d '{
      "company_name": "TATA Industries",
      "website": "http://Tata.com",
      "location": "India",
      "natureOfBusiness": "small_scale",
      "manufacturingProcesses": [
        "three_dimensional_printing",
        "casting"
      ]
    }'
```
**4. Fetch Manufacturers:**
To search for manufacturers, use the /api/supplier/query endpoint with a POST request. Again, ensure you include the JWT token in the Authorization header:
```
curl -X POST http://localhost:8080/api/supplier/query \
-H "Content-Type: application/json" \
-H "Authorization: Bearer <your-jwt-token>" \
-d '{
      "location": "India",
      "natureOfBusiness": "small_scale",
      "manufacturingProcesses": "three_dimensional_printing"
    }'

```
# *Unit Testing*

Unit tests have been written using JUnit and Mockito to ensure the correctness of the application's functionality,if you are using a IDE then Right-click on the test class or method and select "Run" to execute the tests.
  
  

  

 
 

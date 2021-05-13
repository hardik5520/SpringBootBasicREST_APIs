# SpringBootBasicREST_APIs
Hello everyone,
This is a very basic yet useful project of SpringBoot for beginners. 
Here I have designed basic Http methods(GET,POST etc.), for a social media app where a user can create and edit posts keeping in mind using maximum good practices.
The API's have been documented using swagger. The documentation can be seen by starting the app and visiting http://localhost:8080/swagger-ui.html (or http://localhost:8080/v2/api-docs if you do not include swagger-ui dependency in your pom.xml)
The APIs can be monitored using actuator at http://localhost:8080/actuator(Currently I have enable HttpTrace but in prod its not recommended).
Also hal browser dependency is included for a better view of actuator on browser.
The database used here is H2 (that is an in memory database), along with JPA(Java Persistence API).
The implementation for social media is done in package- user, other packages are just for basic explanation of important things, which include-
->helloworld package to explain the very basics of spring, also we have implemented and shown basic internationalisation of REST APIs in helloworld package.
->versioning package explains and show different methods of versioning your REST API.
->exception package is used along with the main user package to generate custom exceptions, i.e. in a better readable form. 
Also there are a lot of comments included for a better explanation of code.

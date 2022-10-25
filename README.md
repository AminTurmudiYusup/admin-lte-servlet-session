# admin-lte-servlet-session
This project describe how to create authentication and session without using spring security/springboot

Create Authentication, Create Session using Servlet and Run inside jetty plugin(without spring security)
Prerequisite
1. This tutorial is a continuation of the previous tutorial(so you must see the previous tutorial for better understanding =https://www.youtube.com/watch?v=lrgCCP6LJEI)
2. fork my previous project from my github(https://github.com/AminTurmudiYusup/admin-lte-servlet)
3. you already familiar to import project into intellij idea
4. your laptop already installed mysql, already create database and assign database to user(my database name dbsession and user session)
5. Understand concept of authentication
6. already understand the concept of session
7. already familiar using MD5 in java (you can see how plain text encode using md5 in my github https://github.com/AminTurmudiYusup/md5-cryptography.git)
8. understand to create package in java project
9. understand to mapping servlet using web.xml(if not, see my previous tutorial before continue this tutorial)


What would you learn
1. Connect java web app to database mysql using jdbc
2. create password and matching password in java using MD5 cryptoghraphy
3. login using username and password which username already saved in database
4. create session, remove session and invalidate session

in this tutorial i will use this scenario
1. user inserted to table user manually
2. user access login page
3. insert username and password
4. if username not found send message "username or password invalid". 
   - why use "username or password invalid" message
   - if i use this message "user not registered"
   - attacker know the username is valid and attacker try to attack website using bruto force attack to insert password
4. if username and password invalid send message "username or password invalid"
5. if username and password valid save session
6. when user request to protected resources(check session is valid or not)
7. logout user, remove session
8. try access protected  resources(without login, the app must redirect into login page if we config session correct)



Let's jump right in
1. run this ddl inside mysql
  CREATE TABLE dbsession.`user` (
  `user_id` varchar (45)NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(180) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;
2. create password using md5 java
3. insert user data into table user
   INSERT INTO dbsession.`user` (user_id,user_name,user_password) VALUES
    ('user01','david@gmail.com','42f749ade7f9e195bf475f37a44cafcb');
4. create db.properties (use to define database configuration)
    - customize database, username and password with your database environment
5. see my package and folder structure
6. add dependency into pom.xml
6. create DbConnection class for connect to database
7. create User class
8. create UserRepository class which dependent to DbConnection class
9. create Md5service
10. create LoginController to handle when user already insert username and password(in this section, session created) this class dependent to UserRepository and Md5Service class
11. creta LogoutController to remove session
12. create new page
13. create controller for new page
14. mapping controller in web.xml
15. try to run and test the app
   - login with invalid username(http://localhost:8080/myWebApp/)
   - login with invalid password
   - try access pretected resources(http://localhost:8080/myWebApp/home-servlet)
   - still success to access protected resources
16. to protect resources, before login we must create a filter and check if a session exists in the filter class
17. create LoginFilter which extend Filter
18. mapping filter in web.xml
19. use filter to protect servlet with spesific patern
20. try to run test app again 
   - login with invalid username
   - login with invalid password
   - try access protected resources(web app redirect into login page if user not login)
   - try to login using valid username and password(user can acces the protected resources)
21. create logout feature
22. create LogoutController
23. mapping LogoutController in web.xml
24. mapping url with href component
   Try to run
   - login
   - logout
   - after logout, try to access protected resources(http://localhost:8080/myWebApp/home-servlet)
   - not succes to access protected resources because session invalid

Thank you, happy learning and happy sharing !!!

How to run?
1. import project into intellij idea
2. run this command(mvn jetty:run) using terminal inside intellij idea
3. hit this url (http://localhost:8080/myWebApp/)

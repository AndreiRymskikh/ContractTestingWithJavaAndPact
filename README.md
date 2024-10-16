# ContractTestingWithJavaAndPact

Step by Step Instructions to Setup Project Architecture in Local Machine


Step 1-
Install Eclipse Spring Suite Plugin



Step 2-
Download below 2 Spring Boot Projects from this lecture and Import Projects into Eclipse.

1. SpringBootRestService (Consumer Microservice) -Library
2. Courses                        (Provider Microservice). - Course




Step 3-
Install MYSQL and Run below DB Scripts on MYSQL WorkBench





****** SpringBootRestService Microservice****************



CREATE DATABASE APIDevelopSpringBoot;

use APIDevelopSpringBoot;

CREATE TABLE Storage2(book_name varchar(50),id varchar(50),

isbn varchar(50),

aisle varchar(50),

author varchar(50),PRIMARY KEY (id));

INSERT INTO Storage2(book_name,id,isbn,aisle,author) values("Microservices","hrtge43","hrtge","43","Shetty");
INSERT INTO Storage2(book_name,id,isbn,aisle,author) values("Selenium","khuys21","khuys","21","Shetty");
INSERT INTO Storage2(book_name,id,isbn,aisle,author) values("Appium","ttefs36","ttefs","36","Shetty");


select * from Storage2;





****** Courses Microservice****************


CREATE DATABASE Courses;

use Courses;

CREATE TABLE Storage2(course_name varchar(50),id varchar(50),

price int,

category varchar(50),

PRIMARY KEY (course_name));


INSERT INTO Storage2(course_name,id,price,category) values("Microservices testing","2",23,"api");
INSERT INTO Storage2(course_name,id,price,category) values("Selenium","3",66,"web");
INSERT INTO Storage2(course_name,id,price,category) values("Appium","12",13,"mobile");




Step 5-

Update the DB Properties into Spring Boot Projects





Step 6
Start the Spring Boot Server 


Step 7

Test the server 

Step 8-
Walk through the code in high level




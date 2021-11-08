create database student;
use student;
SET GLOBAL time_zone = '+3:00';
CREATE TABLE StudentEnrollment(id int,firstName varchar(50),lastName varchar(50),class varchar(10),nationality varchar(15));
alter table StudentEnrollment add primary key (id);

INSERT INTO StudentEnrollment values(1,"Tan","Skipper","1-A","Singapore");
INSERT INTO StudentEnrollment values(2,"Wheng","Mayang","1-B","Phillipines");
INSERT INTO StudentEnrollment values(3,"Bo","Tao","2-A","China");
INSERT INTO StudentEnrollment values(4,"Rohan","Sinha","3-A","India");
INSERT INTO StudentEnrollment values(5,"Jorge","Silva","5-B","Switzerland");
INSERT INTO StudentEnrollment values(6,"Rohan","Sinha","3-A","India");
INSERT INTO StudentEnrollment values(7,"Jorge","Silva","3-A","Switzerland");


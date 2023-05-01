CREATE TABLE IF NOT EXISTS moviesAction(Name TEXT NOT NULL, 
Director TEXT NOT NULL,
 Year INTEGER NOT NULL,
 Rating TEXT NOT NULL,
 Actors TEXT NOT NULL);
--INSERT INTO emp(empid, empname,email) VALUES(1,"Sam","test@test.com");


INSERT INTO moviesAction VALUES ('Name','Director','2000','4','jack,rendy');
--INSERT INTO movies VALUES ('3','Name3','Director2','20020','42','Actory');
--INSERT INTO movies VALUES ('3','Name3','Director3','20300','43','Actorx');
SELECT * FROM moviesAction;


CREATE TABLE IF NOT EXISTS moviesAnimated(Name TEXT NOT NULL, 
Director TEXT NOT NULL,
 Year INTEGER NOT NULL,
 Rating TEXT NOT NULL,
 Animators TEXT NOT NULL);

 INSERT INTO moviesAnimated VALUES ('Name','Director','2000','4','Animator');
--INSERT INTO movies VALUES ('3','Name3','Director2','20020','42','Actory');
--INSERT INTO movies VALUES ('3','Name3','Director3','20300','43','Actorx');
SELECT * FROM moviesAnimated;
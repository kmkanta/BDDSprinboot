DROP TABLE IF EXISTS user;
CREATE TABLE User AS SELECT * FROM CSVREAD("classpath:dataBase/TestData.csv");
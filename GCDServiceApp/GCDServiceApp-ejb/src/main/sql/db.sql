CREATE TABLE IF NOT EXISTS PARAMETERS (
	id int(11) primary key auto_increment,
	parameter1 int(11) NOT NULL,
	parameter2 int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS GCD (
	id int(11) primary key auto_increment,
	computedGCD int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS USER(
	id int(11) primary key auto_increment,
	username varchar(100) NOT NULL,
	password varchar(100) NOT NULL
);


INSERT INTO USER (username, password) 
	SELECT 'unicouser', md5('P@ssw0rd!') FROM DUAL
WHERE NOT EXISTS (SELECT * FROM USER WHERE username = 'unicouser');
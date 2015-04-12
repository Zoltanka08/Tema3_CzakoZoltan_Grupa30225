CREATE DATABASE IF NOT EXISTS restorante;

USE restorante;

CREATE TABLE IF NOT EXISTS Admin
(
id INT NOT NULL AUTO_INCREMENT,
nume varchar(30) unique,
username varchar(30) unique,
pass varchar(25),

PRIMARY KEY(id)

);

CREATE TABLE IF NOT EXISTS Customer
(
id INT NOT NULL AUTO_INCREMENT,
nume VARCHAR(30) NOT NULL,
tel VARCHAR(15) not null,
email VARCHAR(20) not null unique,
city varchar(20) not null,
str varchar(20) not null,
nr int not null,
Pass varchar(20) not null,
Username varchar(25) not null unique,
bank_acc VARCHAR(15),

PRIMARY KEY(id)
    
);

CREATE TABLE IF NOT EXISTS Product
(
id INT NOT NULL AUTO_INCREMENT,
nume VARCHAR(30) NOT NULL unique,
price float,

PRIMARY KEY(id)

);

CREATE TABLE IF NOT EXISTS Warehouse
(
id INT NOT NULL AUTO_INCREMENT,
location int,
pieces int,
id_Product int,

PRIMARY KEY(id),

CONSTRAINT fk_Stock_Product
	FOREIGN KEY (id_Product)
	REFERENCES Product(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE

);

CREATE TABLE IF NOT EXISTS Orders
(
id INT NOT NULL AUTO_INCREMENT,
id_Customer int,
id_Product int,
pieces int,

PRIMARY KEY(id),

CONSTRAINT fk_Orders_Products
	FOREIGN KEY (id_Product)
	REFERENCES Product(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE,
    
CONSTRAINT fk_Orders_Customers
	FOREIGN KEY (id_Customer)
	REFERENCES Customer(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE

);

CREATE TABLE IF NOT EXISTS OPDept
(
id INT NOT NULL AUTO_INCREMENT,
start_date date,
end_date date,
id_Order int,

PRIMARY KEY(id),

CONSTRAINT fk_OPDept_Orders
	FOREIGN KEY (id_Order)
	REFERENCES Orders(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE

);




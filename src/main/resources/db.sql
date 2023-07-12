Drop DATABASE IF EXISTS sale_db;
create database sale_db;
USE sale_db;

CREATE TABLE Customers (
    cust_id INT auto_increment primary key,
    Name VARCHAR(255) not null,
    Address VARCHAR(255)  not null,
    Email VARCHAR(255)  not null,
    Phone DECIMAL(10,0)  not null
);

CREATE TABLE Products (
	p_id INT auto_increment primary key,
	name VARCHAR(255) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	description VARCHAR(500),
    quantity DECIMAL(4,0) NOT NULL
);

CREATE TABLE orders (
  order_id INT auto_increment primary key,
  customer_id INT NOT NULL,
  order_date DATE NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  status VARCHAR(15),
  FOREIGN KEY (customer_id) REFERENCES customers(cust_id)
);

CREATE TABLE order_items (
  id INT auto_increment primary key,
  order_id INT,
  product_id INT,
  quantity INT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  FOREIGN KEY fk_item_order(order_id) REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES products(p_id)
);


Drop DATABASE IF EXISTS sale_db;
create database sale_db;
USE sale_db;

CREATE TABLE Customers (
    cust_id INT PRIMARY KEY,
    Name VARCHAR(255) not null,
    Address VARCHAR(255)  not null,
    Email VARCHAR(255)  not null,
    Phone VARCHAR(20)  not null
);



INSERT INTO Customers (cust_id, Name, Address, Email, Phone) VALUES
(1, 'John Smith', '123 Main St, Anytown USA', 'john.smith@example.com', '555-1234'),
(2, 'Sarah Johnson', '456 Oak Ave, Anytown USA', 'sarah.johnson@example.com', '555-5678'),
(3, 'Tom Williams', '789 Elm St, Anytown USA', 'tom.williams@example.com', '555-9012'),
(4, 'Amy Brown', '321 Pine St, Anytown USA', 'amy.brown@example.com', '555-3456'),
(5, 'Michael Davis', '654 Maple Ave, Anytown USA', 'michael.davis@example.com', '555-7890');

CREATE TABLE Products (
	p_id INT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	description VARCHAR(500),
    quantity DECIMAL(4,0) NOT NULL
);

INSERT INTO Products (p_id, Name, Description, price, quantity) VALUES
(1, 'Laptop', '14" Full HD, 8GB RAM, 256GB SSD', 799.99, 100),
(2, 'Smartphone', '6.5" AMOLED Display, 128GB Storage', 599.99, 0),
(3, 'Tablet', '10.1" HD Screen, 64GB Storage', 349.99, 1),
(4, 'Desktop PC', '27" 4K Display, 16GB RAM, 1TB SSD', 1499.99, 5),
(5, 'Printer', 'Color LaserJet, Double-sided Printing', 299.99, 90);

CREATE TABLE orders (
  order_id INT PRIMARY KEY,
  customer_id INT NOT NULL,
  order_date DATE NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  status VARCHAR(15),
  FOREIGN KEY (customer_id) REFERENCES customers(cust_id)
);

INSERT INTO orders (order_id, customer_id, order_date, total, status) VALUES
(1, 3, '2022-01-01', 119.97, 'ORDER'),
(2, 1, '2022-01-02', 39.98, 'IN_PROGRESS'),
(3, 2, '2022-01-03', 109.97, 'SENT'),
(4, 4, '2022-01-04', 59.99, 'ORDER'),
(5, 5, '2022-01-05', 179.97, 'SENT');

CREATE TABLE order_items (
  id INT PRIMARY KEY,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES products(p_id)
);

INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES
(1, 1, 2, 1, 29.99),
(2, 1, 3, 2, 39.99),
(3, 2, 1, 2, 39.98),
(4, 3, 5, 3, 179.97),
(5, 5, 4, 1, 49.99);


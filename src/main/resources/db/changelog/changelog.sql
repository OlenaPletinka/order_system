-- liquibase formatted sql

-- changeset liquibase:1
CREATE DATABASE IF NOT EXISTS ordering;

-- changeset liquibase:2
CREATE TABLE orders (
  id INT NOT NULL,
  user_name VARCHAR(45) NOT NULL,
  time DATETIME NOT NULL,
  status VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);

-- changeset liquibase:3
CREATE TABLE payment (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  date DATETIME NOT NULL,
  total VARCHAR(45) NOT NULL,
  status VARCHAR(45) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
  UNIQUE INDEX order_id_UNIQUE (order_id ASC) VISIBLE,
  CONSTRAINT fk_payment_orders
    FOREIGN KEY (order_id)
    REFERENCES orders (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- changeset liquibase:4
CREATE TABLE ticket (
  id int NOT NULL AUTO_INCREMENT,
  order_id int NOT NULL,
  seats_number int NOT NULL,
  event_name varchar(45) NOT NULL,
  event_time datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  KEY fk_ticket_orders (order_id),
  CONSTRAINT fk_ticket_orders FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE);

-- changeset liquibase:5
     INSERT INTO orders
     (id, time, status, user_name)
     VALUES (100,'2023-09-01 20:45:29.686723','NEW','Olena Pletinka'),
     (112,'2023-09-01 20:48:21.686123','NEW','Jon Kim');

-- changeset liquibase:6
    INSERT INTO ticket
    (seats_number, event_time, id, order_id, event_name)
    VALUES (1011,'2023-12-12 12:30:00.000000', 1, 100,'Ballet'),
    (1012,'2023-12-12 12:30:00.000000', 2, 100,'Ballet'),
    (1004,'2023-08-15 19:30:00.000000', 3, 112,'Opera');

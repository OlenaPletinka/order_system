-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE orders (
  id INT NOT NULL,
  time DATETIME NOT NULL,
  status VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);

-- changeset liquibase:2
CREATE TABLE payment (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  date DATETIME NOT NULL,
  total VARCHAR(45) NOT NULL,
  status VARCHAR(45) NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
  UNIQUE INDEX order_id_UNIQUE (order_id ASC) VISIBLE,
  CONSTRAINT fk_payment_order_id
    FOREIGN KEY (order_id)
    REFERENCES orders (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);


-- changeset liquibase:3
 CREATE TABLE ticket (
   id INT NOT NULL AUTO_INCREMENT,
   order_id INT NOT NULL,
   seats_number INT NOT NULL,
   event_name VARCHAR(45) NOT NULL,
   event_time DATETIME NOT NULL,
   PRIMARY KEY (id),
   UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
   UNIQUE INDEX order_id_UNIQUE (order_id ASC) VISIBLE,
   CONSTRAINT fk_ticket_order_id
     FOREIGN KEY (order_id)
     REFERENCES orders (id)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION);
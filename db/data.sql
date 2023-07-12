CREATE TABLE users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(255),
  dni BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('ADMIN', 123456789, 'admin', 'passwordsinhash', 'admin', 'admin@admin.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('PROFESSOR', 123456789, 'Stan', 'passwordsinhash', 'Lee', 'stanlee@example.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('PROFESSOR', 987654321, 'John', 'passwordsinhash', 'Doe', 'johndoe@example.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('STUDENT', 678954321, 'Tony', 'passwordsinhash', 'Stark', 'tonystark@example.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('STUDENT', 246813579, 'Jhon', 'passwordsinhash', 'Wick', 'user@example.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('STUDENT', 369258147, 'Peter', 'passwordsinhash', 'Parker', 'peterparker@example.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('STUDENT', 987654123, 'Bruce', 'passwordsinhash', 'Banner', 'brucebanner@example.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('PROFESSOR', 321654987, 'Natasha', 'passwordsinhash', 'Romanoff', 'natasha@example.com');

INSERT INTO users (role, dni, name, password, lastname, email)
VALUES ('STUDENT', 456789123, 'Wanda', 'passwordsinhash', 'Maximoff', 'wanda.maximoff@example.com');


       
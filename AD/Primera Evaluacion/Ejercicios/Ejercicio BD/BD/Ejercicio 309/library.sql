drop database if exists libary;

CREATE DATABASE library;

-- Usar la base de datos creada
USE library;


-- Crear las tablas
CREATE TABLE book (
    idBook INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20),
    title VARCHAR(200),
    authors VARCHAR(300),
    year INT
);

CREATE TABLE client (
    idClient INT PRIMARY KEY AUTO_INCREMENT,
    DNI CHAR(9),
    name VARCHAR(200),
    email VARCHAR(200)
);

CREATE TABLE loan (
    idLoan INT PRIMARY KEY AUTO_INCREMENT,
    idBook INT,
    idClient INT,
    date DATE,
    borrowed BOOL,
    FOREIGN KEY (idBook) REFERENCES book(idBook),
    FOREIGN KEY (idClient) REFERENCES client(idClient)
);

-- Insertar datos en las tablas
INSERT INTO book (idBook, code, title, authors, year) VALUES 
(1, 'asdfg', 'Pride and Prejudice', 'Jane Austen', 1870),
(2, 'asrwqeg', 'Persuasion', 'Jane Austen', 1875),
(3, 'asdfdsa', 'El Quijote', 'Miguel de Cervantes', 1700);

INSERT INTO client (idClient, DNI, name, email) VALUES 
(1, '11111111A', 'Ana', 'ana@gmail.com'),
(2, '22222222B', 'Pablo', 'pablo@gmail.com');

INSERT INTO loan (idLoan, idBook, idClient, date, borrowed) VALUES 
(1, 1, 1, '2013-11-05', 0),
(2, 2, 1, '2013-02-05', 1),
(3, 3, 1, '2013-12-10', 1),
(4, 2, 2, '2013-11-05', 0),
(5, 3, 2, '2013-11-05', 0);

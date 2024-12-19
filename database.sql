-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS coursejdbc;
USE coursejdbc;

-- Criação da tabela department
CREATE TABLE IF NOT EXISTS department (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

-- Dados para a tabela department
INSERT INTO department (Id, Name) VALUES (1, 'Computers');
INSERT INTO department (Id, Name) VALUES (2, 'Electronics');
INSERT INTO department (Id, Name) VALUES (3, 'Fashion');
INSERT INTO department (Id, Name) VALUES (4, 'Books');
INSERT INTO department (Id, Name) VALUES (5, 'Computers');
INSERT INTO department (Id, Name) VALUES (6, 'Electronics');
INSERT INTO department (Id, Name) VALUES (7, 'Fashion');
INSERT INTO department (Id, Name) VALUES (8, 'Books');

-- Criação da tabela seller
CREATE TABLE IF NOT EXISTS seller (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Email VARCHAR(100),
    BirthDate DATE,
    BaseSalary DECIMAL(10, 2),
    DepartmentId INT,
    FOREIGN KEY (DepartmentId) REFERENCES department(Id)
) ENGINE=InnoDB;

-- Dados para a tabela seller
INSERT INTO seller (Id, Name, Email, BirthDate, BaseSalary, DepartmentId)
VALUES (7, 'Bob Brown', 'bob@gmail.com', '1998-04-21', 1000.00, 1);
INSERT INTO seller (Id, Name, Email, BirthDate, BaseSalary, DepartmentId)
VALUES (8, 'Maria Green', 'maria@gmail.com', '1979-12-31', 3500.00, 2);
INSERT INTO seller (Id, Name, Email, BirthDate, BaseSalary, DepartmentId)
VALUES (9, 'Alex Grey', 'alex@gmail.com', '1988-01-15', 2200.00, 1);
INSERT INTO seller (Id, Name, Email, BirthDate, BaseSalary, DepartmentId)
VALUES (10, 'Martha Red', 'martha@gmail.com', '1993-11-30', 3000.00, 4);
INSERT INTO seller (Id, Name, Email, BirthDate, BaseSalary, DepartmentId)
VALUES (11, 'Donald Blue', 'donald@gmail.com', '2000-11-09', 4000.00, 3);
INSERT INTO seller (Id, Name, Email, BirthDate, BaseSalary, DepartmentId)
VALUES (12, 'Alex Pink', 'alexpink@gmail.com', '1997-03-04', 3000.00, 2);
INSERT INTO seller (Id, Name, Email, BirthDate, BaseSalary, DepartmentId)
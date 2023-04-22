SnowdropsProject College Database - Steps made in creating the database

CREATE DATABASE college;
USE college;

CREATE TABLE student (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE address (
    id INT NOT NULL AUTO_INCREMENT,
    student_id INT NOT NULL,
    postal_code VARCHAR(50) NOT NULL,
    county VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE TABLE course (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    points INT NOT NULL,
    length VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE course_student (
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (student_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

INSERT INTO student (first_name, last_name, email) VALUES
    ('Jack', 'Murphy', 'jack@atu.ie'),
    ('Liam', 'Kelly', 'liam@atu.ie'),
    ('Oliver', 'Byrne', 'oliver@atu.ie'),
    ('James', 'Doyle', 'james@atu.ie'),
    ('William', 'Smith', 'william@atu.ie'),
    ('Katie', 'Walsh', 'katie@atu.ie'),
    ('Emma', 'Jones', 'emma@atu.ie'),
    ('Olivia', 'Taylor', 'olivia@atu.ie'),
    ('Aoife', 'Brown', 'aoife@atu.ie'),
    ('Sophia', 'Davis', 'sophia@atu.ie');

INSERT INTO address (student_id, postal_code, county, street) VALUES
    (1, 'H91ETN2', 'Galway', 'Rahoon'),
    (2, 'H91RKC9', 'Galway', 'Circular Rd'),
    (3, 'H91L8TM', 'Galway', 'Greenfields Rd'),
    (4, 'H91JK7F', 'Galway', 'Corrib Village'),
    (5, 'H916U8I', 'Galway', 'Lurgan Park'),
    (6, 'H91K8GT', 'Galway', 'Ballybane'),
    (7, 'H91ZZXX', 'Galway', 'Castle Park'),
    (8, 'H9153XY', 'Galway', 'Menlo'),
    (9, 'H91KKK7', 'Galway', 'Tuam Rd'),
    (10, 'H91LLL7', 'Galway', 'Salthill');

INSERT INTO course (name, points, length) VALUES
    ('Electronics', 400, '4 years'),   
    ('Business', 420, '3 years'),    
    ('Science', 500, '4 years'),    
    ('Medicine', 625, '5 years'),
    ('Arts', 300, '3 years');

INSERT INTO course_student (student_id, course_id) VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 2),
    (5, 3),
    (6, 3),
    (7, 4),
    (8, 5),
    (9, 5),
    (10, 5);

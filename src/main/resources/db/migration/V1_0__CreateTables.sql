DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id integer primary key auto_increment,
    name varchar(50) not null
);

INSERT INTO customer (name)
VALUES ('Customer #1'),
       ('Customer #2'),
       ('Customer #3');


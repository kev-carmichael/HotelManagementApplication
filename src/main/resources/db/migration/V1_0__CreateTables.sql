DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS room_type;
DROP TABLE IF EXISTS room;

CREATE TABLE address
(
    address_id    integer primary key auto_increment,
    street_number varchar(6)  not null,
    street        varchar(50) not null,
    town          varchar(50) not null,
    postcode      varchar(8)  not null
);

CREATE TABLE customer
(
    id      integer primary key auto_increment,
    name    varchar(50) not null,
    address integer not null,
    foreign key (address) references address(address_id)
);

CREATE TABLE room_type
(
    room_type_id      integer primary key auto_increment,
    type    varchar(50) not null
);

CREATE TABLE room
(
    room_id     integer primary key auto_increment,
    room_number varchar(6) not null,
    room_type   integer not null
);


INSERT INTO address (street_number, street, town, postcode)
VALUES ('1', 'A Avenue', 'Alpha Town', 'A1 1AA'),
       ('2', 'B Boulevard', 'Bravo Town', 'B2 2BB'),
       ('3', 'C Close', 'Charles Town', 'C3 3CC');

INSERT INTO customer (name, address)
VALUES ('Customer #1', 1),
       ('Customer #2', 2),
       ('Customer #3', 3);

INSERT INTO room_type (type)
VALUES ('SINGLE'),
       ('DOUBLE');

INSERT INTO room (room_number, room_type)
VALUES ('101', 1),
       ('202', 2),
       ('303', 2);

drop database if exists railway;
create database railway;
use railway;

create table Train(
train_id int primary key,
train_name varchar(200) ,
sou varchar(200),
destination varchar(200),
seat int,
remaining_seat int,
date date,
rate int
);

create table Customer(
customer_id int primary key,
customer_name varchar(200),
gender varchar(10),
age int,
pasword varchar(20)
);

create table Payment(
payment_id int primary key,
customer_id int,
payment_date date,
amount int,
status varchar(10),
payment_mode varchar(10),
foreign key(customer_id) references Customer(customer_id)
);

create table Ticket(
ticket_id int primary key,
customer_id int,
train_id int,
payment_id int,
ticket_date date,
booking_date date,
seat_no int ,
status varchar(20),
foreign key (customer_id) references Customer(customer_id),
foreign key (train_id) references Train(train_id)
);

create table administrator(
username varchar(15),
pass_word varchar(15),
primary key(username,pass_word)
);


insert into administrator
values 
('aryan','xyz'),
('rishi','abc')
;




/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  florian.brosig
 * Created: 21.05.2018
 */

CREATE TABLE USER (
    id int auto_INCREMENT PRIMARY KEY,
    lastname_id int,
    firstname_id int,
    email_id int,
    loginname_id int,
    passwordHash_id int,
    isMale boolean,
    isAdmin boolean,
    isActive boolean
);

CREATE TABLE NAME (
    id int auto_INCREMENT PRIMARY KEY,
    name varchar
);

CREATE TABLE EMAIL (
    id int auto_INCREMENT PRIMARY KEY,
    name varchar
);

CREATE TABLE LOGINNAME (
    id int auto_INCREMENT PRIMARY KEY,
    name varchar
);

CREATE TABLE PASSWORDHASH (
    id int auto_INCREMENT PRIMARY KEY,
    name varchar
);




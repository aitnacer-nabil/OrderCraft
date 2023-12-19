CREATE DATABASE  IF NOT EXISTS artwood;

CREATE TABLE  IF NOT EXISTS client(
                                      client_id INTEGER primary key ,
                                      name VARCHAR(100) not null ,
    email VARCHAR(100) not null ,
    phone VARCHAR(50) not null ,
    adress varchar(100) not null
    );
CREATE TABLE  if not exists user(
                                    user_id INTEGER primary key ,
                                    user_name varchar(40) not null ,
    user_email varchar(50) not null ,
    user_password varchar(50) not null

    );
CREATE TABLE  if not exists commande(
                                        commande_id INTEGER primary key ,
                                        date_ajoute TIMESTAMP default CURRENT_TIME,
                                        date_update TIMESTAMP not null
);
CREATE  TABLE IF NOT EXISTS user_status(
                                           id INTEGER primary key ,
                                           status_name varchar(40) not null
    );
CREATE TABLE  IF NOT EXISTS commande_status(
                                               id INTEGER primary key ,
                                               status_name varchar(40) not null
    );
create  table  if not exists produit(
                                        produit_id INTEGER primary key ,
                                        name varchar(25) not null ,
    description varchar(40) not null ,
    prix FLOAT not null
    );
create table if not exists stock(
                                    id INTEGER primary key ,
                                    qte INTEGER
);
use artwood;
INSERT INTO commande_status (status_name) values ('PREPARATION','COMPLETE','ANULLE');
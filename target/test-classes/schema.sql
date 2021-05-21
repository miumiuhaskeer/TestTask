CREATE TABLE cats (
    id int not null,
    name varchar,
    age int,
    PRIMARY KEY (id)
);
CREATE SEQUENCE cat_seq INCREMENT 1 START 1 MINVALUE 1;
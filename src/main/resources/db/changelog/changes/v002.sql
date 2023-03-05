CREATE TABLE IF NOT EXISTS roles (
   id SERIAL NOT NULL,
   name varchar(20),
   PRIMARY KEY(id)
);
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

CREATE TABLE IF NOT EXISTS users (
   id SERIAL NOT NULL,
   password varchar(120),
   username varchar(20),
   PRIMARY KEY(id)
);

INSERT INTO users (password, username) VALUES ('$2a$12$xM7pAqDSriZP86XqOm9v.O5myp2piUsorxaYT0UwAeakcrfJob0Sa', 'admin');
INSERT INTO users (password, username) VALUES ('$2a$12$r7f8RogT/u4PHVbrX9o6qe4VQXfC46vHhTSW7ngJBRh9qaeFIsOVC', 'user');

CREATE TABLE IF NOT EXISTS users_roles (
   user_id bigint,
   role_id bigint,
   PRIMARY KEY(user_id, role_id)
);

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
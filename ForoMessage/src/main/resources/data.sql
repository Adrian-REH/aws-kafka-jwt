insert into role (description, name) VALUES ('Admin role', 'ADMIN');
insert into role (description, name) VALUES ('Manager role', 'MANAGER');
insert into role (description, name) VALUES ('User role', 'USER');

insert into db_user(username, email, password) VALUES ('adrian', 'adrian@gmail.com', '$2a$10$Uum1/wmFffQ2iz8/MD2gG.Mwrtigik6wyw6H/2NsXKGZbnjfUv96.');
insert into user_roles(user_id, role_id) VALUES (1, 3);

insert into db_user(username, email, password) VALUES ('adrian2', 'adrian@gmail.com2', '$2a$10$Uum1/wmFffQ2iz8/MD2gG.Mwrtigik6wyw6H/2NsXKGZbnjfUv96.');
insert into user_roles(user_id, role_id) VALUES (2, 1);

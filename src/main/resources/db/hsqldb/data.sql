-- INSERT USER DETAILS
INSERT INTO user_details VALUES (1, '5th Avenue 255', 'New York');
INSERT INTO user_details VALUES (2, 'Japanese Street', 'Tokyo');


-- INSERT USERS
INSERT INTO users VALUES (1, 'user', 'Eric', NULL, 'Hanks', '$2a$10$XO8w6gPq2Rysd1PsAJCrBuLEozFylseNmCA/lD5X7SlkeoF816IBG', 'user@webstore.com', 1, 1);
INSERT INTO users VALUES (2, 'admin', 'David', 'Ryan', 'Stewart', '$2a$10$ruJG.3IKT6bFpDrVUGj.zuVoZBR1KuGTQ/d78A8hKlBKCCRWXe0lW', 'admin@webstore.com', 2, 1);


-- INSERT AUTHORITIES
INSERT INTO authorities VALUES (1, 'user', 'ROLE_USER');
INSERT INTO authorities VALUES (2, 'admin', 'ROLE_USER');
INSERT INTO authorities VALUES (3, 'admin', 'ROLE_ADMIN');


-- INSERT BOOK DETAILS
INSERT INTO book_details VALUES (1, 142, 'Książka o Kubusiu Puchatku.');		
INSERT INTO book_details VALUES (2, 960, 'Harry Potter po raz piątu w akcji.');
INSERT INTO book_details VALUES (3, 960, 'Film z więziennym klimatem');
INSERT INTO book_details VALUES (4, 960, 'Książka o frameworku ułatwiającym życie programistom Javy.');
INSERT INTO book_details VALUES (5, 960, 'Lektura obowiązkowa dla programistów Javy!');
INSERT INTO book_details VALUES (6, 960, 'Wielki Brat Patrzy!');
	
	
-- INSERT BOOKS
INSERT INTO books VALUES (1, 'Kubuś Puchatek', 1, 25.99, 60);
INSERT INTO books VALUES (2, 'Harry Potter. Tom 5. Harry Potter i Zakon Feniksa', 2, 39.95, 75);
INSERT INTO books VALUES (3, 'Zielona mila', 3, 29.90, 50);
INSERT INTO books VALUES (4, 'Spring w akcji. Wydanie IV', 4, 89.99, 25);
INSERT INTO books VALUES (5, 'Spring w praktyce', 5, 64.95, 15);
INSERT INTO books VALUES (6, 'Rok 1984', 6, 24.95, 50);


-- INSERT AUTHORS
INSERT INTO authors VALUES (1, 'Alexander', 'Milne');
INSERT INTO authors VALUES (2, 'Joanne', 'Rowling');
INSERT INTO authors VALUES (3, 'Stephen', 'King');
INSERT INTO authors VALUES (4, 'Craig', 'Walls');
INSERT INTO authors VALUES (5, 'Willie', 'Wheeler');
INSERT INTO authors VALUES (6, 'Joshua', 'White');
INSERT INTO authors VALUES (7, 'George', 'Orwell');


-- INSERT BOOK-AUTHORS <MANY TO MANY>
INSERT INTO books_authors VALUES (1, 1);
INSERT INTO books_authors VALUES (2, 2);
INSERT INTO books_authors VALUES (3, 3);
INSERT INTO books_authors VALUES (4, 4);
INSERT INTO books_authors VALUES (5, 5);
INSERT INTO books_authors VALUES (5, 6);
INSERT INTO books_authors VALUES (6, 7);

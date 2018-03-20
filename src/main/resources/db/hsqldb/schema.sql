-- TABLE books
CREATE TABLE books (
	id						INTEGER IDENTITY PRIMARY KEY,
	title					VARCHAR(120),
	book_details_id 		INTEGER,
	price					FLOAT,
	amount					INTEGER
);
CREATE INDEX books_title ON books (title);


-- TABLE authors
CREATE TABLE authors (
	id						INTEGER IDENTITY PRIMARY KEY,
	first_name				VARCHAR(30),
	last_name  				VARCHAR_IGNORECASE(30)
);
CREATE INDEX authors_last_name ON authors (last_name);


-- TABLE books_authors <MANY TO MANY>
CREATE TABLE books_authors (
	book_id					INTEGER,
	author_id 				INTEGER
);


-- TABLE book_details
CREATE TABLE book_details (
	id						INTEGER IDENTITY PRIMARY KEY,
	pages					INTEGER,
	description				varchar(231)
);


-- ADD CONSTRAINTS

ALTER TABLE books
	ADD CONSTRAINT fk_books_book_details_id
	FOREIGN KEY(book_details_id)
	REFERENCES book_details(id);

ALTER TABLE books_authors
	ADD CONSTRAINT fk_books_authors_book_id
	FOREIGN KEY(book_id)
	REFERENCES books(id);
	
ALTER TABLE books_authors
	ADD CONSTRAINT fk_books_authors_author_id
	FOREIGN KEY(author_id)
	REFERENCES authors(id);
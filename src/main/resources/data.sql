DROP TABLE IF EXISTS product;

CREATE TABLE product (
	id INT NOT NULL AUTO_INCREMENT,
	details VARCHAR(255),
	name VARCHAR(255),
	price DOUBLE,
	category_id INT,
	PRIMARY KEY (id)
);

CREATE INDEX FK1mtsbur82frn64de7balymq9s ON product (category_id ASC);

insert into product (details, name, price, category_id) values('Just a default product', 'Things', '42.00', 1);

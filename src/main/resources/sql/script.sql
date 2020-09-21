DROP TABLE IF EXISTS cheque CASCADE;
DROP TABLE IF EXISTS customer CASCADE ;
DROP TABLE IF EXISTS cheque_book CASCADE;
DROP TABLE IF EXISTS cheque_book_cheque CASCADE;

CREATE TABLE cheque
(
    service_type VARCHAR(255) PRIMARY KEY,
    price INTEGER,
    count INTEGER,
    cost INTEGER
);

CREATE TABLE customer
(
    customer_id INTEGER PRIMARY KEY,
    name VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE cheque_book
(
    account_number INTEGER PRIMARY KEY,
    customer_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE cheque_book_cheque
(
    account_number INTEGER,
    service_type VARCHAR(255),
    PRIMARY KEY(account_number, service_type),
    FOREIGN KEY (service_type) REFERENCES cheque(service_type) ON UPDATE CASCADE,
    FOREIGN KEY (account_number) REFERENCES cheque_book(account_number) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO cheque VALUES ('Ноготочки', 1000, 2, 2000);
INSERT INTO cheque VALUES ('Педикюр', 2000, 2, 4000);
INSERT INTO cheque VALUES ('Покраска', 1500, 7, 10500);
INSERT INTO cheque VALUES ('Выщипывание', 1000, 2, 2000);
INSERT INTO cheque VALUES ('Маска', 3000, 4, 12000);
INSERT INTO cheque VALUES ('Завивка', 15000, 1, 15000);
INSERT INTO cheque VALUES ('Стрижка', 300, 1, 300);

INSERT INTO customer VALUES (1, 'Dima', 'Bukinator', 'Anime');
INSERT INTO customer VALUES (2, 'Liza', 'Ellariona', 'Kekw');
INSERT INTO customer VALUES (3, 'Andrey', 'Nowas', 'BibleThump');
INSERT INTO customer VALUES (4, 'Maksim', 'LMK', 'PogChamp');
INSERT INTO customer VALUES (5, 'Loev', 'WoW', 'Arena');

INSERT INTO cheque_book VALUES (100, 1);
INSERT INTO cheque_book VALUES (101, 1);
INSERT INTO cheque_book VALUES (102, 2);
INSERT INTO cheque_book VALUES (103, 2);
INSERT INTO cheque_book VALUES (104, 3);
INSERT INTO cheque_book VALUES (105, 3);
INSERT INTO cheque_book VALUES (106, 4);
INSERT INTO cheque_book VALUES (107, 4);
INSERT INTO cheque_book VALUES (108, 5);
INSERT INTO cheque_book VALUES (109, 5);

INSERT INTO cheque_book_cheque VALUES (100, 'Ноготочки');
INSERT INTO cheque_book_cheque VALUES (100, 'Педикюр');
INSERT INTO cheque_book_cheque VALUES (100, 'Покраска');
INSERT INTO cheque_book_cheque VALUES (100, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (100, 'Маска');

INSERT INTO cheque_book_cheque VALUES (101, 'Покраска');
INSERT INTO cheque_book_cheque VALUES (101, 'Стрижка');
INSERT INTO cheque_book_cheque VALUES (101, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (101, 'Завивка');
INSERT INTO cheque_book_cheque VALUES (101, 'Педикюр');

INSERT INTO cheque_book_cheque VALUES (102, 'Покраска');
INSERT INTO cheque_book_cheque VALUES (102, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (102, 'Маска');
INSERT INTO cheque_book_cheque VALUES (102, 'Педикюр');
INSERT INTO cheque_book_cheque VALUES (102, 'Ноготочки');

INSERT INTO cheque_book_cheque VALUES (103, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (103, 'Ноготочки');
INSERT INTO cheque_book_cheque VALUES (103, 'Педикюр');
INSERT INTO cheque_book_cheque VALUES (103, 'Стрижка');
INSERT INTO cheque_book_cheque VALUES (103, 'Покраска');

INSERT INTO cheque_book_cheque VALUES (104, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (104, 'Маска');
INSERT INTO cheque_book_cheque VALUES (104, 'Педикюр');
INSERT INTO cheque_book_cheque VALUES (104, 'Завивка');
INSERT INTO cheque_book_cheque VALUES (104, 'Стрижка');

INSERT INTO cheque_book_cheque VALUES (105, 'Педикюр');
INSERT INTO cheque_book_cheque VALUES (105, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (105, 'Завивка');
INSERT INTO cheque_book_cheque VALUES (105, 'Маска');
INSERT INTO cheque_book_cheque VALUES (105, 'Ноготочки');

INSERT INTO cheque_book_cheque VALUES (106, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (106, 'Маска');
INSERT INTO cheque_book_cheque VALUES (106, 'Ноготочки');
INSERT INTO cheque_book_cheque VALUES (106, 'Покраска');
INSERT INTO cheque_book_cheque VALUES (106, 'Стрижка');

INSERT INTO cheque_book_cheque VALUES (107, 'Маска');
INSERT INTO cheque_book_cheque VALUES (107, 'Стрижка');
INSERT INTO cheque_book_cheque VALUES (107, 'Покраска');
INSERT INTO cheque_book_cheque VALUES (107, 'Завивка');
INSERT INTO cheque_book_cheque VALUES (107, 'Ноготочки');

INSERT INTO cheque_book_cheque VALUES (108, 'Педикюр');
INSERT INTO cheque_book_cheque VALUES (108, 'Стрижка');
INSERT INTO cheque_book_cheque VALUES (108, 'Маска');
INSERT INTO cheque_book_cheque VALUES (108, 'Покраска');
INSERT INTO cheque_book_cheque VALUES (108, 'Выщипывание');

INSERT INTO cheque_book_cheque VALUES (109, 'Педикюр');
INSERT INTO cheque_book_cheque VALUES (109, 'Покраска');
INSERT INTO cheque_book_cheque VALUES (109, 'Выщипывание');
INSERT INTO cheque_book_cheque VALUES (109, 'Маска');
INSERT INTO cheque_book_cheque VALUES (109, 'Ноготочки');

select * from ((cheque_book_cheque join cheque using (service_type)) join cheque_book using (account_number)) join customer using(customer_id);

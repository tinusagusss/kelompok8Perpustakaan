DROP database perpustakaan_oop;
CREATE database perpustakaan_oop;
use perpustakaan_oop;

create table librarian(
id_lib VARCHAR(3) NOT NULL PRIMARY KEY,
name VARCHAR(25) NOT NULL,
city VARCHAR(15) NOT NULL,
phone VARCHAR(13) NOT NULL);

create table account(
id_acc VARCHAR(3) NOT NULL PRIMARY KEY,
id_lib VARCHAR(3) NOT NULL,
email VARCHAR(25) NOT NULL,
username VARCHAR(20) NOT NULL,
password VARCHAR(18) NOT NULL);

create table member (
id_mem VARCHAR(3) NOT NULL PRIMARY KEY,
name VARCHAR(25) NOT NULL,
city VARCHAR(15) NOT NULL,
phone VARCHAR(13) NOT NULL,
mem_exp DATE NOT NULL);

create table book (
isbn VARCHAR(5) NOT NULL PRIMARY KEY,
title VARCHAR(50) NOT NULL,
page_count INT(5) NOT NULL,
author varchar(25) NOT NULL,
publisher VARCHAR(20) NOT NULL);

create table book_loans (
code VARCHAR(3) NOT NULL PRIMARY KEY,
isbn VARCHAR(5) NOT NULL,
id_mem VARCHAR(3) NOT NULL,
start_date DATE NOT NULL DEFAULT (CURRENT_DATE),
due_date DATE NOT NULL DEFAULT (CURRENT_DATE() + 7),
status BOOLEAN NOT NULL DEFAULT TRUE);

ALTER TABLE account
ADD UNIQUE KEY (id_lib),
ADD FOREIGN KEY (id_lib) REFERENCES librarian(id_lib);

ALTER TABLE book_loans
ADD FOREIGN KEY (isbn) REFERENCES book(isbn);

INSERT INTO member VALUES
('M01', 'Lorem', 'Bandung', '082244334411', '2001/04/28'),
('M02', 'Dolor', 'Medan', '087733124411', '2001/05/18'),
('M03', 'Ipsum', 'Lampung', '081344632311', '2001/06/21');

INSERT INTO book VALUES
('61224', 'Akuntansi Pengantar 1', 170, 'Supardi','Gava Media'),
('91224', 'Kuasa Wanita Jawa', 170, 'Slamet Muljana','Andi Offset'),
('25324', 'Cedera Kepala', 170, 'Eko Priyo Utomo','Kencana'),
('91824', 'Kesehjateraan Sosial', 170,' Lincon Arsyad','Sagung Seto');

INSERT INTO book_loans(code, isbn, id_mem) VALUES
('B01', '61224', 'M01'),
('B02', '91224', 'M02'),
('B03', '25324', 'M03');

INSERT INTO librarian VALUES
('L01', 'Lorem', 'Medan', '082255554444'),
('L02', 'Ipsum', 'Jakarta', '087755552244'),
('L03', 'Dolor', 'Bandung', '081388554554');

INSERT INTO account VALUES
('A01', 'L01', 'lorem@gmail.com', 'Lorem01', '12345'),
('A02', 'L03', 'Dolor@gmail.com', 'Dolor01', '12345'),
('A03', 'L02', 'Ipsum@gmail.com', 'Ipsum01', '12345');







UPDATE book_loans 
set due_date = '2022/01/9'
WHERE  code = "B04";

SELECT * FROM book_loans b
lEFT JOIN member m
ON b.id_mem  = m.id_mem;

SELECT * FROM book_loans;

-- Menghitung keterlambatan hari
-- SELECT due_date - CURRENT_DATE() AS 'result' 
-- FROM book_loans WHERE code = 'B04';

-- Librarian Login
-- SELECT l.name, c.username, c.password FROM account c
-- JOIN librarian l ON c.id_lib = l.id_lib
-- WHERE username = 'Dolor01' AND password = '12345';





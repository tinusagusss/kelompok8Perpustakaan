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

CREATE TABLE payment(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_mem VARCHAR(3) NOT NULL,
amount DOUBLE NOT NULL,
date DATE NOT NULL DEFAULT (CURRENT_DATE)
);

ALTER TABLE payment
ADD FOREIGN KEY (id_mem) REFERENCES member(id_mem);

ALTER TABLE account
ADD UNIQUE KEY (id_lib),
ADD FOREIGN KEY (id_lib) REFERENCES librarian(id_lib);

ALTER TABLE book_loans
ADD FOREIGN KEY (isbn) REFERENCES book(isbn),
ADD FOREIGN KEY (id_mem) REFERENCES member(id_mem);

INSERT INTO member VALUES
('M01', 'Handoko', 'Bandung', '086234332659', '2022/04/28'),
('M02', 'Yudi', 'Jakarta', '089765672811', '2022/04/11'),
('M03', 'Syifa', 'Medan', '088654886564', '2022/06/06'),
('M04', 'Rahmawati', 'Binjai', '087323427625', '2022/03/07'),
('M05', 'Handoyo', 'Stabat', '089873282649', '2022/07/13');

INSERT INTO book VALUES
('61224', 'Akuntansi Pengantar 1', 170, 'Supardi', 'Gava Media'),
('91227', 'Kuasa Wanita Jawa', 120, 'Slamet Muljana', 'Andi Offset'),
('25311', 'Cedera Kepala', 74, 'Eko Priyo Utomo', 'Kencana'),
('71362', 'Kesehjateraan Sosial', 352, 'Lincon Arsyad', 'Sagung Seto'),
('91822', 'Lupa Pulang', 412, 'Gusti Eren', 'Mintore');

INSERT INTO book_loans(code, isbn, id_mem) VALUES
('B01', '61224', 'M01'),
('B02', '91227', 'M02'),
('B03', '25311', 'M03'),
('B04', '91822', 'M04'),
('B05', '25311', 'M05');


INSERT INTO librarian VALUES
('L01', 'Budi', 'Jakarta', '089788675432'),
('L02', 'Bayu', 'Bandung', '082132576455'),
('L03', 'Joko', 'Medan', '088775432877'),
('L04', 'Sinta', 'Binjai', '082367336800'),
('L05', 'Santi', 'Stabat', '082744218923');

INSERT INTO account VALUES
('A01', 'L01', 'budi@gmail.com', 'budi', '12345'),
('A02', 'L02', 'bayu@gmail.com', 'bayu', '12345'),
('A03', 'L03', 'joko@gmail.com', 'joko', '12345'),
('A04', 'L04', 'sinta@gmail.com', 'sinta', '12345'),
('A05', 'L05', 'sinti@gmail.com', 'santi', '12345');

UPDATE book_loans 
set due_date = '2022/01/19'
WHERE  code = "B04";

UPDATE book_loans 
set start_date = '2022/01/12'
WHERE  code = "B04";

SELECT * FROM book_loans b
lEFT JOIN member m
ON b.id_mem  = m.id_mem;

SELECT * FROM book_loans;

SELECT * FROM payment;

-- Mencari Code paling akhir
-- SELECT MAX(code) AS 'MAX' FROM book_loans; 

-- Menghitung keterlambatan hari
-- SELECT due_date - CURRENT_DATE() AS 'result' 
-- FROM book_loans WHERE code = 'B04';

-- Librarian Login
-- SELECT l.name, c.username, c.password FROM account c
-- JOIN librarian l ON c.id_lib = l.id_lib
-- WHERE username = 'Dolor01' AND password = '12345';

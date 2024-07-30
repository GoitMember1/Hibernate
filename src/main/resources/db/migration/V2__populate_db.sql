INSERT INTO client (name) VALUES
('Alice'),
('Bob'),
('Charlie'),
('David'),
('Eva'),
('Frank'),
('Grace'),
('Hannah'),
('Ivan'),
('Jack');

INSERT INTO planet (id, name) VALUES
('MARS', 'Mars'),
('VEN', 'Venus'),
('EARTH', 'Earth'),
('JUP', 'Jupiter'),
('SAT', 'Saturn');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id) VALUES
(1, 'EARTH', 'MARS'),
(2, 'EARTH', 'VEN'),
(3, 'MARS', 'EARTH'),
(4, 'VEN', 'EARTH'),
(5, 'JUP', 'SAT'),
(6, 'SAT', 'JUP'),
(7, 'MARS', 'VEN'),
(8, 'VEN', 'MARS'),
(9, 'EARTH', 'JUP'),
(10, 'JUP', 'EARTH');

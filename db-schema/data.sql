INSERT INTO Rooms(columns, rows) VALUES (6, 8), (8, 12), (10, 16);
INSERT INTO tickets(type, price) VALUES ('half-price', 10), ('student', 16), ('adult', 20);
INSERT INTO RESERVATIONS(row_number, column_number, expiration_date, reservation_group, screening_id, user_username, ticket_id)
VALUES (1,1,'15-11-2044', 1, 1, 'test', 1)
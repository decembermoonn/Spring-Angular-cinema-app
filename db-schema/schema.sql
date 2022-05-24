CREATE TABLE Movies
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(160),
    year INTEGER,
    image_url VARCHAR(300),
    crew VARCHAR(200),
	minutes_length INTEGER,
    im_db_rating NUMERIC(4,2),
    im_db_rating_count INTEGER
)

CREATE TABLE Rooms
(
	id SERIAL PRIMARY KEY,
	columns INTEGER,
	rows INTEGER
)

CREATE TABLE Screenings
(
    id SERIAL PRIMARY KEY,
    beginning TIMESTAMP WITHOUT TIME ZONE,
	movie_id SERIAL,
	room_id SERIAL,
	CONSTRAINT fk_movie
		FOREIGN KEY(movie_id)
			REFERENCES movies(id),
	CONSTRAINT fk_room
		FOREIGN KEY(room_id)
			REFERENCES rooms(id)
)

CREATE TABLE reservations (
    id SERIAL PRIMARY KEY,
    row_number SMALLINT NOT NULL,
    column_number SMALLINT NOT NULL,
    reservation_group INT NOT NULL,
    expiration_date TIMESTAMP WITHOUT TIME ZONE,
    screening_id SERIAL,
    user_id VARCHAR(50),
    ticket_id SERIAL,
    CONSTRAINT fk_screening FOREIGN KEY (screening_id) REFERENCES screenings(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(username),
    CONSTRAINT fk_ticket FOREIGN KEY (ticket_id) REFERENCES tickets(id)
);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    price NUMERIC(4,2) NOT NULL
);

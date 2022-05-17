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
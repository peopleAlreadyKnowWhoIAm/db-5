-- -------------------------------------------------------------------------------------------------------------

USE itunes;

-- Data section
-- Data for table label
INSERT INTO
  label (id, name)
VALUES
  (1, 'Columbia Records'),
  (2, 'L-M Records'),
  (3, 'Nice Life'),
  (4, 'EMI American Records'),
  (5, 'Republic Records'),
  (6, 'Parkwood Entertainment'),
  (7, 'Epic'),
  (8, 'Rimas Entertainment'),
  (9, 'Paramount Pictures'),
  (10, 'River House Artists');

-- Data for table album
INSERT INTO
  album (id, name, year_of_publishing, label_id)
VALUES
  (1, 'Harrys House', 2022, 1),
  (2, 'Gemini Rights', 2022, 2),
  (3, 'Special', 2022, 3),
  (4, 'Hounds of Love', 1985, 4),
  (5, 'Queen Radio: Volume 1', 2022, 5),
  (6, 'Twelve Carat Toothpaste', 2022, 5),
  (7, 'Renaissance', 2022, 6),
  (8, 'I Never Liked You', 2022, 7),
  (9, 'Un Verano Sin Ti', 2022, 8),
  (10, 'Top Gun: Maverick', 2022, 9),
  (11, 'Growin Up', 2022, 10);

-- Data for table genre
INSERT INTO
  genre (id, name)
VALUES
  (1, 'Hip-Hop'),
  (2, 'Pop rock'),
  (3, 'R&B'),
  (4, 'Reggaeton'),
  (5, 'Country'),
  (6, 'Rock');

-- Data for table song
INSERT INTO
  song (id, name, length, album_id, genre_id)
VALUES
  (1, 'As It Was', 166, 1, 2),
  (2, 'Bad Habit', 233, 2, 3),
  (3, 'About Damn Time', 191, 3, 1),
  (4, 'Running Up That Hill', 294, 4, 1),
  (5, 'Sunroof', 163, NULL, 1),
  (6, 'Hold Me Closer', 203, NULL, 1),
  (7, 'Super Freaky Girl', 173, 5, 3),
  (8, 'I Like You', 193, 6, 1),
  (9, 'Break My Soul', 240, 7, 1),
  (10, 'Wait For U', 190, 8, 3),
  (11, 'Me Porto Bonito', 179, 9, 4),
  (12, 'Late Night Talking', 178, 1, 2),
  (13, 'You Proof', 158, NULL, 5),
  (14, 'I Aint Worried', 147, 10, 2),
  (15, 'The Kind Of Love We Make', 225, 11, 5);

-- Data for table author
INSERT INTO
  author (id, name)
VALUES
  (1, 'Harry Styles'),
  (2, 'Steve Lacy'),
  (3, 'Lizzo'),
  (4, 'Kate Bush'),
  (5, 'Nicky Youre'),
  (6, 'dazy'),
  (7, 'Elton John'),
  (8, 'Britney Spears'),
  (9, 'Nicki Minaj'),
  (10, 'Post Malone'),
  (11, 'Doja Cat'),
  (12, 'Beyonce'),
  (13, 'Future'),
  (14, 'Tems'),
  (15, 'Bad Bunny'),
  (16, 'Chencho Corleone'),
  (17, 'Morgan Wallen'),
  (18, 'OneRepublic'),
  (19, 'Luke Combs'),
  (20, 'Drake');

-- Data for table related_genres
INSERT INTO
  related_genres (genre_id, related_genre_id)
VALUES
  (1, 4),
  (2, 1),
  (2, 6),
  (3, 1),
  (3, 6);

-- Data for table user
INSERT INTO
  user (id, name, email)
VALUES
  (1, 'Вілков Ігор', 'emeal1@gmailc.om'),
  (2, 'Завадка Богдан', 'emeal@gmailc.om'),
  (3, 'Шийка Остап', 'emeal3@gmailc.om'),
  (4, 'Гринишин Анна', 'emeal4@gmailc.om'),
  (5, 'Іжик Денис', 'emeal5@gmailc.om'),
  (6, 'Мальчик Володимир', 'emeal6@gmailc.om'),
  (7, 'Рущак Володимир', 'emeal7@gmailc.om'),
  (8, 'Ямінський Марко', 'emeal8@gmailc.om'),
  (9, 'Севастьянов Віталій', 'emeal9@gmailc.om'),
  (10, 'Мороченець Максим', 'emeal10@gmailc.om'),
  (11, 'Бондаренко	Ксенія', 'emeal11@gmailc.om'),
  (12, 'Мамедова Софія', 'emeal12@gmailc.om'),
  (13, 'Лукачович Павло', 'emeal13@gmailc.om'),
  (14, 'Соколов Микита', 'emeal14@gmailc.om'),
  (15, 'Пліш Олег', 'emeal15@gmailc.om');

-- Data for table user_playlist_info
INSERT INTO
  user_playlist_info (id, user_id, name)
VALUES
  (1, 2, 'Super muzlo'),
  (2, 5, 'Puper muzlo'),
  (3, 6, 'Super puper muzlo');

-- Data for table songs_saved_by_user
INSERT INTO
  songs_saved_by_user (song_id, user_id)
VALUES
  (1, 1),
  (4, 1),
  (8, 4),
  (1, 4),
  (4, 4),
  (5, 4),
  (2, 5),
  (1, 5),
  (4, 6),
  (2, 8),
  (5, 8),
  (1, 9),
  (2, 11),
  (9, 14),
  (5, 14),
  (1, 14);

-- Data for table albums_saved_by_user
INSERT INTO
  albums_saved_by_user (album_id, user_id)
VALUES
  (1, 1),
  (3, 2),
  (3, 3),
  (5, 4),
  (8, 5),
  (11, 6),
  (11, 7),
  (11, 8),
  (10, 9);

-- Data for table user_prefer_genre
INSERT INTO
  user_prefer_genre (user_id, genre_id)
VALUES
  (1, 1),
  (1, 2),
  (5, 3),
  (6, 2),
  (6, 1),
  (6, 3),
  (7, 5),
  (9, 2),
  (11, 5),
  (15, 5),
  (15, 2),
  (15, 6);

-- Data for table playlist_has_song
INSERT INTO
  playlist_has_song (user_playlist_info_id, song_id)
VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (1, 5),
  (2, 5),
  (2, 9),
  (2, 8),
  (2, 4),
  (2, 3),
  (3, 9),
  (3, 11),
  (3, 12),
  (3, 15);


-- Data for table user_credential
INSERT INTO
  user_credential (user_id, PASSWORD)
VALUES
  (1, 'password'),
  (2, 'password'),
  (3, 'password'),
  (4, 'password'),
  (5, 'password'),
  (6, 'password'),
  (7, 'password'),
  (8, 'password'),
  (9, 'password'),
  (10, 'password'),
  (11, 'password'),
  (12, 'password'),
  (13, 'password'),
  (14, 'password'),
  (15, 'password');

-- Data for table song_has_author
INSERT INTO
  song_has_author (song_id, author_id)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (5, 6),
  (6, 7),
  (6, 8),
  (7, 9),
  (8, 10),
  (8, 11),
  (9, 12),
  (10, 13),
  (10, 14),
  (11, 15),
  (11, 16),
  (12, 1),
  (13, 17),
  (14, 18),
  (15, 19),
  (10, 20);

-- Data for table album_has_author
INSERT INTO
  album_has_author (album_id, author_id)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 9),
  (6, 10),
  (7, 12),
  (8, 13),
  (9, 15),
  (11, 19);



DELIMITER //

DROP PROCEDURE IF EXISTS add_user_without_name//

CREATE PROCEDURE add_user_without_name(
    IN email VARCHAR(25)
) BEGIN
	DECLARE last int;
 INSERT INTO user(name, email) VALUES ("", email);
 SET last = LAST_INSERT_ID();
 UPDATE user set name = CONCAT("NONAME ", last) where id = last;
END //

ALTER TABLE related_genres
ADD name VARCHAR(64),
ADD related_name VARCHAR(64);//

DROP PROCEDURE IF EXISTS fill_related_table //

CREATE PROCEDURE fill_related_table()
BEGIN
    UPDATE related_genres as rg LEFT JOIN genre as g ON rg.genre_id = g.id
    SET rg.name = g.name;
    UPDATE related_genres as rg LEFT JOIN genre as g ON rg.related_genre_id = g.id
    SET rg.related_name = g.name;
END//

DROP PROCEDURE IF EXISTS fill_user_table//

CREATE PROCEDURE fill_user_table()
BEGIN
    DECLARE counter INT DEFAULT  0;
    SET @email_end = '@gmail.com';
    SET @noname_str = 'NONAME';
    WHILE counter < 10 DO
        SET counter = counter + 1;
        SET @name = CONCAT(@noname_str, counter);
        INSERT INTO user(name, email) VALUES (@name, CONCAT(@name, @email_end));
    END WHILE;
END //


DELIMITER ;
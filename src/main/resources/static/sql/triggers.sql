DELIMITER //

DROP TRIGGER IF EXISTS min_user_trigger//

CREATE TRIGGER min_user_trigger
BEFORE DELETE ON user FOR EACH ROW
BEGIN
    set @len = (SELECT COUNT(*) from user);
    IF @len > 6 THEN 
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'user length will become less than 6. ERROR';
    END IF;
END//

DROP TRIGGER IF EXISTS song_unchangable//

CREATE TRIGGER song_unchangable
BEFORE UPDATE ON song FOR EACH ROW
BEGIN 
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "The table couldn't be modified";
END//

DROP TRIGGER IF EXISTS check_names_ins//
DROP TRIGGER IF EXISTS check_names_upd//
DROP PROCEDURE IF EXISTS check_names//

CREATE PROCEDURE check_names(
    name VARCHAR(255)
) BEGIN
    IF name not in ('Svitlana', 'Petro', 'Olha', 'Taras') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "The name must be: 'Svitlana', 'Petro', 'Olha', 'Taras'";
    END IF;
END //

CREATE TRIGGER check_names_ins
BEFORE INSERT ON user FOR EACH ROW
BEGIN
    CALL check_names(new.name);
END//

CREATE TRIGGER check_names_upd
BEFORE UPDATE ON user FOR EACH ROW
BEGIN
    CALL check_names(new.name);
END//

DELIMITER ;


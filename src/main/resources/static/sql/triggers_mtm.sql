USE itunes;

CREATE TABLE IF NOT EXISTS badge (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    user_id INT NOT NULL
);

DELIMITER //
DROP TRIGGER IF EXISTS badge_check_user_id_on_insert //

CREATE TRIGGER badge_check_user_id_on_insert
BEFORE INSERT ON badge FOR EACH ROW
BEGIN 
    IF new.user_id not in (select id from user) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'user with inserting id not found. NOT INSERTED';
    end if;
end //

DROP TRIGGER IF EXISTS badge_check_user_id_on_update //

CREATE TRIGGER badge_check_user_id_on_update
BEFORE UPDATE ON badge FOR EACH ROW
BEGIN
    IF new.user_id not in (select id from user) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'user with updating id not found. NOT UPDATED';
    END IF;
END //

DROP TRIGGER IF EXISTS user_delete_cascade_badge//

CREATE TRIGGER user_delete_cascade_badge
AFTER DELETE ON user FOR EACH ROW
BEGIN
	DELETE FROM badge WHERE user_id = old.id;
END//

DROP TRIGGER IF EXISTS user_update_cascade_badge//

CREATE TRIGGER user_update_cascade_badge
AFTER UPDATE ON user FOR EACH ROW
BEGIN
	UPDATE badge SET user_id = new.id where user_id = old.id;
END //


DELIMITER ;

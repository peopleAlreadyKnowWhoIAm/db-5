DELIMITER //

USE itunes//

DROP FUNCTION IF EXISTS max_length_for_song//

CREATE FUNCTION max_length_for_song()
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE outVal INT;
    SELECT MAX(length) INTO outVal from song;
    RETURN outVal;
END //

DROP PROCEDURE IF EXISTS show_max_length_for_song//

CREATE PROCEDURE show_max_length_for_song(
    OUT length_str VARCHAR(255)
)
BEGIN
    DECLARE ml INT;
    SET ml = max_length_for_song();
    SET length_str = CONCAT("The length of the longest song:", CONCAT(FLOOR(ml/60),":",ml%60));
END //

DELIMITER ;

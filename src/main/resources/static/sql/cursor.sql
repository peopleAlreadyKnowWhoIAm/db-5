DELIMITER //

DROP PROCEDURE IF EXISTS create_many_tables//

CREATE PROCEDURE create_many_tables()
BEGIN
	DECLARE finished BOOL default false;

    DECLARE curn VARCHAR(255);
    
    DECLARE users CURSOR FOR SELECT name FROM user;

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = true;

    OPEN users;
    label: LOOP
        FETCH users INTO curn;
        
        IF finished then 
        LEAVE label; 
        END IF;
        
        SET curn = CONCAT(curn, ' ', NOW());
        SET @code = CONCAT('CREATE TABLE `', curn, '` (
            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            price DECIMAL(8, 2) NULL
        );');
        PREPARE als FROM @code;
        execute als;

    END LOOP;

END//

DELIMITER ;
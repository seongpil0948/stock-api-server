CREATE TABLE IF NOT EXISTS T_ADDRESS (
    addressId BINARY(16) NOT NULL,
    alias VARCHAR(100) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    detailLocate VARCHAR(255),
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    postalCode VARCHAR(20),
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100),
    county VARCHAR(100),
    town VARCHAR(100),
    PRIMARY KEY (id)
);

DELIMITER $$

CREATE PROCEDURE AddForeignKeyIfNotExists()
BEGIN
    DECLARE fk_exists INT DEFAULT 0;

    SELECT COUNT(*)
    INTO fk_exists
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
    WHERE CONSTRAINT_NAME = 'fk_defaultAddressID'
      AND TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'User';

    IF fk_exists = 0 THEN
        ALTER TABLE User
        ADD CONSTRAINT fk_defaultAddressID
        FOREIGN KEY (defaultAddressID) REFERENCES T_ADDRESS(id);
    END IF;
END $$

DELIMITER ;

CALL AddForeignKeyIfNotExists();
DROP PROCEDURE AddForeignKeyIfNotExists;
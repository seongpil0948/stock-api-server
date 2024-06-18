CREATE TABLE IF NOT EXISTS Address (
    id BINARY(16) NOT NULL,
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
-- 

CREATE PROCEDURE AddForeignKeyIfNotExists()
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.REFERENTIAL_CONSTRAINTS
        WHERE CONSTRAINT_NAME = 'fk_defaultAddressID'
          AND CONSTRAINT_SCHEMA = DATABASE()
    ) THEN
        ALTER TABLE User
        ADD CONSTRAINT fk_defaultAddressID
        FOREIGN KEY (defaultAddressID) REFERENCES Address(id);
    END IF;
END //

DELIMITER ;

CALL AddForeignKeyIfNotExists();
DROP PROCEDURE AddForeignKeyIfNotExists;
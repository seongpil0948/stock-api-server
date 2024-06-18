CREATE TABLE IF NOT EXISTS User (
    id VARCHAR(50) NOT NULL,
    userName VARCHAR(100),
    displayName VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    emailVerified BOOLEAN NOT NULL,
    phone VARCHAR(20),
    defaultAddressID BINARY(16),
    prefersNotifications BOOLEAN NOT NULL DEFAULT TRUE,
    seasonalPhoto ENUM('🌷', '🌞', '🍂', '☃️') NOT NULL,
    avatar VARCHAR(255),
    isFavorite BOOLEAN NOT NULL DEFAULT FALSE,
    membership ENUM('0', '1', '2', '3') NOT NULL DEFAULT '0',
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
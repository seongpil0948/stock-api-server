CREATE TABLE IF NOT EXISTS Role (
    roleId VARCHAR(50) NOT NULL,
    useYn BOOLEAN NOT NULL,
    roleNm VARCHAR(100) NOT NULL,
    roleDscr TEXT,
    loginId VARCHAR(50),
    PRIMARY KEY (roleId)
);
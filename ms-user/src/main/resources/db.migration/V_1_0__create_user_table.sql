CREATE TABLE TB_USERS (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL unique,
    password VARCHAR(255) NOT NULL,
    registration INTEGER NOT NULL unique
);
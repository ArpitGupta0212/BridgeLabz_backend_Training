-- ==========================================
-- DATABASE
-- ==========================================

--CREATE DATABASE greeting_db;

-- ==========================================
-- Connect to greeting_db
-- ==========================================

-- psql:
-- \c greeting_db

-- ==========================================
-- USERS TABLE
-- ==========================================

CREATE TABLE users (

                       id SERIAL PRIMARY KEY,

                       username VARCHAR(50) UNIQUE NOT NULL,

                       password VARCHAR(255) NOT NULL,

                       role VARCHAR(20) NOT NULL,

                       email VARCHAR(100),

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

-- ==========================================
-- GREETINGS TABLE
-- ==========================================

CREATE TABLE greetings (

                           id SERIAL PRIMARY KEY,

                           message TEXT NOT NULL,

                           image_path VARCHAR(255),

                           created_by INT,

                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                           CONSTRAINT fk_user
                               FOREIGN KEY(created_by)
                                   REFERENCES users(id)
                                   ON DELETE CASCADE

);

-- ==========================================
-- DEFAULT ADMIN
-- password = admin123
-- ==========================================

INSERT INTO users
(
    username,
    password,
    role,
    email
)
VALUES
    (
        'admin',
        '240be518fabd2724ddb6f04eebffb9746b8d7c4b9e5c3a3b6a4f4e5d6c1f6f70',
        'ADMIN',
        'admin@gmail.com'
    );

-- ==========================================
-- DEFAULT USER
-- password = user123
-- ==========================================

INSERT INTO users
(
    username,
    password,
    role,
    email
)
VALUES
    (
        'user',
        '0a041b9462caa4a31bac3567e0b6e6fd9100787d6d86b318a5e9d6fbb7b8b0f2',
        'USER',
        'user@gmail.com'
    );

-- ==========================================
-- SAMPLE GREETINGS
-- ==========================================

INSERT INTO greetings
(
    message,
    image_path,
    created_by
)
VALUES
    (
        'Welcome to Greeting App',
        'images/welcome.jpg',
        1
    );

INSERT INTO greetings
(
    message,
    image_path,
    created_by
)
VALUES
    (
        'Have a Nice Day',
        'images/day.jpg',
        1
    );

INSERT INTO greetings
(
    message,
    image_path,
    created_by
)
VALUES
    (
        'Happy Coding',
        'images/code.jpg',
        2
    );


UPDATE greetings
SET image_path='images/welcome.png'
WHERE id=1;

UPDATE greetings
SET image_path='images/day.png'
WHERE id=2;

UPDATE greetings
SET image_path='images/code.png'
WHERE id=3;

SELECT id, message, image_path
FROM greetings
ORDER BY id DESC;
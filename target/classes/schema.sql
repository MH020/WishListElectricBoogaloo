CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL
);

CREATE TABLE wishlist (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          item_name VARCHAR(100) NOT NULL,
                          CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

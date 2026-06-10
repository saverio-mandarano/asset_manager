-- Disable foreign key checks so imported rows can be inserted in any order
SET FOREIGN_KEY_CHECKS=0;

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO users (username, password) VALUES ('ADMIN', 'admin');
INSERT INTO users (username, password) VALUES ('USER', 'user');

INSERT INTO role_user (user_id, role_id) VALUES (1, 1);
INSERT INTO role_user (user_id, role_id) VALUES (2, 2);

INSERT INTO categories (name) VALUES ('Crypto');
INSERT INTO categories (name) VALUES ('Bond');
INSERT INTO categories (name) VALUES ('Commodity');
INSERT INTO categories (name) VALUES ('Stock');

INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Apple Inc.', 'AAPL', 228.50, 'Big tech hardware e servizi digitali.', '/images/apple.png', 4);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Microsoft Corporation', 'MSFT', 415.30, 'Software enterprise e cloud computing.', '/images/microsoft.png', 4);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Nvidia Corporation', 'NVDA', 875.28, 'GPU e infrastrutture AI.', '/images/nvidia.png', 4);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Google / Alphabet Inc.', 'GOOGL', 140.80, 'Search, advertising e AI.', '/images/google.png', 4);

INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Bitcoin', 'BTC', 65000, 'Criptovaluta principale decentralizzata.', '/images/bitcoin.png', 1);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Ethereum', 'ETH', 3500, 'Blockchain per smart contract e DeFi.', '/images/ethereum.png', 1);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Dogecoin', 'DOGE', 0.15, 'Criptovaluta altamente speculativa.', '/images/dogecoin.png', 1);

INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Gold', 'XAU', 2350, 'Metallo prezioso rifugio sicuro.', '/images/gold.png', 3);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Silver', 'XAG', 28.50, 'Metallo industriale e prezioso.', '/images/silver.png', 3);

INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('US Treasury Bond', 'UST10Y', 100, 'Titolo di stato USA a lungo termine.', '/images/us_bond.png', 2);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('German Government Bond', 'DE10Y', 98, 'Bund tedesco considerato molto sicuro.', '/images/german_bond.png', 2);
INSERT INTO assets (name, ticker, last_price, description, image_url, category_id) VALUES ('Italian Government Bond', 'IT10Y', 95, 'BTP italiano con rischio medio.', '/images/italian_bond.png', 2);

INSERT INTO tags (name) VALUES ('Speculative');
INSERT INTO tags (name) VALUES ('Fast-Growing');
INSERT INTO tags (name) VALUES ('Dividend-Paying');
INSERT INTO tags (name) VALUES ('Tech');
INSERT INTO tags (name) VALUES ('Stable');
INSERT INTO tags (name) VALUES ('Store-of-Value');

INSERT INTO asset_tag VALUES (1, 4);
INSERT INTO asset_tag VALUES (1, 2);
INSERT INTO asset_tag VALUES (2, 4);
INSERT INTO asset_tag VALUES (2, 3);
INSERT INTO asset_tag VALUES (3, 4);
INSERT INTO asset_tag VALUES (3, 2);
INSERT INTO asset_tag VALUES (4, 4);
INSERT INTO asset_tag VALUES (4, 2);
INSERT INTO asset_tag VALUES (5, 6);
INSERT INTO asset_tag VALUES (5, 1);
INSERT INTO asset_tag VALUES (6, 6);
INSERT INTO asset_tag VALUES (6, 1);
INSERT INTO asset_tag VALUES (7, 1);
INSERT INTO asset_tag VALUES (7, 2);
INSERT INTO asset_tag VALUES (8, 6);
INSERT INTO asset_tag VALUES (8, 5);
INSERT INTO asset_tag VALUES (9, 6);
INSERT INTO asset_tag VALUES (9, 5);
INSERT INTO asset_tag VALUES (10, 5);
INSERT INTO asset_tag VALUES (10, 6);
INSERT INTO asset_tag VALUES (11, 5);
INSERT INTO asset_tag VALUES (11, 6);
INSERT INTO asset_tag VALUES (12, 5);
INSERT INTO asset_tag VALUES (12, 6);

SET FOREIGN_KEY_CHECKS=1;
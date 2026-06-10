-- Roles
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

-- Users (password: admin e user)
INSERT INTO users (username, password) VALUES ('ADMIN', 'admin');
INSERT INTO users (username, password) VALUES ('USER', 'user');

-- Assegno ruoli agli utenti
INSERT INTO role_user (user_id, role_id) VALUES (1, 1);
INSERT INTO role_user (user_id, role_id) VALUES (2, 2);

-- Categorie
INSERT INTO categories (name) VALUES ('Crypto');
INSERT INTO categories (name) VALUES ('Bond');
INSERT INTO categories (name) VALUES ('Commodity');
INSERT INTO categories (name) VALUES ('Stock');

-- =========================
-- ASSET
-- =========================

-- STOCK (solo big tech)
INSERT INTO assets (name, ticker, lastPrice, description, imageUrl, category_id) VALUES
('Apple Inc.', 'AAPL', 228.50, 'Big tech hardware e servizi digitali.', '/images/apple.png', 4),
('Microsoft Corporation', 'MSFT', 415.30, 'Software enterprise e cloud computing.', '/images/microsoft.png', 4),
('Nvidia Corporation', 'NVDA', 875.28, 'GPU e infrastrutture AI.', '/images/nvidia.png', 4),
('Google / Alphabet Inc.', 'GOOGL', 140.80, 'Search, advertising e AI.', '/images/google.png', 4);

-- CRYPTO
INSERT INTO assets (name, ticker, lastPrice, description, imageUrl, category_id) VALUES
('Bitcoin', 'BTC', 65000, 'Criptovaluta principale decentralizzata.', '/images/bitcoin.png', 1),
('Ethereum', 'ETH', 3500, 'Blockchain per smart contract e DeFi.', '/images/ethereum.png', 1),
('Dogecoin', 'DOGE', 0.15, 'Criptovaluta altamente speculativa.', '/images/dogecoin.png', 1);

-- COMMODITY
INSERT INTO assets (name, ticker, lastPrice, description, imageUrl, category_id) VALUES
('Gold', 'XAU', 2350, 'Metallo prezioso rifugio sicuro.', '/images/gold.png', 3),
('Silver', 'XAG', 28.50, 'Metallo industriale e prezioso.', '/images/silver.png', 3);

-- BOND
INSERT INTO assets (name, ticker, lastPrice, description, imageUrl, category_id) VALUES
('US Treasury Bond', 'UST10Y', 100, 'Titolo di stato USA a lungo termine.', '/images/us_bond.png', 2),
('German Government Bond', 'DE10Y', 98, 'Bund tedesco considerato molto sicuro.', '/images/german_bond.png', 2),
('Italian Government Bond', 'IT10Y', 95, 'BTP italiano con rischio medio.', '/images/italian_bond.png', 2);

-- =========================
-- TAGS (ridotti e normalizzati)
-- =========================

INSERT INTO tags (name) VALUES
('Speculative'),      -- alto rischio, forte volatilità
('Fast-Growing'),     -- crescita rapida attesa
('Dividend-Paying'),  -- distribuisce dividendi
('Tech'),             -- settore tecnologico
('Stable'),           -- bassa volatilità, difensivo
('Store-of-Value');   -- preservazione valore (oro, crypto come BTC)

-- =========================
-- ASSET_TAG (min 2 tag per asset)
-- =========================

-- STOCK
INSERT INTO asset_tag VALUES (1, 4), (1, 2); -- Apple: Tech, Fast-Growing
INSERT INTO asset_tag VALUES (2, 4), (2, 3); -- Microsoft: Tech, Dividend-Paying
INSERT INTO asset_tag VALUES (3, 4), (3, 2); -- Nvidia: Tech, Fast-Growing
INSERT INTO asset_tag VALUES (4, 4), (4, 2); -- Google: Tech, Fast-Growing

-- CRYPTO
INSERT INTO asset_tag VALUES (5, 6), (5, 1); -- Bitcoin: Store-of-Value, Speculative
INSERT INTO asset_tag VALUES (6, 6), (6, 1); -- Ethereum: Store-of-Value, Speculative
INSERT INTO asset_tag VALUES (7, 1), (7, 2); -- Dogecoin: Speculative, Fast-Growing

-- COMMODITY
INSERT INTO asset_tag VALUES (8, 6), (8, 5); -- Gold: Store-of-Value, Stable
INSERT INTO asset_tag VALUES (9, 6), (9, 5); -- Silver: Store-of-Value, Stable

-- BOND
INSERT INTO asset_tag VALUES (10, 5), (10, 6); -- US Bond: Stable, Store-of-Value
INSERT INTO asset_tag VALUES (11, 5), (11, 6); -- German Bond: Stable, Store-of-Value
INSERT INTO asset_tag VALUES (12, 5), (12, 6); -- Italian Bond: Stable, Store-of-Value
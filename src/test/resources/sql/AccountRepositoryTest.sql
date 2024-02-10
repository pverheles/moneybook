INSERT INTO app_user (id, create_date, modify_date, "version", "name", "comment", email, state)
VALUES(1, now(), now(), 1, 'Petro Verheles', 'haha', 'pet@mail.com', 'A');

INSERT INTO account (id, amount, create_date, modify_date, user_id, "version", "name", "comment", bank, currency, state)
VALUES(34, 0, now(), now(), 1, 1, 'My bank acc', 'comm', 'MONOBANK', 'USD', 'A');
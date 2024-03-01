INSERT INTO app_user (id, create_date, modify_date, "version", "name", "comment", email, state)
VALUES(1, now(), now(), 1, 'Petro Verheles', 'haha', 'pet@mail.com', 'A');

INSERT INTO account (id, amount, create_date, modify_date, user_id, "version", "name", "comment", bank, currency, state)
VALUES(1, 0, now(), now(), 1, 1, 'My bank acc', 'comm', 'MONOBANK', 'USD', 'A');

INSERT INTO category (id, create_date, modify_date, user_id, "version", "name", "comment", state)
VALUES (1, now(), now(), 1, 0, 'Products', '', 'A');

INSERT INTO operation
(id, amount, account_id, category_id, create_date, modify_date, envelope_id, expense_plan_id, user_id, "version", "name", "comment", state, operation_datetime)
VALUES (1, 10, 1, 1, now(), now(), NULL, NULL, 1, 0, '', 'SILPO', 'A', '2023-10-19 10:23:54');

INSERT INTO operation
(id, amount, account_id, category_id, create_date, modify_date, envelope_id, expense_plan_id, user_id, "version", "name", "comment", state, operation_datetime)
VALUES (2, 10, 1, 1, now(), now(), NULL, NULL, 1, 0, '', 'SILPO', 'A', '2023-11-19 10:23:54');
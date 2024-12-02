INSERT INTO clients (id, client_name)
VALUES
    (1, 'Ivanov'),
    (2, 'Petrov'),
    (3, 'Sidorov');

INSERT INTO contacts (contact_id, contact_type, client_id, text)
VALUES
    (7, 'phone', 1, '+7123456789'),
    (8, 'email', 2, '123@mail.ru'),
    (9, 'phone', 3, '+7123456598');
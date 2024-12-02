CREATE TABLE IF NOT EXISTS clients (
    id              BIGSERIAL PRIMARY KEY NOT NULL,
    client_name     VARCHAR (100) NOT NULL
    );

COMMENT ON TABLE clients IS 'Таблица клиентов';
COMMENT ON COLUMN clients.id IS 'id клиента';
COMMENT ON COLUMN clients.client_name IS 'Имя клиента';

CREATE TABLE IF NOT EXISTS contacts (
    contact_id      BIGSERIAL PRIMARY KEY NOT NULL,
    contact_type    VARCHAR (10) NOT NULL,
    client_id       bigint NOT NULL,
    text            VARCHAR (20) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
    );

COMMENT ON TABLE contacts IS 'Таблица контактов';
COMMENT ON COLUMN contacts.contact_id IS 'id контакта';
COMMENT ON COLUMN contacts.contact_type IS 'Тип контакта';
COMMENT ON COLUMN contacts.client_id IS 'Ссылка на клиента';

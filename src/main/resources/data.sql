-- Lösche alte Daten und setze die Sequenzen zurück
TRUNCATE TABLE users_authority, user_roles, users, authority, role, rank RESTART IDENTITY CASCADE;

-- Ränge einfügen
INSERT INTO rank (id, name)
VALUES (gen_random_uuid(), 'Silver')
ON CONFLICT (name) DO NOTHING;

INSERT INTO rank (id, name)
VALUES (gen_random_uuid(), 'Gold')
ON CONFLICT (name) DO NOTHING;

INSERT INTO rank (id, name)
VALUES (gen_random_uuid(), 'Platinum')
ON CONFLICT (name) DO NOTHING;

-- Rollen einfügen
INSERT INTO role (id, name)
VALUES (gen_random_uuid(), 'CLIENT')
ON CONFLICT (name) DO NOTHING;

-- Autoritäten einfügen mit festgelegten UUIDs
INSERT INTO authority (id, name)
VALUES ('008a48b0-9f3b-453e-9fff-d7efc58be1d7', 'CAN_PLACE_ORDER')
ON CONFLICT (name) DO NOTHING;

INSERT INTO authority (id, name)
VALUES ('31b8e52a-ed1d-4f80-971d-5306e0c6e0df', 'CAN_RETRIEVE_PURCHASE_HISTORY')
ON CONFLICT (name) DO NOTHING;

INSERT INTO authority (id, name)
VALUES ('632ef0e4-9244-49b0-8791-f7a51f81719c', 'CAN_RETRIEVE_PRODUCTS')
ON CONFLICT (name) DO NOTHING;

-- Beispielkunde (z.B. Max Mustermann) einfügen
INSERT INTO users (id, first_name, last_name, email, username, password, rank_id)
VALUES (gen_random_uuid(), 'Max', 'Mustermann', 'max.mustermann@example.com', 'maxmustermann', '<hashed_password>',
        (SELECT id FROM rank WHERE name = 'Silver'))
ON CONFLICT (email) DO NOTHING;

-- Zuweisung von Rollen für Max Mustermann
INSERT INTO user_roles (users_id, role_id)
SELECT u.id, r.id FROM users u, role r
WHERE u.username = 'maxmustermann' AND r.name = 'CLIENT'
ON CONFLICT DO NOTHING;

-- Zuweisung von Autoritäten für Max Mustermann
INSERT INTO users_authority (users_id, authority_id)
SELECT u.id, a.id FROM users u, authority a
WHERE u.username = 'maxmustermann' AND a.id IN ('008a48b0-9f3b-453e-9fff-d7efc58be1d7', '31b8e52a-ed1d-4f80-971d-5306e0c6e0df', '632ef0e4-9244-49b0-8791-f7a51f81719c')
ON CONFLICT DO NOTHING;

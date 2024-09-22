-- Installiere die pgcrypto Erweiterung für UUID-Generierung
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Tabelle für Ränge
CREATE TABLE IF NOT EXISTS rank (
                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                    name VARCHAR(50) UNIQUE NOT NULL
);

-- Tabelle für Rollen
CREATE TABLE IF NOT EXISTS role (
                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                    name VARCHAR(50) UNIQUE NOT NULL
);

-- Tabelle für Autoritäten
CREATE TABLE IF NOT EXISTS authority (
                                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                         name VARCHAR(50) UNIQUE NOT NULL
);

-- Tabelle für Benutzer
CREATE TABLE IF NOT EXISTS users (
                                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                     first_name VARCHAR(100) NOT NULL,
                                     last_name VARCHAR(100) NOT NULL,
                                     email VARCHAR(100) UNIQUE NOT NULL,
                                     username VARCHAR(100) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     rank_id UUID REFERENCES rank(id)
);

-- Join-Tabelle für Benutzer und Rollen
CREATE TABLE IF NOT EXISTS user_roles (
                                          users_id UUID REFERENCES users(id),
                                          role_id UUID REFERENCES role(id),
                                          PRIMARY KEY (users_id, role_id)
);

-- Join-Tabelle für Rollen und Autoritäten
CREATE TABLE IF NOT EXISTS role_authorities (
                                                role_id UUID REFERENCES role(id),
                                                authority_id UUID REFERENCES authority(id),
                                                PRIMARY KEY (role_id, authority_id)
);

-- Join-Tabelle für Benutzer und Autoritäten
CREATE TABLE IF NOT EXISTS users_authority (
                                               users_id UUID REFERENCES users(id),
                                               authority_id UUID REFERENCES authority(id),
                                               PRIMARY KEY (users_id, authority_id)
);

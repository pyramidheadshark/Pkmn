CREATE TABLE student (
    "id" UUID PRIMARY KEY NOT NULL,
    familyName TEXT NOT NULL,
    firstName TEXT NOT NULL,
    patronicName TEXT,
    "group" TEXT NOT NULL
);

CREATE TABLE card (
    "id" UUID PRIMARY KEY NOT NULL,
    "name" TEXT NOT NULL,
    hp INT2,
    evolves_from UUID REFERENCES card(id),
    game_set TEXT,
    card_number INT2 NOT NULL,
    pokemon_owner UUID REFERENCES student(id),
    stage TEXT NOT NULL,
    retreat_cost TEXT,
    weakness_type TEXT,
    resistance_type TEXT,
    attack_skills JSON NOT NULL,
    pokemon_type TEXT NOT NULL,
    regulation_mark CHAR(1) NOT NULL
);
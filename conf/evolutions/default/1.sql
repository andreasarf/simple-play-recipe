--- !Ups

DROP TABLE IF EXISTS recipes;

CREATE TABLE IF NOT EXISTS recipes (
    `id` integer PRIMARY KEY AUTO_INCREMENT,
    -- name of recipe
    `title` varchar(100) NOT NULL,
    -- time required to cook/bake the recipe
    `making_time` varchar(100) NOT NULL,
    -- number of people the recipe will feed
    `serves` varchar(100) NOT NULL,
    -- food items necessary to prepare the recipe
    `ingredients` varchar(300) NOT NULL,
    -- price of recipe
    `cost` integer NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

--- !Downs
DROP TABLE IF EXISTS recipes;

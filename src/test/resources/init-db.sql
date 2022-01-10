CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login varchar,
    password varchar,
    firstname varchar,
    lastname varchar,
    created_at timestamp,
    locked boolean,
    email varchar,
    role varchar,
    active boolean,
    action_code varchar

);




CREATE TABLE "plane" (
                          "id" SERIAL PRIMARY KEY,
                          "name" varchar,
                          "model" varchar,
                          "passenger_seats" int,
                          "cost_1km" decimal(15,2)
);

CREATE TABLE "flight" (
                          "id" SERIAL PRIMARY KEY,
                          "start_date" timestamp,
                          "end_date" timestamp,
                          "km" int,
                          "airport_start_id" int,
                          "airport_end_id" int,
                          "plane_id" int,
                          "cost"  decimal(15,2)
);

CREATE TABLE "airport" (
                           "id" SERIAL PRIMARY KEY,
                           "name" varchar,
                           "code" varchar,
                           "tax" decimal(15,2),
                           "city_id" int
);

CREATE TABLE "city" (
                        "id" SERIAL PRIMARY KEY,
                        "name" varchar
);

CREATE TABLE "ticket" (
                         "id" SERIAL PRIMARY KEY,
                         "created_at" timestamp,
                         "baggage" boolean,
                         "priority" boolean,
                         "flight_id" int,
                         "user_id" int
);

ALTER TABLE "flight" ADD FOREIGN KEY ("airport_start_id") REFERENCES "airport" ("id");

ALTER TABLE "flight" ADD FOREIGN KEY ("airport_end_id") REFERENCES "airport" ("id");

ALTER TABLE "flight" ADD FOREIGN KEY ("plane_id") REFERENCES "plane" ("id");

ALTER TABLE "airport" ADD FOREIGN KEY ("city_id") REFERENCES "city" ("id");

ALTER TABLE "ticket" ADD FOREIGN KEY ("flight_id") REFERENCES "flight" ("id");

ALTER TABLE "ticket" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

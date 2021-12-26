CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "login" varchar,
  "password" varchar,
  "firstname" varchar,
  "lastname" varchar,
  "created_at" timestamp,
  "locked" boolean,
  "email" varchar,
  "role" varchar
);

CREATE TABLE "plaine" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "model" varchar,
  "passenger_seats" int,
  "cost_1km" decimal(15,2)
);

CREATE TABLE "flight" (
  "id" SERIAL PRIMARY KEY,
  "startdate" timestamp,
  "enddate" timestamp,
  "km" int,
  "airport_start_id" int,
  "airport_end_id" int,
  "plaine_id" int
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

CREATE TABLE "ticet" (
  "id" SERIAL PRIMARY KEY,
  "created_at" timestamp,
  "baggage" boolean,
  "priority" boolean,
  "flight_id" int,
  "user_id" int
);

ALTER TABLE "flight" ADD FOREIGN KEY ("airport_start_id") REFERENCES "airport" ("id");

ALTER TABLE "flight" ADD FOREIGN KEY ("airport_end_id") REFERENCES "airport" ("id");

ALTER TABLE "flight" ADD FOREIGN KEY ("plaine_id") REFERENCES "plaine" ("id");

ALTER TABLE "airport" ADD FOREIGN KEY ("city_id") REFERENCES "city" ("id");

ALTER TABLE "ticet" ADD FOREIGN KEY ("flight_id") REFERENCES "flight" ("id");

ALTER TABLE "ticet" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

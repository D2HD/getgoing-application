-- Reset everything in the database.
-- Note: Terminate the process 'GetGoingApplication'.
DROP TABLE Tweet;
DROP DATABASE d2hd;

-- TODO: Access control for database.
-- CREATE USER d2hd_admin WITH PASSWORD 'd2hd' VALID UNTIL '2023-12-31';
-- CREATE DATABASE d2hd WITH OWNER d2hd_admin;

CREATE DATABASE d2hd WITH OWNER postgres;
CREATE TABLE Tweet (
                       url VARCHAR PRIMARY KEY NOT NULL,
                       main_content VARCHAR NOT NULL,
                       profile_name VARCHAR NOT NULL,
                       tweet_endpoint VARCHAR NOT NULL,
                       likes INTEGER,
                       retweets INTEGER,
                       quote_tweets INTEGER,
                       timestamp DATE,
                       tweet_json VARCHAR UNIQUE
);

-- For convenience, no need to change to the user "postgres"
-- Replace "user" with your windows/macos/linux username.
create user "user" superuser createdb replication;
create database "user" with owner "user";
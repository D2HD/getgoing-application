-- Reset everything in the database.
-- Note: Terminate the process 'GetGoingApplication'.
DROP TABLE tweet;
DROP DATABASE d2hd;

CREATE DATABASE d2hd WITH OWNER postgres;
CREATE TABLE tweet (
                       value1 varchar primary key not null,
                       value2 varchar not null,
                       value3 varchar not null,
                       value4 varchar not null,
                       value5 varchar not null,
                       value6 varchar not null,
                       value7 varchar not null,
                       value8 varchar not null,
                       value9 varchar not null,
                       value10 varchar not null,
                       value11 varchar not null,
                       value12 varchar not null,
                       value13 varchar not null,
                       value14 varchar not null,
                       value15 varchar not null
);

-- For convenience, no need to change to the user "postgres"
-- Replace "user" with your windows/macos/linux username.
create user "user" superuser createdb replication;
create database "user" with owner "user";
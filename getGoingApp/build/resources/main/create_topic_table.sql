-- Reset everything in the database.
-- Note: Terminate the process 'GetGoingApplication'.


CREATE DATABASE d2hd WITH OWNER postgres;
CREATE TABLE topic (
    topic_id varchar primary key not null,
    topic_name varchar primary key not null,
    num_of_occurrence integer primary key not null,
    timestamp timestamp primary key not null,
    like_count integer primary key not null,
    retweet_count integer primary key not null,
    quote_tweet_count integer primary key not null,
    general_sentiment decimal primary key not null
);

-- For convenience, no need to change to the user "postgres"
-- Replace "user" with your windows/macos/linux username.
create user "user" superuser createdb replication;
create database "user" with owner "user";
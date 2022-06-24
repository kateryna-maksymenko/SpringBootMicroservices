drop database coordinatetrackerdb;
drop user coordinatetracker;
create user coordinatetracker with password 'password';
create database coordinatetrackerdb with template=template0 owner=coordinatetracker;
\connect coordinatetrackerdb;
grant all privileges on database coordinatetrackerdb to coordinatetracker;

alter default privileges grant all on tables to coordinatetracker;

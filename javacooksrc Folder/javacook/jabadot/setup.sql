-- The initial setup for jabadot
-- $Id: setup.sql,v 1.3 2002/03/02 00:51:37 ian Exp $

-- Database creation is normally done with an admin tool...
-- create database jabadot;

\echo Creating user table
drop table users;
create table users (
	name		char(12) PRIMARY KEY,
	password	char(20),
	firstName 	varchar(40),
	lastName	varchar(40),
	email		varchar(60),
	city		varchar(20),
	prov		varchar(20),
	country	varchar(20),
	credt		date,
	lastLog		date,
	skin		varchar(20),
	editpriv	boolean,
	adminpriv	boolean
);

-- The creation of the admin account is COMMENTED OUT
-- Before you uncomment this you MUST change the password, email, etc.
-- Anybody running a JabaDot site with 'CHANGE THIS' as the password,
-- will surely die a horrible death Real Soon Now.

-- insert into userdb values(
-- 	'admin', 'CHANGE THIS', 'JabaDot Admin', 'admin@jaba.dot',
--	'CITY', 'STATE', 'COUNTRY', 
--	null, null, "jabadot_green",
--  't', 't'
-- )

\echo Creating submissions table
create table submissions (
	id		serial PRIMARY KEY,
	submittor	char(12),	-- fkey name in userdb
	subdate		datetime,
	subject		char(50),
	subsref		char(80),
	substance	text
);

\echo Creating Categories table
create table categories (
	n			int PRIMARY KEY,
	cat			char(15),
	fromthe		char(20)
);
insert into categories values (
	1, 'Java Language',		'Back to Basics');
insert into categories values (
	2, 'Core API',			'Whatshamecallit');
insert into categories values (
	3, 'Strings',			'Strings and Things');
insert into categories values (
	4, 'Dates/Times',		'Who/what/when');
insert into categories values (
	5, 'Numbers',			'How shall I count thee?');
insert into categories values (
	6, 'Servlets',			'Jeeves was here');
insert into categories values (
	7, 'Java Server Pages', 'Let''s get rid of ASP');

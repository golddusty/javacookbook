--
-- Selected TOC Entries:
--
\connect - ian
--
-- TOC Entry ID 2 (OID 31255)
--
-- Name: submissions_id_seq Type: SEQUENCE Owner: ian
--

CREATE SEQUENCE "submissions_id_seq" start 1 increment 1 maxvalue 2147483647 minvalue 1  cache 1 ;

--
-- TOC Entry ID 4 (OID 31274)
--
-- Name: submissions Type: TABLE Owner: ian
--

CREATE TABLE "submissions" (
	"id" integer DEFAULT nextval('"submissions_id_seq"'::text) NOT NULL,
	"submittor" character(12),
	"subdate" timestamp with time zone,
	"subject" character(50),
	"subsref" character(80),
	"substance" text,
	Constraint "submissions_pkey" Primary Key ("id")
);

--
-- TOC Entry ID 5 (OID 31308)
--
-- Name: categories Type: TABLE Owner: ian
--

CREATE TABLE "categories" (
	"n" integer NOT NULL,
	"cat" character(15),
	"fromthe" character(20),
	Constraint "categories_pkey" Primary Key ("n")
);

--
-- TOC Entry ID 6 (OID 31323)
--
-- Name: users Type: TABLE Owner: ian
--

CREATE TABLE "users" (
	"name" character(12) NOT NULL,
	"password" character(20),
	"firstname" character varying(30),
	"lastname" character varying(30),
	"email" character varying(60),
	"city" character varying(20),
	"prov" character varying(20),
	"country" character varying(20),
	"creation" date,
	"lastlogin" date,
	"editpriv" boolean,
	"adminpriv" boolean,
	Constraint "users_pkey" Primary Key ("name")
);

--
-- Data for TOC Entry ID 7 (OID 31274)
--
-- Name: submissions Type: TABLE DATA Owner: ian
--


--
-- Data for TOC Entry ID 8 (OID 31308)
--
-- Name: categories Type: TABLE DATA Owner: ian
--


INSERT INTO "categories" VALUES (1,'Java Language  ','Back to Basics      ');
INSERT INTO "categories" VALUES (2,'Core API       ','Whatshamecallit     ');
INSERT INTO "categories" VALUES (3,'Strings        ','Strings and Things  ');
INSERT INTO "categories" VALUES (4,'Dates/Times    ','Who/what/when       ');
INSERT INTO "categories" VALUES (5,'Numbers        ','How shall I count th');
INSERT INTO "categories" VALUES (6,'Servlets       ','Jeeves was here     ');
INSERT INTO "categories" VALUES (7,'Java Server Pag','Let''s get rid of ASP');
--
-- Data for TOC Entry ID 9 (OID 31323)
--
-- Name: users Type: TABLE DATA Owner: ian
--


INSERT INTO "users" VALUES ('iadmin      ','fredonia            ','Ian','Darwin','ian@darwinsys.com','Toronto','Ontario','Canada',NULL,NULL,'t','t');
--
-- TOC Entry ID 3 (OID 31255)
--
-- Name: submissions_id_seq Type: SEQUENCE SET Owner: 
--

SELECT setval ('"submissions_id_seq"', 1, 'f');


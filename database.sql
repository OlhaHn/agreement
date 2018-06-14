drop table if exists agreement cascade;
drop table if exists system cascade;
drop table if exists users cascade;
DROP SEQUENCE IF exists agreement_id_seq;
DROP SEQUENCE IF exists system_id_seq;
DROP SEQUENCE IF exists users_id_seq;


-- ID serial numbers for 'agreement' table
CREATE SEQUENCE agreement_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE agreement_id_seq
  OWNER TO postgres;

-- ID serial numbers for 'system' table
CREATE SEQUENCE system_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.system_id_seq
  OWNER TO postgres;

  -- ID serial numbers for 'users' table
CREATE SEQUENCE users_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.users_id_seq
  OWNER TO postgres;
  
  
-- 'period_type' for 'agreement' table  
drop TYPE if exists period_type;
CREATE TYPE period_type AS ENUM (
    'MONTH',
    'YEAR',
    'QUARTER'
);
ALTER TYPE period_type OWNER TO postgres;



CREATE TABLE system (
    id integer NOT NULL DEFAULT nextval('system_id_seq'::regclass), -- or type serial
    name character varying(255) UNIQUE NOT NULL,
    description character varying(255) DEFAULT NULL,
    owner integer NOT NULL, --REFERENCES OWNER(id)   -----------FOREIGN KEY to OWNER(ID)----------
	CONSTRAINT system_pkey PRIMARY KEY (id)
);


CREATE TABLE users (
	id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
	name character varying(255) UNIQUE NOT NULL,
	password character varying(255) NOT NULL
);

CREATE TABLE agreement (
    id integer NOT NULL DEFAULT nextval('agreement_id_seq'::regclass),  -- or type serial
    order_number integer UNIQUE NOT NULL,
    date_from date not null default now(),
    to_date date not null default now(),
    amount numeric(6, 2)  not null, -- money or real	-- https://www.postgresql.org/docs/9.5/static/datatype-money.html
						-- LC_MONETARY   in  https://www.postgresql.org/docs/9.5/static/locale.html
    amount_type character varying(10)  not null,
    amount_period character varying(7)  not null,
    authorization_percent real,
    active boolean not null default true,
    request integer UNIQUE default null,
    system_id integer NOT NULL,
	CONSTRAINT agreement_pkey PRIMARY KEY (id),
	CONSTRAINT agreement_system_id_fkey FOREIGN KEY (id)
		REFERENCES public.system (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT check_amount_type CHECK (amount_type::text='NET'::text or amount_type::text='BRU'::text),
	CONSTRAINT agreement_check CHECK (to_date >= date_from),
	CONSTRAINT check_period_types CHECK (amount_period::text = 'MONTH'::text OR amount_period::text = 'YEAR'::text OR amount_period::text = 'QUARTER'::text)
);


ALTER TABLE agreement OWNER TO postgres;

ALTER TABLE system OWNER TO postgres;

insert into users (name, password)
values ('user', 'user'),
	('admin', 'admin')
-- insert into system (system_id, name, description, owner) values (1, 'KUCYK', '', 12),
--							(2, 'ŁÓDKA', '', 12);

							
insert into system (name, description, owner) 
values ( 'KUCYK', '', 12),
	( 'ŁÓDKA', '', 12),
	( 'KAPISZON', '', 12),
	( 'KOTEK', '', 11),
	( 'DEMON', '', 11),
	( 'ZÓŁWIK', '', 15),
	( 'KOJOTEK', '', 15);
							
insert into agreement (order_number, date_from, to_date, amount, amount_type, amount_period, authorization_percent, active, request, system_id) 
values ( 22, '2/2/2012', '4/3/2014', 100.00, 'NET', 'MONTH', 2, 'true',   11, 1),
	( 21, '3/10/2012', '3/3/2014', 555.00, 'NET', 'MONTH', 2, 'true', 12, 2),
	( 34, '6/4/2011', '6/5/2014', 453.33, 'NET', 'MONTH', 2, 'true',  13, 3),
	( 222, '1/1/2015', '1/1/2016', 123.31, 'BRU', 'YEAR', 2, 'false', 14, 4),
	( 303, '3/3/2017', '3/12/2017', 122.11, 'NET', 'MONTH', 2, 'true',  15, 5),
	( 212, '01/23/1917', '12/25/2017', 122.12, 'NET', 'MONTH', 2, 'true',  16, 6),
	( 311, '1/12/2017', '2/12/2017', 444.00, 'NET', 'MONTH', 2, 'true',  17, 7);
	

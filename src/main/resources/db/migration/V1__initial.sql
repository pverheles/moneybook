-- public.app_user definition

-- Drop table

-- DROP TABLE app_user;

CREATE TABLE app_user (
	create_date timestamp(6) NULL,
	id bigserial NOT NULL,
	modify_date timestamp(6) NULL,
	"version" int8 NULL,
	"name" varchar(50) NULL,
	"comment" varchar(1000) NULL,
	email varchar(255) NULL,
	state varchar(255) NULL,
	CONSTRAINT app_user_email_key UNIQUE (email),
	CONSTRAINT app_user_pkey PRIMARY KEY (id),
	CONSTRAINT app_user_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[])))
);


-- public.account definition

-- Drop table

-- DROP TABLE account;

CREATE TABLE account (
	amount numeric(25, 2) NOT NULL,
	create_date timestamp(6) NULL,
	id bigserial NOT NULL,
	modify_date timestamp(6) NULL,
	user_id int8 NULL,
	"version" int8 NULL,
	"name" varchar(50) NULL,
	"comment" varchar(1000) NULL,
	bank varchar(255) NULL,
	currency varchar(255) NOT NULL,
	state varchar(255) NULL,
	CONSTRAINT account_bank_check CHECK (((bank)::text = ANY ((ARRAY['MONOBANK'::character varying, 'PRIVATBANK'::character varying])::text[]))),
	CONSTRAINT account_currency_check CHECK (((currency)::text = ANY ((ARRAY['UAH'::character varying, 'USD'::character varying, 'EUR'::character varying])::text[]))),
	CONSTRAINT account_pkey PRIMARY KEY (id),
	CONSTRAINT account_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT fkjajia7qudllc01cly9yddon8u FOREIGN KEY (user_id) REFERENCES app_user(id)
);


-- public.category definition

-- Drop table

-- DROP TABLE category;

CREATE TABLE category (
	create_date timestamp(6) NULL,
	id bigserial NOT NULL,
	modify_date timestamp(6) NULL,
	user_id int8 NULL,
	"version" int8 NULL,
	"name" varchar(50) NULL,
	"comment" varchar(1000) NULL,
	state varchar(255) NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id),
	CONSTRAINT category_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT fkrlg1obihj1el9rttiseudaehs FOREIGN KEY (user_id) REFERENCES app_user(id)
);


-- public.expense_plan definition

-- Drop table

-- DROP TABLE expense_plan;

CREATE TABLE expense_plan (
	create_date timestamp(6) NULL,
	id bigserial NOT NULL,
	modify_date timestamp(6) NULL,
	user_id int8 NULL,
	"version" int8 NULL,
	"name" varchar(50) NULL,
	"comment" varchar(1000) NULL,
	state varchar(255) NULL,
	CONSTRAINT expense_plan_pkey PRIMARY KEY (id),
	CONSTRAINT expense_plan_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT fk6632jx5c41xb2bfu1e5ypx2ns FOREIGN KEY (user_id) REFERENCES app_user(id)
);


-- public.envelope definition

-- Drop table

-- DROP TABLE envelope;

CREATE TABLE envelope (
	create_date timestamp(6) NULL,
	expense_plan_id int8 NULL,
	id bigserial NOT NULL,
	modify_date timestamp(6) NULL,
	user_id int8 NULL,
	"version" int8 NULL,
	"name" varchar(50) NULL,
	"comment" varchar(1000) NULL,
	currency varchar(255) NULL,
	state varchar(255) NULL,
	CONSTRAINT envelope_currency_check CHECK (((currency)::text = ANY ((ARRAY['UAH'::character varying, 'USD'::character varying, 'EUR'::character varying])::text[]))),
	CONSTRAINT envelope_pkey PRIMARY KEY (id),
	CONSTRAINT envelope_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT fkb3dobm7aj1cb1hndfijonpcdk FOREIGN KEY (user_id) REFERENCES app_user(id),
	CONSTRAINT fkr4ystaak5oruror5qm8qmyf7r FOREIGN KEY (expense_plan_id) REFERENCES expense_plan(id)
);


-- public.operation definition

-- Drop table

-- DROP TABLE operation;

CREATE TABLE operation (
	amount numeric(25, 2) NOT NULL,
	account_id int8 NOT NULL,
	category_id int8 NULL,
	create_date timestamp(6) NULL,
	envelope_id int8 NULL,
	expense_plan_id int8 NULL,
	id bigserial NOT NULL,
	modify_date timestamp(6) NULL,
	user_id int8 NULL,
	"version" int8 NULL,
	"name" varchar(50) NULL,
	"comment" varchar(1000) NULL,
	state varchar(255) NULL,
	CONSTRAINT operation_pkey PRIMARY KEY (id),
	CONSTRAINT operation_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT fk34miq7v2mk22l40oketymlmul FOREIGN KEY (user_id) REFERENCES app_user(id),
	CONSTRAINT fkf5wq9tiycumrmvvg0nhvgycxi FOREIGN KEY (expense_plan_id) REFERENCES expense_plan(id),
	CONSTRAINT fkgonejnrj5fsgh68nix71n6xeb FOREIGN KEY (envelope_id) REFERENCES envelope(id),
	CONSTRAINT fkloy20r01mn4truqqu460w3j9q FOREIGN KEY (account_id) REFERENCES account(id),
	CONSTRAINT fkqgtsu1maiu5kviirvjg8phdkw FOREIGN KEY (category_id) REFERENCES category(id)
);
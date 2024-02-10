CREATE TABLE app_user (
	id bigserial NOT NULL,
	create_date timestamp(6) NOT NULL,
	modify_date timestamp(6) NOT NULL,
	"version" int8 NOT NULL,
	"name" varchar(50) NOT NULL,
	"comment" varchar(1000) NULL,
	email varchar(255) NOT NULL,
	state varchar(255) NOT NULL,
	CONSTRAINT app_user_email_key UNIQUE (email),
	CONSTRAINT app_user_pkey PRIMARY KEY (id),
	CONSTRAINT app_user_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[])))
);

CREATE TABLE account (
	id bigserial NOT NULL,
	amount numeric(25, 2) NOT NULL,
	create_date timestamp(6) NOT NULL,
	modify_date timestamp(6) NOT NULL,
	user_id int8 NOT NULL,
	"version" int8 NOT NULL,
	"name" varchar(50) NOT NULL,
	"comment" varchar(1000) NULL,
	bank varchar(255) NOT NULL,
	currency varchar(255) NOT NULL,
	state varchar(255) NULL,
	CONSTRAINT account_bank_check CHECK (((bank)::text = ANY ((ARRAY['MONOBANK'::character varying, 'PRIVATBANK'::character varying])::text[]))),
	CONSTRAINT account_currency_check CHECK (((currency)::text = ANY ((ARRAY['UAH'::character varying, 'USD'::character varying, 'EUR'::character varying])::text[]))),
	CONSTRAINT account_pkey PRIMARY KEY (id),
	CONSTRAINT account_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT account_user_id_fk FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE category (
	id bigserial NOT NULL,
	create_date timestamp(6) NOT NULL,
	modify_date timestamp(6) NOT NULL,
	user_id int8 NOT NULL,
	"version" int8 NOT NULL,
	"name" varchar(50) NOT NULL,
	"comment" varchar(1000) NULL,
	state varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id),
	CONSTRAINT category_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT category_user_id_fk FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE expense_plan (
	id bigserial NOT NULL,
	create_date timestamp(6) NOT NULL,
	modify_date timestamp(6) NOT NULL,
	user_id int8 NOT NULL,
	"version" int8 NOT NULL,
	"name" varchar(50) NOT NULL,
	"comment" varchar(1000) NULL,
	state varchar(255) NOT NULL,
	CONSTRAINT expense_plan_pkey PRIMARY KEY (id),
	CONSTRAINT expense_plan_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT expense_plan_user_id_fk FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE envelope (
	id bigserial NOT NULL,
	create_date timestamp(6) NOT NULL,
	modify_date timestamp(6) NOT NULL,
	expense_plan_id int8 NOT NULL,
	user_id int8 NOT NULL,
	"version" int8 NOT NULL,
	"name" varchar(50) NOT NULL,
	"comment" varchar(1000) NULL,
	currency varchar(255) NOT NULL,
	state varchar(255) NOT NULL,
	CONSTRAINT envelope_currency_check CHECK (((currency)::text = ANY ((ARRAY['UAH'::character varying, 'USD'::character varying, 'EUR'::character varying])::text[]))),
	CONSTRAINT envelope_pkey PRIMARY KEY (id),
	CONSTRAINT envelope_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT envelope_user_id_fk FOREIGN KEY (user_id) REFERENCES app_user(id),
	CONSTRAINT envelope_expense_plan_id_fk FOREIGN KEY (expense_plan_id) REFERENCES expense_plan(id)
);

CREATE TABLE operation (
	id bigserial NOT NULL,
	amount numeric(25, 2) NOT NULL,
	account_id int8 NOT NULL,
	category_id int8 NOT NULL,
	create_date timestamp(6) NOT NULL,
	modify_date timestamp(6) NOT NULL,
	envelope_id int8 NULL,
	expense_plan_id int8 NULL,
	user_id int8 NOT NULL,
	"version" int8 NOT NULL,
	"name" varchar(50) NOT NULL,
	"comment" varchar(1000) NULL,
	state varchar(255) NOT NULL,
	CONSTRAINT operation_pkey PRIMARY KEY (id),
	CONSTRAINT operation_state_check CHECK (((state)::text = ANY ((ARRAY['A'::character varying, 'I'::character varying])::text[]))),
	CONSTRAINT operation_user_id_pk FOREIGN KEY (user_id) REFERENCES app_user(id),
	CONSTRAINT operation_expense_plan_id_pk FOREIGN KEY (expense_plan_id) REFERENCES expense_plan(id),
	CONSTRAINT operation_envelope_id_pk FOREIGN KEY (envelope_id) REFERENCES envelope(id),
	CONSTRAINT operation_account_id_pk FOREIGN KEY (account_id) REFERENCES account(id),
	CONSTRAINT operation_category_id_pk FOREIGN KEY (category_id) REFERENCES category(id)
);
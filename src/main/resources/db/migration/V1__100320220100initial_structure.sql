----------------------------- CREATES SEQUENCES -------------------------
CREATE SEQUENCE public.configuration_fee_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.disbursement_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.merchant_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.order_entity_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.shopper_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
	CACHE 1
	NO CYCLE;

----------------------------- CREATES TABLES -------------------------
CREATE TABLE public.configuration_fee (
         id int8 NOT NULL DEFAULT nextval('configuration_fee_seq'),
         created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         last_modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         range_floor_amount numeric(19, 2) NOT NULL,
         range_ceil_amount numeric(19, 2) NOT NULL,
         fee numeric(19, 2) NOT NULL,
         deleted boolean,
         PRIMARY KEY (id)
);

CREATE TABLE public.disbursement (
         id int8 NOT NULL DEFAULT nextval('disbursement_seq'),
         created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         deleted bool NOT NULL,
         last_modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         disbursed_amount numeric(19, 2) NOT NULL,
         fee numeric(19, 2) NOT NULL,
         fee_value_charged numeric(19, 2) NOT NULL,
         week_of_year int4 NOT NULL,
         "year" int4 NOT NULL,
         merchant_id int8 NOT NULL,
         order_id int8 NOT NULL,
         PRIMARY KEY (id)
);

CREATE TABLE public.merchant (
         id int8 NOT NULL DEFAULT NEXTVAL('merchant_seq'),
         created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         deleted bool NOT NULL,
         last_modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         email varchar(255) NOT NULL,
         "name" varchar(255) NOT NULL,
         cif varchar(255) NOT NULL,
         PRIMARY KEY (id)
);

CREATE TABLE public.order_entity (
         id int8 NOT NULL DEFAULT nextval('order_entity_seq'),
         created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         deleted bool NOT NULL,
         last_modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
         amount numeric(19, 2) NOT NULL,
         completed_at timestamp NULL,
         merchant_id int8 NOT NULL,
         shopper_id int8 NOT NULL,
         PRIMARY KEY (id)
);

CREATE TABLE public.shopper (
        id int8 NOT NULL DEFAULT nextval('shopper_seq'),
        created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        deleted bool NOT NULL,
        last_modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        email varchar(255) NOT NULL,
        "name" varchar(255) NOT NULL,
        nif varchar(255) NOT NULL,
        PRIMARY KEY (id)
);

----------------------------- ADDS CONSTRAINTS INTO TABLES -------------------------
alter table if exists disbursement add constraint FK_disbursement_order foreign key (order_id) references order_entity;
alter table if exists disbursement add constraint FK_disbursement_merchant foreign key (merchant_id) references merchant;
alter table if exists order_entity add constraint FK_order_entity_merchant foreign key (merchant_id) references merchant;
alter table if exists order_entity add constraint FK_order_entity_shopper foreign key (shopper_id) references shopper;
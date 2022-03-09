----------------------------- CREATES SEQUENCES -------------------------
create sequence disbursement_seq start 1 increment 1;
create sequence merchant_seq start 1 increment 1;
create sequence order_entity_seq start 1 increment 1;
create sequence shopper_seq start 1 increment 1;

----------------------------- CREATES TABLES -------------------------
create table disbursement (id int8 not null, created_at timestamp, last_modified_at timestamp, disbursed_amount numeric(19, 2), fee numeric(19, 2), week_of_year int4, year int4, merchant_id int8, primary key (id));
create table disbursement_orders (disbursement_id int8 not null, order_id int8 not null);
create table merchant (id int8 not null, created_at timestamp, last_modified_at timestamp, email varchar(255), name varchar(255), cif varchar(255), primary key (id));
create table order_entity (id int8 not null, created_at timestamp, last_modified_at timestamp, amount numeric(19, 2), completed_at timestamp, merchant_id int8, order_id int8, shopper_id int8, primary key (id));
create table shopper (id int8 not null, created_at timestamp, last_modified_at timestamp, email varchar(255), name varchar(255), nif varchar(255), primary key (id));

----------------------------- ADDS CONSTRAINTS INTO TABLES -------------------------
alter table if exists disbursement_orders add constraint UK_disbursement_orders_order unique (order_id);
alter table if exists disbursement add constraint FK_disbursement_merchant foreign key (merchant_id) references merchant;
alter table if exists disbursement_orders add constraint FK_disbursement_orders_order foreign key (order_id) references order_entity;
alter table if exists disbursement_orders add constraint FK_disbursement_orders_disbursement foreign key (disbursement_id) references disbursement;
create table  api_tickets.tb_tickets(
    id bigserial primary key,
    cpf varchar(11) not null,
    customer_name varchar(100) not null,
    customer_mail varchar(200) not null,
    event_id bigint,
    brl_total_amount NUMERIC(19,2),
    usd_total_amount NUMERIC(19,2),
    status varchar(100) not null,

    CONSTRAINT fk_tickets_event
        FOREIGN KEY (event_id)
        REFERENCES api_events.tb_events(id)
);
insert into roles (name)
values ('USER');
insert into roles (name)
values ('ADMIN');

insert into users ( email,
                   name,
                   surname,
                   pass,
                   creation_date,
                   last_update_date,
                   version,
                   is_active)
values ('admin@mail.ru',
        'admin',
        'admin',
        '$2a$10$SJTmO168dU4AOwWGnHOn3uybCJ7I2SAC06enVIEsF7mjNvF9uBClO',--pass
        current_timestamp,
        current_timestamp,
        1,
        true);
insert into "user_roles" (role_id, user_id)
values (2, 1);
select * from users;
select * from roles;



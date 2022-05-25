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
        '$2a$10$pN0ELR5AmWsqzrZ2iADn7elWYuHM2ozMiMOc0WFX7d2dwJoq2HSbu',--yulia13
        current_timestamp,
        current_timestamp,
        1,
        true);
insert into "user_roles" (role_id, user_id)
values (2, 1);
select * from users;
select * from roles;



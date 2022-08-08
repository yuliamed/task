insert into roles (name)
values ('USER');
insert into roles (name)
values ('ADMIN');
insert into roles (name)
values ('AUTO_PICKER');

insert into users (email,
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
select *
from users;
select *
from roles;



insert into "bodies" (id, name)
values (1, 'SEDAN'),
       (2, 'STATION_WAGON'),
       (3, 'HATCHBACK'),
       (4, 'MINIVAN'),
       (5, 'OFF_ROAD_VEHICLE'),
       (6, 'COMPARTMENT'),
       (7, 'MINIBUS'),
       (8, 'VAN'),
       (9, 'PICKUP_TRUCK');

insert into "car_brands" (id, name)
values (1, 'HYUNDAI'),
       (2, 'KIA'),
       (3, 'SSANGYONG'),
       (4, 'DAEWOO'),
       (5, 'BAOJUN'),
       (6, 'BYD'),
       (7, 'BRILLIANCE'),
       (8, 'CHANGAN'),
       (9, 'CHERY'),
       (10, 'DONGFENG'),
       (11, 'FAW'),
       (12, 'FOTON'),
       (13, 'GAC'),
       (14, 'GEELY'),
       (15, 'GREAT_WALL'),
       (16, 'HAIMA'),
       (17, 'HAVAL'),
       (18, 'JAC'),
       (19, 'LANDWIND'),
       (20, 'LIFAN'),
       (21, 'QOROS'),
       (22, 'ROEWE'),
       (23, 'SAIC_MOTOR'),
       (24, 'WULING'),
       (25, 'ZOTYE'),
       (26, 'BUICK'),
       (27, 'CADDILAC'),
       (28, 'CHEVROLET'),
       (29, 'CHRYSLER'),
       (30, 'DODGE'),
       (31, 'EAGLE'),
       (32, 'LINCOLN'),
       (33, 'HUMMER'),
       (34, 'FISKER'),
       (35, 'FORD'),
       (36, 'GMC'),
       (37, 'JEEP'),
       (38, 'PANOZ'),
       (39, 'PONTIAC'),
       (40, 'SALEEN'),
       (41, 'SSC'),
       (42, 'TESLA'),
       (43, 'ACURA'),
       (44, 'DAIHATSU'),
       (45, 'DATSUN'),
       (46, 'INFINITI'),
       (47, 'HONDA'),
       (48, 'ISUZU'),
       (49, 'LEXUS'),
       (50, 'KAWASAKI'),
       (51, 'MAZDA'),
       (52, 'MITSUBISHI'),
       (53, 'NISSAN'),
       (54, 'SUBARU'),
       (55, 'SUZUKI'),
       (56, 'TOYOTA'),
       (57, 'YAMAHA'),
       (58, 'ASTON_MARTIN'),
       (59, 'BENTLEY'),
       (60, 'CATERHAM'),
       (61, 'JAGUAR'),
       (62, 'LAND_ROVER'),
       (63, 'LOTUS'),
       (64, 'MCLAREN'),
       (65, 'MG'),
       (66, 'MINI'),
       (67, 'ROLLS_ROYCE'),
       (68, 'ROVER'),
       (69, 'VAUXHALL'),
       (70, 'BMW');


insert into "currency_type" (id, name, rate_relative_dollar)
values (2, 'RUS', 53.32),
       (4, 'EUR', 0.9503),
       (3, 'USD', 1),
       (1, 'BYN', 2.53),
       (5, 'CAD', 1.3);

insert into "drives" (id, name)
values (1, 'FRONT'),
       (2, 'REAR'),
       (3, 'FULL');

insert into "engines" (id, name)
values (1, 'GASOLINE'),
       (2, 'DIESEL'),
       (3, 'ELECTRIC'),
       (4, 'GASOLINE_HYBRID'),
       (5, 'DIESEL_HYBRID');

insert into "order_statuses" (id, name)
values (1, 'CREATED'),
       (2, 'IN_PROCESS'),
       (3, 'CLOSED'),
       (4, 'CANCELED');

insert into "transmissions" (id, name)
values (1, 'MACHINE'),
       (2, 'MECHANICAL'),
       (3, 'ROBOT'),
       (4, 'VARIATOR');

select * from transmissions;
select * from order_statuses;
select * from engines;
select * from drives;
select * from currency_type;
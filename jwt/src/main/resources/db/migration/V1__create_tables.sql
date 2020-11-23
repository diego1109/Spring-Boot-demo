drop table if exists users;

create table users (
  `id` varchar(255) not null,
  `name` varchar(255) not null,
  `password` varchar(255) not null,
  primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into users (id, name, password) values ('eikjkla2l','diego','123');
INSERT INTO users (full_name, email, nickname, birthdate, profile_image, password, active)
VALUES('Sr Administrador', 'admin@cwi.com.br', 'admin', '1990-01-01',
 'https://this-person-does-not-exist.com/img/avatar-113a6c76357dc887908dcc5177201493.jpg',
 '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

INSERT INTO users (full_name, email, nickname, birthdate, profile_image, password, active)
VALUES('Joao Silva', 'joao@cwi.com.br', 'jojo', '2000-12-07',
 'https://this-person-does-not-exist.com/img/avatar-116f42d2361b5d8337eb90bbdce9f808.jpg',
 '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

INSERT INTO users (full_name, email, nickname, birthdate, profile_image, password, active)
VALUES('Claudio', 'claudio@cwi.com.br', 'clau', '1990-01-01',
  'https://this-person-does-not-exist.com/img/avatar-112d48a913e95e4d6e6af1b1df570d84.jpg',
  '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);


insert into permission (function, user_id) values ('ADMIN', 1);
insert into permission (function, user_id) values ('USER', 1);

insert into permission (function, user_id) values ('USER', 2);
insert into permission (function, user_id) values ('USER', 2);

insert into permission (function, user_id) values ('USER', 3);
insert into permission (function, user_id) values ('USER', 3);

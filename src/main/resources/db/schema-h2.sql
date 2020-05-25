DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  id          BIGINT(20)  NOT NULL COMMENT '主键ID',
  name        VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
  password    text,
  create_time TIMESTAMP   NULL,
  avatar      text,
  PRIMARY KEY (id)
);

alter table user
  add column phone VARCHAR(30);

CREATE TABLE article
(
  id              BIGINT(20)  NOT NULL COMMENT '主键ID',
  title           VARCHAR(60) NULL DEFAULT NULL COMMENT '姓名',
  author          VARCHAR(30),
  publish_date    TIMESTAMP   NULL,
  update_date     TIMESTAMP   NULL,
  article_content TEXT,
  article_tabloid TEXT,
  last_article_id BIGINT(20),
  next_article_id BIGINT(20),
  likes           BIGINT(20),
  PRIMARY KEY (id)
);
alter table article
  add column play_count BIGINT default 0 not null;

CREATE TABLE tag
(
  id   BIGINT(20)  NOT NULL COMMENT '主键ID',
  name VARCHAR(60) NULL DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (id)
);

create table article_tag
(
  article_id bigint not null,
  tag_id     bigint null,
  constraint FK_article_id
    foreign key (article_id) references article (id),
  constraint FK_tag_id
    foreign key (tag_id) references tag (id)
);

alter table article
  add column publish_status integer default 1;

alter table article
  add column copy_right integer default 0;

alter table article
  add column catalog_id bigint;

create table catalog
(
  id   bigint      not null comment '主键ID'
    primary key,
  name varchar(60) null comment '分类名'
);

alter table article
  add constraint FK_ID foreign key (catalog_id) REFERENCES catalog (id);


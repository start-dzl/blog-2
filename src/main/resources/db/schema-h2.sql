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

CREATE TABLE tag
(
  id   BIGINT(20)  NOT NULL COMMENT '主键ID',
  name VARCHAR(60) NULL DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (id)
);
alter table mbp.article_tag
  add constraint FK_article_id foreign key (article_id) references article (ID);
alter table mbp.article_tag
  add constraint FK_tag_id foreign key (tag_id) references tag (ID);
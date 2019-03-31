drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

drop table if exists oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity integer(11) DEFAULT NULL,
  refresh_token_validity integer(11) DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);

drop table if exists oauth_access_token;
create table `oauth_access_token` (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGBLOB,
  refresh_token VARCHAR(255)
);

drop table if exists oauth_refresh_token;
create table `oauth_refresh_token`(
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication LONGBLOB
);

drop table if exists authority;
CREATE TABLE authority (
  id  integer,
  authority varchar(255),
  primary key (id)
);

drop table if exists user;
CREATE TABLE user (
  id  integer auto_increment,
  enabled boolean not null,
  email varchar(255) not null,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  password varchar(255) not null,
  version integer,
  primary key (id)
);

drop table if exists user_authorities;
CREATE TABLE user_authorities (
  user_id bigint not null,
  authorities_id bigint not null
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication VARBINARY(8000)
);

drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);

drop table if exists password_reset_token;
create table password_reset_token (
    id  integer auto_increment,
    user_id VARCHAR(255),
    token VARCHAR(255),
    expiry_date_time DATETIME,
    PRIMARY KEY (id)
);

drop table if exists persistent_logins;
CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);

# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categoria (
  codigo                    bigint not null,
  nome                      varchar(255),
  descricao                 varchar(255),
  constraint pk_categoria primary key (codigo))
;

create table fabricante (
  codigo                    bigint not null,
  cnpj                      varchar(255),
  nome                      varchar(255),
  rua                       varchar(255),
  numero                    integer,
  bairro                    varchar(255),
  cep                       varchar(255),
  cidade                    varchar(255),
  estado                    varchar(255),
  pais                      varchar(255),
  telefone1                 varchar(255),
  telefone2                 varchar(255),
  email                     varchar(255),
  constraint pk_fabricante primary key (codigo))
;

create table fornecedor (
  codigo                    bigint not null,
  cnpj                      varchar(255),
  nome                      varchar(255),
  rua                       varchar(255),
  numero                    integer,
  bairro                    varchar(255),
  cep                       varchar(255),
  cidade                    varchar(255),
  estado                    varchar(255),
  pais                      varchar(255),
  telefone1                 varchar(255),
  telefone2                 varchar(255),
  email                     varchar(255),
  constraint pk_fornecedor primary key (codigo))
;

create table produto (
  codigo                    bigint not null,
  fornecedor_codigo         bigint,
  nome                      varchar(255),
  descricao                 varchar(255),
  modelo                    varchar(255),
  preco                     float,
  quantidade                float,
  altura                    integer,
  largura                   integer,
  comprimento               integer,
  peso                      float,
  constraint pk_produto primary key (codigo))
;

create sequence categoria_seq;

create sequence fabricante_seq;

create sequence fornecedor_seq;

create sequence produto_seq;

alter table produto add constraint fk_produto_fornecedor_1 foreign key (fornecedor_codigo) references fornecedor (codigo);
create index ix_produto_fornecedor_1 on produto (fornecedor_codigo);



# --- !Downs

drop table if exists categoria cascade;

drop table if exists fabricante cascade;

drop table if exists fornecedor cascade;

drop table if exists produto cascade;

drop sequence if exists categoria_seq;

drop sequence if exists fabricante_seq;

drop sequence if exists fornecedor_seq;

drop sequence if exists produto_seq;


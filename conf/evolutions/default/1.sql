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

create table produto (
  codigo                    bigint not null,
  fabricante_codigo         bigint,
  categoria_codigo          bigint,
  nome                      varchar(255),
  descricao                 varchar(255),
  modelo                    varchar(255),
  preco                     double,
  quantidade                double,
  altura                    integer,
  largura                   integer,
  comprimento               integer,
  peso                      double,
  constraint pk_produto primary key (codigo))
;

create sequence categoria_seq;

create sequence fabricante_seq;

create sequence produto_seq;

alter table produto add constraint fk_produto_fabricante_1 foreign key (fabricante_codigo) references fabricante (codigo) on delete restrict on update restrict;
create index ix_produto_fabricante_1 on produto (fabricante_codigo);
alter table produto add constraint fk_produto_categoria_2 foreign key (categoria_codigo) references categoria (codigo) on delete restrict on update restrict;
create index ix_produto_categoria_2 on produto (categoria_codigo);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists categoria;

drop table if exists fabricante;

drop table if exists produto;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists categoria_seq;

drop sequence if exists fabricante_seq;

drop sequence if exists produto_seq;


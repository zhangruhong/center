/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/27 17:04:43                           */
/*==============================================================*/


drop table if exists T_GOODS;

drop table if exists T_SYS_USER;

/*==============================================================*/
/* Table: T_GOODS                                               */
/*==============================================================*/
create table T_GOODS
(
   id                   varchar(100) not null,
   name                 varchar(500),
   mainImageUrl         varchar(500),
   detailUrl            varchar(500),
   shopName             varchar(100),
   originalPrice        float,
   soldCountPerMonth    int,
   incomingRate         float,
   incoming             float,
   salerWang            varchar(100),
   tbkShortUrl          varchar(100),
   tbkLongUrl           varchar(500),
   taoToken             varchar(100),
   ticketTotal          int,
   ticketLeft           int,
   ticketValue          varchar(100),
   ticketStartTime      datetime,
   ticketEndTime        datetime,
   ticketUrl            varchar(500),
   ticketTaoToken       varchar(100),
   ticketShortUrl       varchar(100),
   isPromotion          int,
   primary key (id)
);

alter table T_GOODS comment '商品表';

/*==============================================================*/
/* Table: T_SYS_USER                                            */
/*==============================================================*/
create table T_SYS_USER
(
   id                   varchar(100) not null,
   username             varchar(100),
   alipayNo		        varchar(200),
   alipayUsername       varchar(200),
   password				varchar(200),
   nickname				varchar(200),
   mobile                varchar(20),
   enabled				int,
   primary key (id)
);

alter table T_SYS_USER comment '用户信息表';


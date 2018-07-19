/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/11 15:12:50                           */
/*==============================================================*/


drop table if exists account;

drop table if exists business;

drop table if exists contacts;

drop table if exists log;

drop table if exists operation;

drop table if exists power;

/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account
(
   acc_id               numeric(10,0) not null,
   bus_id               numeric(10,0),
   account_type         varchar(50) not null comment '数据范围：1~4
            1：管理员
            2：前台
            3：客户经理
            4：经理',
   acc_name             varchar(50) not null,
   acc_psd              varchar(50) not null,
   isStart              bool not null,
   acc_time             datetime,
   primary key (acc_id)
);

/*==============================================================*/
/* Table: business                                              */
/*==============================================================*/
create table business
(
   bus_id               numeric(10,0) not null,
   con_id               numeric(10,0) not null,
   bus_type             varchar(50),
   bus_name             varchar(50),
   bus_add              varchar(50),
   bus_phone            varchar(50),
   bus_postcode         numeric(10,0),
   bus_star             int comment '数据范围：0~5  表示0到5星级',
   con_intergral        decimal,
   discount             decimal,
   primary key (bus_id)
);

/*==============================================================*/
/* Table: contacts                                              */
/*==============================================================*/
create table contacts
(
   con_id               numeric(10,0) not null,
   con_title            varchar(50),
   con_name             varchar(50),
   con_position         varchar(50),
   con_tel              varchar(50),
   con_mobile           numeric(20,0),
   con_fax              numeric(20,0),
   con_email            varchar(30),
   primary key (con_id)
);

/*==============================================================*/
/* Table: log                                                   */
/*==============================================================*/
create table log
(
   log_id               numeric(10,0) not null,
   acc_id               numeric(10,0),
   log_type             bool comment 'true为登陆，false为退出',
   log_time             datetime,
   primary key (log_id)
);

/*==============================================================*/
/* Table: operation                                             */
/*==============================================================*/
create table operation
(
   opt_id               numeric(10,0) not null,
   acc_id               numeric(10,0),
   opt_date             datetime not null,
   opt_url              varchar(50),
   opt_fun              varchar(50),
   opt_exp              varchar(50),
   remarks              varchar(50),
   primary key (opt_id)
);

/*==============================================================*/
/* Table: power                                                 */
/*==============================================================*/
create table power
(
   account_type         varchar(50) not null comment '数据范围：1~4
            1：管理员
            2：前台
            3：客户经理
            4：经理',
   record               bool comment 'true:只可修改30分钟内记录，且只能修改自己账户添加的接待记录
            false:可以修改本店48小时内的接待记录',
   information          bool comment 'true:只可以修改30分钟内记录，且只能修改自己账户添加的会员资料
            false:任何时间均可修改本店的会员信息',
   business             bool comment 'true:可以
            false:不可以',
   report               bool comment 'true:可以
            false:不可以',
   account              bool comment 'true:可以
            false:不可以',
   cardtype             bool comment 'true:可以
            false:不可以',
   recharge             bool comment 'true:可以
            false:不可以',
   manager              bool comment 'true:可以
            false:不可以',
   primary key (account_type)
);

alter table account add constraint FK_Relationship_10 foreign key (bus_id)
      references business (bus_id) on delete restrict on update restrict;

alter table account add constraint FK_Relationship_8 foreign key (account_type)
      references power (account_type) on delete restrict on update restrict;

alter table business add constraint FK_Relationship_1 foreign key (con_id)
      references contacts (con_id) on delete restrict on update restrict;

alter table log add constraint FK_Relationship_12 foreign key (acc_id)
      references account (acc_id) on delete restrict on update restrict;

alter table operation add constraint FK_Relationship_5 foreign key (acc_id)
      references account (acc_id) on delete restrict on update restrict;


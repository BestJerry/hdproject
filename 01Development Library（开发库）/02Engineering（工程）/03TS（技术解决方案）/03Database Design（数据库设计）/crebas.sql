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
   account_type         varchar(50) not null comment '���ݷ�Χ��1~4
            1������Ա
            2��ǰ̨
            3���ͻ�����
            4������',
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
   bus_star             int comment '���ݷ�Χ��0~5  ��ʾ0��5�Ǽ�',
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
   log_type             bool comment 'trueΪ��½��falseΪ�˳�',
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
   account_type         varchar(50) not null comment '���ݷ�Χ��1~4
            1������Ա
            2��ǰ̨
            3���ͻ�����
            4������',
   record               bool comment 'true:ֻ���޸�30�����ڼ�¼����ֻ���޸��Լ��˻���ӵĽӴ���¼
            false:�����޸ı���48Сʱ�ڵĽӴ���¼',
   information          bool comment 'true:ֻ�����޸�30�����ڼ�¼����ֻ���޸��Լ��˻���ӵĻ�Ա����
            false:�κ�ʱ������޸ı���Ļ�Ա��Ϣ',
   business             bool comment 'true:����
            false:������',
   report               bool comment 'true:����
            false:������',
   account              bool comment 'true:����
            false:������',
   cardtype             bool comment 'true:����
            false:������',
   recharge             bool comment 'true:����
            false:������',
   manager              bool comment 'true:����
            false:������',
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


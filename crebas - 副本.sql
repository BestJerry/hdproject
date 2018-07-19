/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/11 14:55:35                           */
/*==============================================================*/


drop table if exists News;

drop table if exists Userscheck;

drop table if exists admin;

drop table if exists users;

/*==============================================================*/
/* Table: News                                                  */
/*==============================================================*/
create table News
(
   n_flag               national varchar(50) not null,
   n_id                 int(11) NOT NULL AUTO_INCREMENT,
   u_id                 national varchar(50),
   title                national varchar(50) not null,
   n_body               national varchar(50) not null,
   author               national varchar(50) not null,
   n_date               date not null,
   n_abouts             national varchar(50),
   n_passflag           bool not null,
   primary key (n_id)
);

/*==============================================================*/
/* Table: Userscheck                                            */
/*==============================================================*/
create table Userscheck
(
   uc_number            national varchar(50) not null,
   uc_commitdate        date not null,
   uc_id                int(11) NOT NULL AUTO_INCREMENT,
   uc_flag              bool not null,
   uc_enddate           date,
   primary key (uc_id)
);

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   a_name               national varchar(50) not null,
   a_id                 national varchar(50) not null,
   a_psd                national varchar(50) not null,
   a_flag               national varchar(50) not null,
   primary key (a_id)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   u_id                 national varchar(50) not null,
   uc_id               int(11),
   u_name               national varchar(50) not null,
   psd                  national varchar(50) not null,
   number               national varchar(50),
   u_abouts             national varchar(50),
   phone                national varchar(50) not null,
   primary key (u_id)
);

alter table News add constraint FK_Relationship_5 foreign key (u_id)
      references users (u_id) on delete restrict on update restrict;

alter table users add constraint FK_Relationship_2 foreign key (uc_id)
      references Userscheck (uc_id) on delete restrict on update restrict;


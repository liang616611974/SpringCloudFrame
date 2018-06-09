drop index idx_group_code on scd_dict;

drop table if exists scd_dict;

/*==============================================================*/
/* Table: scd_dict                                              */
/*==============================================================*/
create table scd_dict
(
   id                   bigint(20) not null comment '����',
   group_code           varchar(35) not null comment '�ֵ�����',
   dict_code            varchar(35) not null comment '�ֵ���',
   sys_code             varchar(4) not null comment 'ϵͳ����',
   group_desc           varchar(50) not null comment '�ֵ�������',
   dict_desc            varchar(50) not null comment '�ֵ�����',
   dict_order           tinyint(2) not null comment '˳��',
   is_use               tinyint(1) not null comment '�Ƿ�ʹ��',
   cre_user             bigint(20) not null comment '�����û�',
   cre_time             datetime not null comment '����ʱ��',
   mdf_user             bigint(20) comment '�޸��û�',
   mdf_time             datetime not null comment '�޸�ʱ��',
   primary key (id)
);

alter table scd_dict comment '�ֵ��';

/*==============================================================*/
/* Index: idx_group_code                                        */
/*==============================================================*/
create index idx_group_code on scd_dict
(
   group_code
);

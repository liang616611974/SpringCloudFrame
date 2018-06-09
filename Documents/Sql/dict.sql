drop table if exists scd_dict;

/*==============================================================*/
/* Table: scd_dict                                              */
/*==============================================================*/
create table scd_dict
(
   group_id             varchar(35) not null comment '�ֵ���id',
   dict_id              varchar(35) not null comment '�ֵ�id',
   sys_code             varchar(4) not null comment 'ϵͳ����',
   group_desc           varchar(50) not null comment '�ֵ�������',
   dict_desc            varchar(50) not null comment '�ֵ�����',
   dict_order           tinyint(2) not null comment '˳��',
   is_use               tinyint(1) not null comment '�Ƿ�ʹ��',
   cre_time             datetime not null comment '����ʱ��',
   mdf_time             datetime not null comment '�޸�ʱ��',
   primary key (group_id, dict_id)
);

alter table scd_dict comment '�ֵ��';

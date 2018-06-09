drop index idx_group_code on scd_dict;

drop table if exists scd_dict;

/*==============================================================*/
/* Table: scd_dict                                              */
/*==============================================================*/
create table scd_dict
(
   id                   bigint(20) not null comment '主键',
   group_code           varchar(35) not null comment '字典组编号',
   dict_code            varchar(35) not null comment '字典编号',
   sys_code             varchar(4) not null comment '系统编码',
   group_desc           varchar(50) not null comment '字典组描述',
   dict_desc            varchar(50) not null comment '字典描述',
   dict_order           tinyint(2) not null comment '顺序',
   is_use               tinyint(1) not null comment '是否使用',
   cre_user             bigint(20) not null comment '创建用户',
   cre_time             datetime not null comment '创建时间',
   mdf_user             bigint(20) comment '修改用户',
   mdf_time             datetime not null comment '修改时间',
   primary key (id)
);

alter table scd_dict comment '字典表';

/*==============================================================*/
/* Index: idx_group_code                                        */
/*==============================================================*/
create index idx_group_code on scd_dict
(
   group_code
);

drop table if exists scd_dict;

/*==============================================================*/
/* Table: scd_dict                                              */
/*==============================================================*/
create table scd_dict
(
   group_id             varchar(35) not null comment '字典组id',
   dict_id              varchar(35) not null comment '字典id',
   sys_code             varchar(4) not null comment '系统编码',
   group_desc           varchar(50) not null comment '字典组描述',
   dict_desc            varchar(50) not null comment '字典描述',
   dict_order           tinyint(2) not null comment '顺序',
   is_use               tinyint(1) not null comment '是否使用',
   cre_time             datetime not null comment '创建时间',
   mdf_time             datetime not null comment '修改时间',
   primary key (group_id, dict_id)
);

alter table scd_dict comment '字典表';

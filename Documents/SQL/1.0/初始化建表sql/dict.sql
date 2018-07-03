-- drop index uk_sys_group_code on scd_dict;

DROP TABLE IF EXISTS scd_dict;

/*==============================================================*/
/* Table: scd_dict                                              */
/*==============================================================*/
CREATE TABLE scd_dict
(
   id                   BIGINT(20) NOT NULL COMMENT '主键',
   group_code           VARCHAR(35) NOT NULL COMMENT '字典组编号',
   dict_code            VARCHAR(35) NOT NULL COMMENT '字典编号',
   sys_code             VARCHAR(4) NOT NULL COMMENT '系统编码',
   group_desc           VARCHAR(50) NOT NULL COMMENT '字典组描述',
   dict_desc            VARCHAR(50) NOT NULL COMMENT '字典描述',
   dict_order           TINYINT(2) NOT NULL COMMENT '顺序',
   is_use               TINYINT(1) NOT NULL COMMENT '是否使用',
   cre_user             BIGINT(20) NOT NULL COMMENT '创建用户',
   cre_time             DATETIME NOT NULL COMMENT '创建时间',
   mdf_user             BIGINT(20) COMMENT '修改用户',
   mdf_time             DATETIME NOT NULL COMMENT '修改时间',
   PRIMARY KEY (id)
);

ALTER TABLE scd_dict COMMENT '字典表';

/*==============================================================*/
/* Index: uk_sys_group_code                                     */
/*==============================================================*/
CREATE UNIQUE INDEX uk_sys_group_code ON scd_dict
(
   sys_code,
   group_code
);
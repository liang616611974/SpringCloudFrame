drop table if exists scd_goods;

/*==============================================================*/
/* Table: scd_goods                                             */
/*==============================================================*/
create table scd_goods
(
   id                   bigint(20) not null comment '主键',
   goods_name           varchar(50) not null comment '商品名称',
   goods_type           varchar(35) not null comment '商品类型',
   price                decimal(20,2) not null comment '价格',
   producer             varchar(50) comment '生产商',
   produce_date         date comment '生产日期',
   cre_time             datetime not null comment '创建时间',
   mdf_time             datetime comment '修改时间',
   cre_user             bigint(20) comment '创建用户',
   mdf_user             bigint(20) comment '修改用户',
   primary key (id)
)
ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='商品表';

alter table scd_goods comment '商品表';

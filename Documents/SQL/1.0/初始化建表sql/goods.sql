DROP TABLE IF EXISTS scd_goods;

/*==============================================================*/
/* Table: scd_goods                                             */
/*==============================================================*/
CREATE TABLE scd_goods
(
   id                   BIGINT(20) NOT NULL COMMENT '主键',
   goods_name           VARCHAR(50) NOT NULL COMMENT '商品名称',
   goods_type           VARCHAR(35) NOT NULL COMMENT '商品类型',
   price                DECIMAL(20,2) NOT NULL COMMENT '价格',
   producer             VARCHAR(50) COMMENT '生产商',
   produce_date         DATE COMMENT '生产日期',
   img_url              VARCHAR(100) COMMENT '图片',
   cre_time             DATETIME NOT NULL COMMENT '创建时间',
   mdf_time             DATETIME COMMENT '修改时间',
   cre_user             BIGINT(20) COMMENT '创建用户',
   mdf_user             BIGINT(20) COMMENT '修改用户',
   PRIMARY KEY (id)
)
ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='商品表';

ALTER TABLE scd_goods COMMENT '商品表';

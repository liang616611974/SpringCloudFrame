drop table if exists scd_goods;

/*==============================================================*/
/* Table: scd_goods                                             */
/*==============================================================*/
create table scd_goods
(
   id                   bigint(20) not null comment '����',
   goods_name           varchar(50) not null comment '��Ʒ����',
   goods_type           varchar(35) not null comment '��Ʒ����',
   price                decimal(20,2) not null comment '�۸�',
   producer             varchar(50) comment '������',
   produce_date         date comment '��������',
   cre_time             datetime not null comment '����ʱ��',
   mdf_time             datetime comment '�޸�ʱ��',
   cre_user             bigint(20) comment '�����û�',
   mdf_user             bigint(20) comment '�޸��û�',
   primary key (id)
)
ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='��Ʒ��';

alter table scd_goods comment '��Ʒ��';

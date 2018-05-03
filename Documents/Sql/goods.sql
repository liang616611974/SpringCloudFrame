DROP TABLE IF EXISTS goods;
CREATE TABLE `goods` (
  `id` BIGINT(20) NOT NULL COMMENT '����',
  `goods_name` VARCHAR(50) DEFAULT NULL COMMENT '��Ʒ����',
  `type` VARCHAR(50) DEFAULT NULL COMMENT '��Ʒ����',
  `price` DECIMAL(20,2) DEFAULT NULL COMMENT '��Ʒ�۸�',
  `producer_name` VARCHAR(50) DEFAULT NULL COMMENT '����������',
  `produce_date` DATE DEFAULT NULL COMMENT '��������',
  `create_time` DATETIME DEFAULT NULL COMMENT '����ʱ��',
  `modify_time` DATETIME DEFAULT NULL COMMENT '�޸�ʱ��',
  `create_user` BIGINT(20) DEFAULT NULL COMMENT '�����û�����',
  `modify_user` BIGINT(20) DEFAULT NULL COMMENT '�޸��û�����',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='��Ʒ��'
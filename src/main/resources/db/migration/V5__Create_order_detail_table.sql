CREATE TABLE order_details (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '注文詳細ID',
  order_id int(11) NOT NULL COMMENT '注文ID',
  product_id int(11) NOT NULL COMMENT '商品ID',
  unit int(11) NOT NULL COMMENT '数量',
  created_at datetime NOT NULL DEFAULT current_timestamp() COMMENT '作成日時',
  modified_at datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日時',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='注文詳細';

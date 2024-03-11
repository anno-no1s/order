CREATE TABLE products (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  name varchar(255) NOT NULL COMMENT '商品名',
  price int(11) NOT NULL COMMENT '価格',
  created_at datetime NOT NULL DEFAULT current_timestamp() COMMENT '作成日時',
  modified_at datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日時',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品';

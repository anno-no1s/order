CREATE TABLE customers (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '顧客ID',
  name varchar(255) NOT NULL COMMENT '顧客名',
  created_at datetime NOT NULL DEFAULT current_timestamp() COMMENT '作成日時',
  modified_at datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日時',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顧客';

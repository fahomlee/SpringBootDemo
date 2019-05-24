--角色表
CREATE TABLE `sys_role` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`name` VARCHAR(50) NOT NULL COMMENT '名称',
	`create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
)
COMMENT='角色'
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;
--添加字段
alter table sys_role add column sex INT(1) NOT NULL DEFAULT 1 COMMENT '1男0女';
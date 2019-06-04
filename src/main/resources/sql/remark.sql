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


--角色表数据
INSERT INTO `sys_role` (`id`, `name`, `sex`, `create_time`, `update_time`) VALUES (1, '角色1', '2', '2019-05-29 17:36:49', '2019-06-04 10:00:31');
INSERT INTO `sys_role` (`id`, `name`, `sex`, `create_time`, `update_time`) VALUES (2, '角色2', '1', '2019-05-29 17:39:38', '2019-06-04 10:00:36');
INSERT INTO `sys_role` (`id`, `name`, `sex`, `create_time`, `update_time`) VALUES (3, '角色3', '1', '2019-05-29 17:41:12', '2019-06-04 10:00:38');
INSERT INTO `sys_role` (`id`, `name`, `sex`, `create_time`, `update_time`) VALUES (4, '角色4', '1', '2019-05-29 17:42:55', '2019-06-04 10:00:41');
--用户表数据
INSERT INTO `sys_user` (`id`, `name`, `age`) VALUES ('1', '用户1', 15);
--用户角色关系表数据
INSERT INTO `user_role_rel` (`id`, `sys_user_id`, `sys_role_id`) VALUES ('1', '1', 1);
INSERT INTO `user_role_rel` (`id`, `sys_user_id`, `sys_role_id`) VALUES ('2', '1', 2);
INSERT INTO `user_role_rel` (`id`, `sys_user_id`, `sys_role_id`) VALUES ('3', '1', 3);
INSERT INTO `user_role_rel` (`id`, `sys_user_id`, `sys_role_id`) VALUES ('4', '2', 2);


--角色用户关系表
CREATE TABLE `user_role_rel` (
	`id` VARCHAR(50) NOT NULL COMMENT '主键',
	`sys_user_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '用户id',
	`sys_role_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '角色id',
	PRIMARY KEY (`id`)
)
COMMENT='用户-角色关系表'
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;




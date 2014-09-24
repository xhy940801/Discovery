SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE `user_secu_infos`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(32) NOT NULL COMMENT '用户名',
	`password` CHAR(32) NOT NULL COMMENT 'md5 hash后的密码',
	`registration_time` DATETIME NOT NULL,
	`last_login_time` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY (`email`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `user_esse_infos`(
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_secu_info_id` INT NOT NULL,
	`nickname` VARCHAR(16) NOT NULL,
	`tel` VARCHAR(16) NOT NULL DEFAULT '',
	`phone` VARCHAR(16) NOT NULL DEFAULT '',
	`address` VARCHAR(255) NOT NULL DEFAULT '',
	PRIMARY KEY (`id`),
	UNIQUE KEY (`user_secu_info_id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
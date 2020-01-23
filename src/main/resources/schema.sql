DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `email` VARCHAR(255) UNIQUE,
  `password` CHAR(60),
  `reset_token` CHAR(36)
);
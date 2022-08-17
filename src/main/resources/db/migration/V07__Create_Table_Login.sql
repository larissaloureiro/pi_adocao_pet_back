DROP TABLE IF EXISTS `tb_login`;
CREATE TABLE `tb_login` (
  `id_login` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `senha_login` varchar(255) DEFAULT NULL,
  `username_login` varchar(255) DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_login`),
  UNIQUE KEY `uk_username_login` (`username_login`),
  KEY `fk_login_usuario` (`id_usuario`),
  CONSTRAINT `fk_login_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
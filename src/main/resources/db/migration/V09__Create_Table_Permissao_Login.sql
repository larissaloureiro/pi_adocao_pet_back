DROP TABLE IF EXISTS `permissao_login`;
CREATE TABLE `permissao_login` (
  `id_login` bigint NOT NULL,
  `id_permissao` bigint NOT NULL,
  KEY `fk_permissaologin_permissao` (`id_permissao`),
  KEY `fk_permissaologin_login` (`id_login`),
  CONSTRAINT `fk_permissaologin_permissao` FOREIGN KEY (`id_permissao`) REFERENCES `tb_permissao` (`id`),
  CONSTRAINT `fk_permissaologin_login` FOREIGN KEY (`id_login`) REFERENCES `tb_login` (`id_login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
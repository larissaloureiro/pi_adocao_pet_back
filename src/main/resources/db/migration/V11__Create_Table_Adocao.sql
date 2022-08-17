DROP TABLE IF EXISTS `tb_adocao`;
CREATE TABLE `tb_adocao` (
  `id_adocao` bigint NOT NULL AUTO_INCREMENT,
  `data_atualizacao` datetime NOT NULL,
  `data_solicitacao` datetime NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `id_animal` bigint DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_adocao`),
  KEY `fk_adocao_animal` (`id_animal`),
  KEY `fk_adocao_usuario` (`id_usuario`),
  CONSTRAINT `fk_adocao_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  CONSTRAINT `fk_adocao_animal` FOREIGN KEY (`id_animal`) REFERENCES `tb_animal` (`id_animal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
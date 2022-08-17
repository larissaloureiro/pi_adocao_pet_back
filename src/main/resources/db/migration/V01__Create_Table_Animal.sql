DROP TABLE IF EXISTS `tb_animal`;
CREATE TABLE `tb_animal` (
  `id_animal` bigint NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `descricao` varchar(250) DEFAULT NULL,
  `disponivel` bit(1) NOT NULL,
  `especie` varchar(50) DEFAULT NULL,
  `foto_base64` longtext,
  `idade` int NOT NULL,
  `inf_adicionais` varchar(250) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `porte` varchar(50) DEFAULT NULL,
  `raca` varchar(50) DEFAULT NULL,
  `sexo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_animal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
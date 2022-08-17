DROP TABLE IF EXISTS `tb_usuario`;
CREATE TABLE `tb_usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `cpf_usuario` varchar(11) DEFAULT NULL,
  `data_cadastro_usuario` datetime NOT NULL,
  `data_nascimento_usuario` datetime NOT NULL,
  `email_usuario` varchar(255) DEFAULT NULL,
  `cep_end_usuario` varchar(255) DEFAULT NULL,
  `complemento_end_usuario` varchar(255) DEFAULT NULL,
  `localidade_end_usuario` varchar(255) DEFAULT NULL,
  `logradouro_end_usuario` varchar(255) DEFAULT NULL,
  `numero_end_usuario` varchar(255) DEFAULT NULL,
  `referencia_end_usuario` varchar(255) DEFAULT NULL,
  `uf_end_usuario` varchar(255) DEFAULT NULL,
  `nome_usuario` varchar(45) DEFAULT NULL,
  `rg_usuario` varchar(9) DEFAULT NULL,
  `telefone_usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;
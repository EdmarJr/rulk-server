
DELETE FROM usuario WHERE 1=1;
DELETE FROM unidade WHERE 1=1;
DELETE FROM empresa WHERE 1=1;
insert into empresa (email,ativo,nome,telefone) values ('teste@gmail.com','true','Teste','6182089940');
insert into unidade (ativo,nome,empresa_id) values ('true','Unidade Teste',1);
insert into usuario(email,cpf,unidade_id) values ('teste@gmail.com','75394073104',1);
insert into usuario (email,cpf,foto,hashSenha,unidade_id)values ('edmarfagunde@gmail.com','75394073104',NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',1);
insert into plano (nome,valor_mensal,unidade_id) values('Teste',1234.55,1);
insert into colaborador (ativo,dataFim,dataInicio,email) values ('true',NULL,NULL,'edmarfagunde@gmail.com');
insert into colaborador_has_permission_in_unidades (unidade_id,colaborador_id) values ('edmarfagunde@gmail.com',1);
insert into grupo values ('ADMINISTRADOR');
insert into usuario_grupo values ('ADMINISTRADOR','edmarfagunde@gmail.com');
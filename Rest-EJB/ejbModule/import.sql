use rulk;
DELETE FROM dbo.usuario WHERE 1=1;
DELETE FROM dbo.unidade WHERE 1=1;
DELETE FROM dbo.empresa WHERE 1=1;
DBCC CHECKIDENT (unidade, RESEED, 0);
DBCC CHECKIDENT (empresa, RESEED, 0);
insert into dbo.empresa (email,ativo,nome,telefone) values ('teste@gmail.com','true','Teste','6182089940');
insert into dbo.unidade (ativo,nome,empresa_id) values ('true','Unidade Teste',1);
insert into dbo.usuario(email,cpf,unidade_id) values ('teste@gmail.com','75394073104',1);
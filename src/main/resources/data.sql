INSERT INTO my_users(id, email, login, nome, senha, sobrenome)
	VALUES (1, 'wfrauches89@gmail.com', 'Wfrauches89', 'Wanderson', '123WaF', 'Frauches');

ALTER SEQUENCE my_users_ID_SEQ RESTART WITH 2;

INSERT INTO my_perfils(id, descricao)VALUES (1, 'Administrador');
INSERT INTO my_perfils(id, descricao)VALUES (2, 'Gerente');
INSERT INTO my_perfils(id, descricao)VALUES (3, 'Cliente');

ALTER SEQUENCE my_perfils_SEQ RESTART WITH 4;

INSERT INTO my_perfil_usuario_relacional(id, id_perfil, id_usuario)VALUES (1, 1, 1);

ALTER SEQUENCE my_perfil_usuario_relacional_seq RESTART WITH 2;

INSERT INTO recursos(id, chave, nome)VALUES (1, 'Usuário', 'Tela Usuário');
INSERT INTO recursos(id, chave, nome)VALUES (2, 'Perfil', 'Tela Perfil');
INSERT INTO recursos(id, chave, nome)VALUES (3, 'Recurso', 'Tela Recurso');

ALTER SEQUENCE recursos_seq RESTART WITH 4;

INSERT INTO my_permissao_perfil_recurso_relacional(id, id_perfil, id_recursos)VALUES (1, 1, 1);

ALTER SEQUENCE my_permissao_perfil_recurso_relacional_seq RESTART WITH 2;
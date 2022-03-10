insert into serie_documento(credito, debito,descricao, documento,movimenta_entrada,movimenta_saida,ultimo_numero) VALUES (false, true, 'Guia de entrada', 'GE', true, false,0);
insert into serie_documento(credito, debito,descricao, documento,movimenta_entrada,movimenta_saida,ultimo_numero) VALUES (false, false, 'Guia de Saida', 'GS', false, true,0);
insert into serie_documento(credito, debito,descricao, documento,movimenta_entrada,movimenta_saida,ultimo_numero) VALUES (true, false, 'Devolucao', 'DV', false, true,0);

insert into departamento (nome, orcamento, sigla) VALUES ('Tecnologias de Informacao', '350000', 'TI');
insert into departamento (nome, orcamento, sigla) VALUES ('Producao', '135000', 'PD');

insert into funcionario (contacto, email, localidade,morada,nome,departamento_id) VALUES ('842460275', 'blandino@gmail.com', 'kha Pfumo', 'Av Malhangalene', 'Blandino Junior',1);
insert into funcionario (contacto, email, localidade,morada,nome,departamento_id) VALUES ('823645248', 'sibone@gmail.com', 'Namaacha', 'Bairro B', 'Sibone Mucumbe',2);

INSERT INTO fornecedor (cidade, email, endereco, nome, nuit, pais,telefobe) VALUES ('Matola', 'itmoz@gmail.com', 'Av Namaacha', 'IT Moz Lda', '12345678', 'Moçambique', '842460275');
INSERT INTO fornecedor (cidade, email, endereco, nome,nuit, pais, telefobe) VALUES ('Beira', 'plastex@gmail.com', 'Av Luis caBral', 'Plastex Lda', '13679842', 'Moçambique', '872460277');
INSERT INTO fornecedor (cidade, email, endereco, nome, nuit, pais,telefobe) VALUES ('Maputo', 'pintex@gmail.com', 'Av Malhangalene', 'Pintex Lda', '24681367', 'Moçambique', '823645248');

INSERT INTO categoria_entrada (existencia,nome, fornecedor_id) VALUES ('120', 'Mobile Phones', '1');
INSERT INTO categoria_entrada (existencia,nome, fornecedor_id) VALUES ('170', 'Bags', '2');
INSERT INTO categoria_entrada (existencia,nome, fornecedor_id) VALUES ('80', 'Pallets', '2');
INSERT INTO categoria_entrada (existencia,nome, fornecedor_id) VALUES ('27', 'Cartridge', '1');
INSERT INTO categoria_entrada (existencia,nome, fornecedor_id) VALUES ('26', 'Sprays', '3');

INSERT INTO orcamento (descricao,valor,departamento_id,funcionario_id) VALUES ('Orcamento 1 Semestre', '150000', '1', '1');
INSERT INTO orcamento (descricao,valor,departamento_id,funcionario_id) VALUES ('Orcamento 2 Semestre', '200000', '1', '1');
INSERT INTO orcamento (descricao,valor,departamento_id,funcionario_id) VALUES ('Orcamento anual 2022', '135000', '2', '2');

INSERT INTO registo(data, numero_documento, funcionario_id, serie_documento_id) VALUES ('2022-01-12', 1, 1,1);

INSERT INTO artigos_registo (descricao,quantidade,valor, registo_id,categoria_entrada_id) VALUES ('Nokia',4,12500, 1,1);
INSERT INTO artigos_registo (descricao,quantidade,valor, registo_id,categoria_entrada_id) VALUES ('HP',6,17500, 1,2);
INSERT INTO artigos_registo (descricao,quantidade,valor, registo_id,categoria_entrada_id) VALUES ('HP1245',2,22000, 1,4);

INSERT INTO registo(data, numero_documento, funcionario_id, serie_documento_id) VALUES ('2022-02-18', 1, 1,2);

INSERT INTO artigos_registo (descricao,quantidade,valor, registo_id,categoria_entrada_id) VALUES ('HP4510',2,42000, 2,4);

INSERT INTO registo(data, numero_documento, funcionario_id, serie_documento_id) VALUES ('2022-03-07', 1, 2,3);

INSERT INTO artigos_registo (descricao,quantidade,valor, registo_id,categoria_entrada_id) VALUES ('XP',2,1400, 3,5);











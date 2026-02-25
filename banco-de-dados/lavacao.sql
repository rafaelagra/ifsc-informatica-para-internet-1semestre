-- 1. Fase DDL: Criação do Banco de Dados e Tabelas

--  CRIAÇÃO DO BANCO DE DADOS LAVAÇÃO
CREATE DATABASE IF NOT EXISTS Lavacao;
use Lavacao;

-- Criação da Tabela CLIENTE (Superentidade)
CREATE TABLE cliente (
	id INT NOT NULL AUTO_INCREMENT, -- Chave Primária (PK) e Auto Incremento
    nome VARCHAR(50),
    celular VARCHAR(50),
    email VARCHAR(100),
    endereco VARCHAR(100),
    dataCadastro DATE,
    dataNascimento DATE,
    PRIMARY KEY (id)
);

-- Criação da Tabela PESSOA_FISICA (Subentidade)
-- O id_cliente é PK e FK ao mesmo tempo, garantindo o relacionamento 1:1 com cliente.
CREATE TABLE pessoa_fisica (
	id_cliente INT NOT NULL,
    cpf VARCHAR(100),
    PRIMARY KEY (id_cliente),
    -- Define a Chave Estrangeira (FK)
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
		ON DELETE CASCADE -- Se o cliente for apagado, o registro aqui também é
);

-- Criação da Tabela PESSOA_JURIDICA (Subentidade)
-- O id_cliente é PK e FK ao mesmo tempo.
CREATE TABLE pessoa_juridica (
	id_cliente INT NOT NULL,
    cnpj VARCHAR(100),
    inscricaoEstadual VARCHAR(100),
    PRIMARY KEY (id_cliente),
    -- Define a Chave Estrangeira (FK)
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
		ON DELETE CASCADE
);

-- 2. Fase DML: População das Tabelas (5 Registros)

--  População da Tabela CLIENTE
INSERT INTO cliente (nome, celular, email, endereco, dataCadastro, dataNascimento) VALUES
('João Silva', '(11) 98765-4321', 'joao.s@email.com', 'Rua A, 100', '2025-10-01', '1985-05-20'), -- ID 1 (PF)
('Empresa Alfa Ltda.', '(21) 3333-4444', 'contato@alfa.com', 'Av B, 500', '2025-10-05', NULL),          -- ID 2 (PJ)
('Maria Santos', '(31) 91111-2222', 'maria.s@email.com', 'Rua C, 30', '2025-10-10', '1992-08-15'),  -- ID 3 (PF)
('Indústria Beta S/A', '(41) 5555-6666', 'fiscal@beta.com', 'Rodovia D, 10', '2025-10-15', NULL),    -- ID 4 (PJ)
('Pedro Souza', '(51) 94444-5555', 'pedro.s@email.com', 'Rua E, 22', '2025-10-20', '2000-01-01');   -- ID 5 (PF)

-- População da Tabela PESSOA_FISICA (IDs 1, 3, 5)
INSERT INTO pessoa_fisica (id_cliente, cpf) VALUES
(1, '111.111.111-11'),
(3, '333.333.333-33'),
(5, '555.555.555-55');

-- População da Tabela PESSOA_JURIDICA (IDs 2, 4)
INSERT INTO pessoa_juridica (id_cliente, cnpj, inscricaoEstadual) VALUES
(2, '22.222.222/0001-22', 'ISENTO'),
(4, '44.444.444/0001-44', '123.456.789.000');

-- 3. Consultas SQL 
-- 1. Mostrar todas as informações de cliente (Tabela CLIENTE)
SELECT * FROM cliente;

-- 2. Mostrar todas as informações de cliente pessoa física
SELECT
	c.*, -- Seleciona todas as colunas da tabela cliente
    pf.cpf -- Seleciona o CPF da tabela pessoa_fisica
FROM
	cliente c -- defini a tabela cliente e dei apelido (alias) para ela de 'c'
JOIN 
	pessoa_fisica pf -- junta com a tabela pessoa_fisica, que eu apelidei de 'pf'
    ON c.id = pf.id_cliente ; -- O ID do cliente deve ser igual ao ID_CLIENTE da pessoa física.
    

-- 3. Mostrar todas as informações de cliente pessoa jurídica
SELECT 
    c.*, -- Seleciona todas as colunas da tabela cliente (c.* significa 'todas as colunas do alias c')
    pj.cnpj, -- Seleciona o CNPJ
    pj.inscricaoEstadual -- Seleciona a Inscrição Estadual
FROM 
    cliente c -- Inicia a consulta na tabela cliente (Superentidade)
JOIN 
    pessoa_juridica pj -- Junta com a tabela pessoa_juridica (Subentidade)
    ON c.id = pj.id_cliente; -- a chave primária (id) de cliente deve ser igual à chave estrangeira (id_cliente) de pessoa_juridica.

-- 4. Mostrar apenas o nome e cpf de todos os clientes pessoa fisica
SELECT
	c.nome, -- seleciona somente o nome da tabela cliente
    pf.cpf -- seleciona somente o CPF da tabela pessoa_fisica
FROM
	cliente c -- Tabela cliente
INNER JOIN
	pessoa_fisica pf -- Junta com a tabela pessoa_fisica
    ON c.id = pf.id_cliente; -- a chave primária (id) de cliente deve ser igual à chave estrangeira (id_cliente) de pessoa_fisica

-- 5. Mostrar nome, cnpj e inscrição estadual de todos os clientes pessoa jurídica
SELECT
	c.nome, -- seleciona o nome da tabela cliente
    pj.cnpj, -- seleciona o CNPJ da tabela pessoa_juridica
    pj.inscricaoEstadual -- seleciona a inscrição estadual da tabela pessoa_juridica
FROM
	cliente c -- tabela cliente
INNER JOIN
	pessoa_juridica pj -- Junta com a tabela pessoa_juridica
    ON c.id = pj.id_cliente; -- a chave primária (id) de cliente deve ser igual à chave estrangeira (id_cliente) de pessoa_juridica.

-- 6. Mostrar o nome e a data de cadastro de todas as pessoas físicas ordenados por nome
SELECT 
	c.nome, -- Seleciona o nome do cliente
    c.dataCadastro -- Seleciona a data de cadastro do cliente
FROM 
	cliente c -- Tabela cliente
INNER JOIN
	pessoa_fisica pf -- Junta com a tabela pessoa_fisica
    ON c.id = pf.id_cliente -- A ligação garante que estou pegando só os clientes que tem um registro em pessoa_fisica
ORDER BY
	c.nome ASC; -- -- Ordena o resultado pelo nome do cliente, de A a Z
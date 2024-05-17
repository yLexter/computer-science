-- Create
CREATE TABLE zona (
	nome VARCHAR(255),
	num_zona INT,
	qnt_eleitor INT,
	CONSTRAINT PRIMARY KEY (num_zona)
);

CREATE TABLE partido (
	num_partido INT,
	data_criacao DATE,
	nome VARCHAR(255),
	CONSTRAINT PRIMARY KEY (num_partido)
);

CREATE TABLE cargo (
	cod_cargo VARCHAR(11),
	nome VARCHAR(255),
	ano_eleicao INT UNIQUE NOT NULL,
	CONSTRAINT PRIMARY KEY (cod_cargo)
);

CREATE TABLE secao (
	zona_id INT NOT NULL,
	nome_secao VARCHAR(255),
	qnt_eleitores INT,
	num_secao INT,
	CONSTRAINT PRIMARY KEY (num_secao),
	CONSTRAINT FOREIGN KEY (zona_id) REFERENCES zona (num_zona) 
);

CREATE TABLE votacao (
	cod_votacao VARCHAR(11),
	qnt_votos INT,
	secao_id INT,
	ano INT UNIQUE NOT NULL,
	CONSTRAINT PRIMARY KEY (cod_votacao),
	CONSTRAINT FOREIGN KEY (secao_id) REFERENCES secao (num_secao) 
);

CREATE TABLE candidato (
	nome VARCHAR(255),
	cod_cargo VARCHAR(11),
	num_partido INT,
	num_candidato INT,
	CONSTRAINT PRIMARY KEY (num_candidato),
	CONSTRAINT FOREIGN KEY (cod_cargo) REFERENCES cargo (cod_cargo),
	CONSTRAINT FOREIGN KEY (num_partido) REFERENCES partido (num_partido)
);

CREATE TABLE candidato_votacao (
	candidato_id INT,
	votacao_id VARCHAR(11),
	CONSTRAINT PRIMARY KEY (votacao_id, candidato_id),
	CONSTRAINT FOREIGN KEY (candidato_id) REFERENCES candidato (num_candidato),
	CONSTRAINT FOREIGN KEY (votacao_id) REFERENCES votacao (cod_votacao)
);

-- Drops
DROP TABLE candidato_votacao;
DROP TABLE candidato;
DROP TABLE votacao;
DROP TABLE secao;
DROP TABLE zona;
DROP TABLE cargo;
DROP TABLE partido;

-- A.
ALTER TABLE zona DROP COLUMN qnt_eleitor;

-- B.
CREATE INDEX index_not_clustred ON candidato (cod_cargo, num_partido);

-- C.
INSERT INTO zona (num_zona, nome) 
	VALUES (1, "Zona norte"), (2, "Zona sul"), (3, "Zona leste"), (4, "Zona oeste");

INSERT INTO secao (zona_id, nome_secao, qnt_eleitores, num_secao) 
VALUES (1, 'Seção NA', 100, 1),
       (1, 'Seção NB', 250, 2),
       (2, 'Seção SA', 1000, 3),
       (2, 'Seção SB', 3000, 6),
       (3, 'Seção LA', 5000, 4),
       (3, 'Seção LB', 20000, 7),
       (4, 'Seção OA', 10000, 5),
       (4, 'Seção OB', 10000, 8);

-- Testando...
INSERT INTO secao (zona_id, nome_secao, qnt_eleitores, num_secao) VALUES (1, NULL, 100, 50);

DELETE FROM secao WHERE num_secao = 50;

-- D.
ALTER TABLE partido MODIFY COLUMN nome VARCHAR(255) NOT NULL;
ALTER TABLE cargo MODIFY COLUMN nome VARCHAR(255) NOT NULL;
ALTER TABLE secao MODIFY COLUMN nome_secao VARCHAR(255) NOT NULL;
ALTER TABLE candidato MODIFY COLUMN nome VARCHAR(255) NOT NULL;
ALTER TABLE zona MODIFY COLUMN nome VARCHAR(255) NOT NULL;



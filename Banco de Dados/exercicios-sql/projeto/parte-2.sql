-- Tabela Paciente
CREATE TABLE Paciente (
ID_Paciente SERIAL PRIMARY KEY,
CPF VARCHAR(11) NOT NULL,
Nome VARCHAR(100),
Idade INT,
Altura DECIMAL(5, 2)
);
-- Tabela Nutricionista
CREATE TABLE Nutricionista (
ID_Nutricionista SERIAL PRIMARY KEY,
Nome VARCHAR(100),
CRN VARCHAR(20)
);
-- Tabela TipoAtividadeFisica
CREATE TABLE TipoAtividadeFisica (
ID SERIAL PRIMARY KEY,
Nome VARCHAR(100)
);
-- Tabela ProfissionalEdFisica
CREATE TABLE ProfissionalEdFisica (
ID_Profissional_Ed_Fisica SERIAL PRIMARY KEY,
Nome VARCHAR(100),
CREF VARCHAR(20)
);
-- Tabela GrupoMuscular
CREATE TABLE GrupoMuscular (
ID SERIAL PRIMARY KEY,
Nome VARCHAR(100)
);
-- Tabela MedidaCorporal
CREATE TABLE MedidaCorporal (
ID SERIAL PRIMARY KEY,
Nome VARCHAR(100)
);
-- Tabela Exame
CREATE TABLE Exame (
ID SERIAL PRIMARY KEY,
Nome VARCHAR(100)
);
-- Tabela ExercÃ-cio
CREATE TABLE ExercÃ-cio (
ID_ExercÃ-cio SERIAL PRIMARY KEY,
DescriÃ§Ã£o TEXT
);
-- Tabela PlanoSaÃºde
CREATE TABLE PlanoSaÃºde (
ID_PlanoSaude SERIAL PRIMARY KEY,
ID_Paciente INT not NULL,
Nome VARCHAR(100),
DescriÃ§Ã£o TEXT,
FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente)
);
-- Tabela Pacote
CREATE TABLE Pacote (
ID_Pacote SERIAL PRIMARY KEY,
ID_Paciente INT not NULL,
Custo DECIMAL(10, 2),
PerÃ-odo_Validade VARCHAR(50),
FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente)
);
-- Tabela Consulta
CREATE TABLE Consulta (
ID_Consulta SERIAL PRIMARY KEY,
ID_Paciente INT not NULL,
ID_Nutricionista INT NOT NULL,
PreÃ§o_PadrÃ£o DECIMAL(10, 2),
FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente),
FOREIGN KEY (ID_Nutricionista) REFERENCES
Nutricionista(ID_Nutricionista)
);
-- Tabela PlanoAlimentar
CREATE TABLE PlanoAlimentar (
ID_PlanoAlimentar SERIAL,
ID_Consulta INT not NULL UNIQUE,
DescriÃ§Ã£o TEXT,
PRIMARY KEY (ID_PlanoAlimentar),
FOREIGN KEY (ID_Consulta) REFERENCES Consulta(ID_Consulta)
);
-- Tabela RefeiÃ§Ã£o
CREATE TABLE RefeiÃ§Ã£o (
ID_RefeiÃ§Ã£o SERIAL PRIMARY KEY,
ID_Plano_Alimentar INT NOT NULL,
Calorias INT,
HorÃ¡rio TIME,
DescriÃ§Ã£o TEXT,
FOREIGN KEY (ID_Plano_Alimentar) REFERENCES
PlanoAlimentar(ID_PlanoAlimentar)
);
-- Tabela Perfil
CREATE TABLE Perfil (
ID_Perfil SERIAL,
ID_Paciente INT NOT NULL,
Data_inicio DATE,
Data_fim DATE,
Foto TEXT,
PRIMARY KEY (ID_Perfil, ID_Paciente),
FOREIGN KEY (ID_Paciente) REFERENCES Paciente(ID_Paciente)
);
-- Tabela Anamnese
CREATE TABLE Anamnese (
ID_Anamnese SERIAL,
ID_Consulta INT NOT NULL UNIQUE,
Data_Anamnese DATE,
IMC DECIMAL(5, 2),
Peso DECIMAL(5, 2),
Desejo TEXT,
HistÃ³rico_MÃ©dico TEXT,
GorduraTotal DECIMAL(5, 2),
GorduraVisceral DECIMAL(5, 2),
PRIMARY KEY (ID_Anamnese),
FOREIGN KEY (ID_Consulta) REFERENCES Consulta(ID_Consulta)
);
-- Tabela Treino
CREATE TABLE Treino (
ID_Treino SERIAL,
ID_Consulta INT not NULL UNIQUE,
ID_Profissional_Ed_Fisica INT NOT NULL,
Data_Treino DATE,
DescriÃ§Ã£o TEXT,
PRIMARY key (ID_Treino),
FOREIGN KEY (ID_Consulta) REFERENCES Consulta(ID_Consulta),
FOREIGN KEY (ID_Profissional_Ed_Fisica) REFERENCES
ProfissionalEdFisica(ID_Profissional_Ed_Fisica)
);
-- Tabela Treino_Contem_ExercÃ-cio
CREATE TABLE Treino_Contem_ExercÃ-cio (
ID_Treino INT,
ID_ExercÃ-cio INT,
PRIMARY KEY (ID_Treino, ID_ExercÃ-cio),
FOREIGN KEY (ID_Treino) REFERENCES Treino(ID_Treino),
FOREIGN KEY (ID_ExercÃ-cio) REFERENCES ExercÃ-cio(ID_ExercÃ-cio)
);
-- Tabela Pagamento
CREATE TABLE Pagamento (
ID_Pagamento SERIAL PRIMARY KEY,
ID_Consulta INT NOT NULL,
Tipo VARCHAR(50),
Valor DECIMAL(10, 2),
Data_Pagamento DATE,
FOREIGN KEY (ID_Consulta) REFERENCES Consulta(ID_Consulta)
);
-- Tabela AnamneseTipoAtividadeFisica
CREATE TABLE AnamneseTipoAtividadeFisica (
ID_Anamnese INT,
ID_TipoAtividadeFisica INT,
PRIMARY KEY (ID_Anamnese, ID_TipoAtividadeFisica),
FOREIGN KEY (ID_Anamnese) REFERENCES Anamnese(ID_Anamnese),
FOREIGN KEY (ID_TipoAtividadeFisica) REFERENCES
TipoAtividadeFisica(ID)
);
-- Tabela AnamneseMedidaCorporal
CREATE TABLE AnamneseMedidaCorporal (
ID_Anamnese INT,
ID_MedidaCorporal INT,
Valor DECIMAL(10, 2),
PRIMARY KEY (ID_Anamnese, ID_MedidaCorporal),
FOREIGN KEY (ID_Anamnese) REFERENCES Anamnese(ID_Anamnese),
FOREIGN KEY (ID_MedidaCorporal) REFERENCES MedidaCorporal(ID)
);
-- Tabela AnamneseExame
CREATE TABLE AnamneseExame (
ID_Anamnese INT,
ID_Exame INT,
Resultado TEXT,
PRIMARY KEY (ID_Anamnese, ID_Exame),
FOREIGN KEY (ID_Anamnese) REFERENCES Anamnese(ID_Anamnese),
FOREIGN KEY (ID_Exame) REFERENCES Exame(ID)
);
-- Tabela ExercicioGrupoMuscular
CREATE TABLE ExercicioGrupoMuscular (
ID_Exercicio INT,
ID_GrupoMuscular INT,
SERIES INT,
REPETICOES INT,
PRIMARY KEY (ID_Exercicio, ID_GrupoMuscular),
FOREIGN KEY (ID_Exercicio) REFERENCES ExercÃ-cio(ID_ExercÃ-cio),
FOREIGN KEY (ID_GrupoMuscular) REFERENCES GrupoMuscular(ID)
);
-- Inserir dados na tabela Paciente
INSERT INTO Paciente (CPF, Nome, Idade, Altura) VALUES
('12345678901', 'JoÃ£o da Silva', 30, 1.75),
('23456789012', 'Maria Oliveira', 25, 1.65),
('34567890123', 'Carlos Pereira', 40, 1.80);
-- Inserir dados na tabela Nutricionista
INSERT INTO Nutricionista (Nome, CRN) VALUES
('Dra. Ana Costa', 'CRN12345'),
('Dr. Paulo Lima', 'CRN67890'),
('Dra. Juliana Santos', 'CRN54321');
-- Inserir dados na tabela TipoAtividadeFisica
INSERT INTO TipoAtividadeFisica (Nome) VALUES
('MusculaÃ§Ã£o'),
('Corrida'),
('NataÃ§Ã£o');
-- Inserir dados na tabela ProfissionalEdFisica
INSERT INTO ProfissionalEdFisica (Nome, CREF) VALUES
('Prof. JoÃ£o Mendes', 'CREF12345'),
('Prof. Marina Rocha', 'CREF67890'),
('Prof. Ricardo Alves', 'CREF54321');
-- Inserir dados na tabela GrupoMuscular
INSERT INTO GrupoMuscular (Nome) VALUES
('Bembros Superiores'),
('Bembros Inferiores'),
('TÃ³rax e AbdÃ´men');
-- Inserir dados na tabela MedidaCorporal
INSERT INTO MedidaCorporal (Nome) VALUES
('Coxas'),
('BÃ-ceps'),
('Busto'),
('Quadril');
-- Inserir dados na tabela Exame
INSERT INTO Exame (Nome) VALUES
('Hemograma'),
('Colesterol'),
('TriglicerÃ-deos'),
('Glicemia de Jejum'),
('Vitamina D'),
('TSH'),
('T4 livre');
-- Inserir dados na tabela ExercÃ-cio
INSERT INTO ExercÃ-cio (DescriÃ§Ã£o) VALUES
('Supino reto'),
('Agachamento livre'),
('Rosca direta');
-- Inserir dados na tabela PlanoSaÃºde
INSERT INTO PlanoSaÃºde (ID_Paciente, Nome, DescriÃ§Ã£o) VALUES
(1, 'Plano Ouro', 'Cobertura completa'),
(2, 'Plano Prata', 'Cobertura intermediÃ¡ria'),
(3, 'Plano Bronze', 'Cobertura bÃ¡sica');
-- Inserir dados na tabela Pacote
INSERT INTO Pacote (ID_Paciente, Custo, PerÃ-odo_Validade) VALUES
(1, 500.00, '6 meses'),
(2, 300.00, '3 meses'),
(3, 1000.00, '12 meses');
-- Inserir dados na tabela Consulta
INSERT INTO Consulta (ID_Paciente, ID_Nutricionista, PreÃ§o_PadrÃ£o)
VALUES
(1, 1, 200.00),
(2, 2, 180.00),
(3, 3, 220.00);
-- Inserir dados na tabela PlanoAlimentar
INSERT INTO PlanoAlimentar (ID_Consulta, DescriÃ§Ã£o) VALUES
(1, 'Dieta para ganho de massa muscular'),
(2, 'Dieta para perda de peso'),
(3, 'Dieta equilibrada para manutenÃ§Ã£o');
-- Inserir dados na tabela RefeiÃ§Ã£o
INSERT INTO RefeiÃ§Ã£o (ID_Plano_Alimentar, Calorias, HorÃ¡rio,
DescriÃ§Ã£o) VALUES
(1, 500, '08:00', 'CafÃ© da manhÃ£'),
(2, 700, '12:00', 'AlmoÃ§o'),
(3, 400, '19:00', 'Jantar');
-- Inserir dados na tabela Perfil
INSERT INTO Perfil (ID_Paciente, Data_inicio, Data_fim, Foto) VALUES
(1, '2023-01-01', '2023-12-31', 'joao_silva.jpg'),
(2, '2023-02-01', '2023-11-30', 'maria_oliveira.jpg'),
(3, '2023-03-01', '2024-02-28', 'carlos_pereira.jpg');
-- Inserir dados na tabela Anamnese
INSERT INTO Anamnese (ID_Consulta, Data_Anamnese, IMC, Peso, Desejo,
HistÃ³rico_MÃ©dico, GorduraTotal, GorduraVisceral) VALUES
(1, '2023-01-10', 24.5, 75.0, 'Ganhar massa muscular', 'Nenhum', 20.0,
5.0),
(2, '2023-02-10', 28.0, 70.0, 'Perder peso', 'HipertensÃ£o', 25.0, 8.0),
(3, '2023-03-10', 22.0, 65.0, 'Manter peso', 'Nenhum', 18.0, 4.0);
-- Inserir dados na tabela Treino
INSERT INTO Treino (ID_Consulta, ID_Profissional_Ed_Fisica, Data_Treino,
DescriÃ§Ã£o) VALUES
(1, 1, '2023-01-15', 'Treino para hipertrofia'),
(2, 2, '2023-02-15', 'Treino para emagrecimento'),
(3, 3, '2023-03-15', 'Treino de manutenÃ§Ã£o');
-- Inserir dados na tabela Treino_Contem_ExercÃ-cio
INSERT INTO Treino_Contem_ExercÃ-cio (ID_Treino, ID_ExercÃ-cio) VALUES
(1, 1),
(2, 2),
(3, 3);
-- Inserir dados na tabela Pagamento
INSERT INTO Pagamento (ID_Consulta, Tipo, Valor, Data_Pagamento) VALUES
(1, 'CartÃ£o de CrÃ©dito', 200.00, '2023-01-20'),
(2, 'Boleto BancÃ¡rio', 180.00, '2023-02-20'),
(3, 'Dinheiro', 220.00, '2023-03-20');
-- Inserir dados na tabela AnamneseTipoAtividadeFisica
INSERT INTO AnamneseTipoAtividadeFisica (ID_Anamnese,
ID_TipoAtividadeFisica) VALUES
(1, 1),
(2, 2),
(3, 3);
-- Inserir dados na tabela AnamneseMedidaCorporal
INSERT INTO AnamneseMedidaCorporal (ID_Anamnese, ID_MedidaCorporal, Valor)
VALUES
(1, 1, 60.0),
(2, 2, 30.0),
(3, 3, 90.0);
-- Inserir dados na tabela AnamneseExame
INSERT INTO AnamneseExame (ID_Anamnese, ID_Exame, Resultado) VALUES
(1, 1, 'Normal'),
(2, 2, 'Elevado'),
(3, 3, 'Normal');
-- Inserir dados na tabela ExercicioGrupoMuscular
INSERT INTO ExercicioGrupoMuscular (ID_Exercicio, ID_GrupoMuscular,
SERIES, REPETICOES) VALUES
(1, 1, 3, 10),
(2, 2, 4, 12),
(3, 3, 3, 15);
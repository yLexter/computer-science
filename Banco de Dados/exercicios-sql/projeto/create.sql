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

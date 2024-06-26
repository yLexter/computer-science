-- ------------- Scripts de Query ---------------------------------

-- Letra A

SELECT p.Nome as Profissional
FROM ProfissionalEdFisica p
JOIN Treino t ON p.ID_Profissional_Ed_Fisica = t.ID_Profissional_Ed_Fisica
JOIN Consulta c ON t.ID_Consulta = c.ID_Consulta
JOIN Paciente pa ON c.ID_Paciente = pa.ID_Paciente
WHERE pa.Nome = 'Maria';

-- Letra B

SELECT e.Descrição as Exercicios_de_João
FROM Exercício e
JOIN Treino_Contem_Exercício te ON e.ID_Exercício = te.ID_Exercício
JOIN Treino t ON te.ID_Treino = t.ID_Treino
JOIN Consulta c ON t.ID_Consulta = c.ID_Consulta
JOIN Paciente pa ON c.ID_Paciente = pa.ID_Paciente
WHERE pa.Nome = 'João';

-- Letra C
SELECT r.Descrição as REFEICAO_DE_JOAO
FROM Refeição r
JOIN PlanoAlimentar pa ON r.ID_Plano_Alimentar = pa.ID_PlanoAlimentar
JOIN Consulta c ON pa.ID_Consulta = c.ID_Consulta
JOIN Paciente p ON c.ID_Paciente = p.ID_Paciente
JOIN Nutricionista n ON c.ID_Nutricionista = n.ID_Nutricionista
WHERE p.Nome = 'João' AND n.Nome = 'Eduardo';

-- Letra D
SELECT p.Nome as Profissional_Educação_Física, COUNT(t.ID_Treino) AS Total_Treinos
FROM ProfissionalEdFisica p
JOIN Treino t ON p.ID_Profissional_Ed_Fisica = t.ID_Profissional_Ed_Fisica
GROUP BY p.Nome;

-- Letra E
CREATE OR REPLACE FUNCTION media_calorias_refeicoes()
RETURNS TABLE (
    Nome VARCHAR(100),
    Média_Calorias NUMERIC(10, 2)
) AS $$
BEGIN
    RETURN QUERY
    SELECT p.Nome, COALESCE(AVG(r.Calorias), 0)::NUMERIC(10, 2) AS Média_Calorias
    FROM Paciente p
    LEFT JOIN Consulta c ON p.ID_Paciente = c.ID_Paciente
    LEFT JOIN PlanoAlimentar pa ON c.ID_Consulta = pa.ID_Consulta
    LEFT JOIN Refeição r ON pa.ID_PlanoAlimentar = r.ID_Plano_Alimentar
    GROUP BY p.Nome;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM media_calorias_refeicoes();

-- Letra F
CREATE OR REPLACE FUNCTION valor_medio_pacotes()
RETURNS TABLE (
    Nome VARCHAR(100),
    Valor_Medio_Pago DECIMAL(10, 2)
) AS $$
BEGIN
    RETURN QUERY
    SELECT p.Nome, COALESCE(AVG(pa.Custo), 0)::DECIMAL(10, 2) AS Valor_Medio_Pago
    FROM Paciente p
    LEFT JOIN Pacote pa ON p.ID_Paciente = pa.ID_Paciente
    GROUP BY p.Nome;
END;
$$ LANGUAGE plpgsql;

SELECT * FROM valor_medio_pacotes();

-- Letra G
SELECT 
    n.Nome AS Nome_Nutricionista,
    COALESCE(SUM(p.Custo), 0) AS Total_Pago_Mensalidades
FROM 
    Nutricionista n
LEFT JOIN 
    Consulta c ON n.ID_Nutricionista = c.ID_Nutricionista
LEFT JOIN 
    Paciente pa ON c.ID_Paciente = pa.ID_Paciente
LEFT JOIN 
    Pacote p ON pa.ID_Paciente = p.ID_Paciente
GROUP BY 
    n.Nome;

-- Letra H
WITH ClienteTreinos AS (
    SELECT
        p.ID_Paciente,
        p.Nome AS Nome_Cliente,
        COUNT(t.ID_Treino) AS Quantidade_Treinos
    FROM
        Paciente p
    LEFT JOIN
        Consulta c ON p.ID_Paciente = c.ID_Paciente
    LEFT JOIN
        Treino t ON c.ID_Consulta = t.ID_Consulta
    GROUP BY
        p.ID_Paciente, p.Nome
)
SELECT
    Nome_Cliente,
    Quantidade_Treinos
FROM
    ClienteTreinos
ORDER BY
    Quantidade_Treinos DESC
LIMIT 3;

-- ----------------------------------------------------





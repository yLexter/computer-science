-- 1. Procedure

CREATE PROCEDURE insere_empregado ( 
    matr integer,
    nome character varying(50),
    endereco character varying(50),
    data_lotacao timestamp,
    lotacao_cod_dep integer,
    gerencia_cod_dep integer,
    lotacao_div integer,
    gerencia_div integer,
    lista_vencimentos integer[],
    lista_descontos integer[]
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO depto.empregado (matr, nome, endereco, data_lotacao, lotacao, gerencia_cod_dep, lotacao_div, gerencia_div)
    VALUES (matr, nome, endereco, data_lotacao, lotacao_cod_dep, gerencia_cod_dep, lotacao_div, gerencia_div);

    IF lista_vencimentos IS NOT NULL THEN
        FOR i IN 1 .. array_length(lista_vencimentos, 1) LOOP
            INSERT INTO depto.emp_venc (cod_venc, matr)
            VALUES (lista_vencimentos[i], matr);
        END LOOP;
    END IF;

    IF lista_descontos IS NOT NULL THEN
        FOR i IN 1 .. array_length(lista_descontos, 1) LOOP
            INSERT INTO depto.emp_desc (cod_desc, matr)
            VALUES (lista_descontos[i], matr);
        END LOOP;
    END IF;
END;
$$;

-- 2. Função

CREATE FUNCTION lista_empregados_salarios_departamento (
    salario_inicial numeric,
    salario_final numeric,
    cod_depto integer
)
RETURNS TABLE (
  matricula int,
  nome character varying(50),
  nome_divisao character varying(50),
  salario_liquido numeric
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT 
        e.matr AS matricula,
        e.nome,
        d.nome AS nome_divisao,
        dep.nome as nome_departamente,
        SUM(v.valor) - COALESCE(SUM(ds.valor), 0) AS salario_liquido
    FROM 
        depto.empregado e
    INNER JOIN 
        depto.divisao d ON e.lotacao_div = d.cod_divisao
    LEFT JOIN 
        depto.emp_venc ev ON e.matr = ev.matr
    LEFT JOIN 
        depto.vencimento v ON ev.cod_venc = v.cod_venc
    LEFT JOIN 
        depto.emp_desc ed ON e.matr = ed.matr
    LEFT JOIN 
        depto.desconto ds ON ed.cod_desc = ds.cod_desc
    LEFT JOIN 
    	depto.departamento dep ON dep.cod_dep = d.cod_dep    
    GROUP BY 
        e.matr, e.nome, d.nome, dep.nome
END;
$$;




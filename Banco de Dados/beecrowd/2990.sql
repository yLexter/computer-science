SELECT e.cpf, e.enome, d.dnome 
FROM empregados e INNER JOIN departamentos d
ON e.dnumero = d.dnumero 
WHERE NOT EXISTS (
    SELECT cpf_emp FROM trabalha t
    WHERE e.cpf = t.cpf_emp
) ORDER BY e.cpf
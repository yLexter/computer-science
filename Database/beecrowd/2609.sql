SELECT cat.name as name, SUM(prod.amount) as sum 
FROM categories cat INNER JOIN products prod ON
prod.id_categories = cat.id
GROUP BY cat.name
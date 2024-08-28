SELECT prod.name, prov.name FROM providers prov
INNER JOIN products prod
ON prov.id = prod.id_providers
WHERE prod.id_categories = 6
SELECT
name as name, 
cast(EXTRACT(DAY FROM payday) AS INT) as day
FROM loan
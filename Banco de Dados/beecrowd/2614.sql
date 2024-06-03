SELECT 
cust.name as name,  
rent.rentals_date as rentals_date
FROM rentals rent INNER JOIN customers as cust 
ON rent.id_customers = cust.id
WHERE EXTRACT(MONTH FROM rent.rentals_date) = 9
AND EXTRACT(YEAR FROM rent.rentals_date) = 2016
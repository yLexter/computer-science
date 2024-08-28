# Exercise 1 - Stored SQL procedures and functions

- Considering the beecrowd 2991 exercise data model

1. Create a stored procedure to insert an employee into the system. The stored procedure must receive information from the employee table, a set (it can be an array of integers, or another data structure you wish to use) of expiration codes, a set (it can be an array of integers, or another data structure you wish to use) ) of discount codes. The stored procedure must insert the employee, all their respective salaries and deductions.

 Stored Procedure ->
 insert_employee(matr integer, name character varying(50), address character varying(50), data_lotacao timestamp, lotacao_cod_dep integer, manage_cod_dep integer, lotacao_div integer, manage_div integer, increase_tx numeric, list_vencimentos[] integer, list_descontos[] integer)

 The only really mandatory attribute would be the matr (registration number)

2. Create an sql function to list the registration number, name, department name, and division name of employees who have NET salaries between a certain range of values. The function must receive as parameters the range of values ​​and the code of the department in which you want to select the employees.

 Function ->
 list_employees_salaries_department(numeric initial_salary, numeric_final_salary, integer department_code): --> table (registration, name, name of employee division, net salary)
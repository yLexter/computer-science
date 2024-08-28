# Monitoring Physical Activity and Nutrition for the Well-Being of Patients Nutrition

## Duo: **Lisa Costa** ([GitHub](https://github.com/lisacsiqueira))

## Description

- Your consultations consist of weighing, measuring abdominal circumference and other measurements (thighs, biceps, bust, hips), age, type of physical activity performed by the patient, body mass index, visceral fat and total fat). After the first consultation, the patient must undergo a checkup, presenting the results of some tests (blood count, cholesterol, triglycerides, fasting blood glucose, vitamin D, TSH and free T4).

- Based on this information, the nutritionist team draws up a meal plan with the number of daily meals, the time for each one, and what should be consumed in daily calories. This plan must be printed and stored for future reference.

- At the end of a month, the patient returns and the measurements initially collected are updated (everything must be measured again), and the eating plan is adjusted based on them. It is necessary to maintain a history of this information, per patient. The patient's needs or desires (gaining muscle mass or losing weight) are also noted. Consultations are charged per month, with the right to return every 30 days for reevaluation. Patients can have private consultations or through a health plan.

- It is necessary to maintain a record of accredited health plans. Furthermore, there is the possibility for the customer to subscribe to packages, which provide discounts on monthly fees and build loyalty. Therefore, it must be possible for the system to register these packages (cost and validity period).

Payments can be made by card or in cash. Flávia also wants the reporting option in the system.

Reporting options that interest you are:

1. Number of patients per health plan who pay for consultations, with information regarding the amounts that are transferred by plan and the total received for consultations

2. Progress reports per patient at each appointment (for example: how many kilos you lost, how much visceral fat you lost, percentage of total fat, BMI), graphs are desired for each of these individual reports.

### Physical activities

- Integrated management system with the gym and nutritionist team. It has a system of cards, which must be taken by patients whenever they go to train, to monitor their training. It happens that chips get torn or lost and customers are not happy about it. Therefore, the system needs to help patients follow their training via cell phone, or a desktop system that gives the client the option to print their training before carrying out it.

- Before starting any physical activity program, the patient must fill out a supplementary anamnesis form to the one filled out for the nutritional module, where their name, age, objective of carrying out the physical activity, and health problems that the patient has are noted. already have, if you are undergoing medical treatment or use any ongoing medication and if you are or were a smoker. The system needs to maintain data collected from customer measurements such as weight and measurements in centimeters of: chest, waist, upper abdomen, hip, right arm, right forearm, left arm, left forearm, upper right thigh, middle right thigh, upper left thigh , middle left thigh, left calf, right calf and date the measurements were taken.

- Within a period defined by physical educators, clients must redo their physical assessment and the data collected must be registered in the system, and compared with previous data so that the client knows where they are improving and where they need to improve. Furthermore, the exercises performed must be recorded in the system at each assessment.

- Exercises must be registered by day of the week that they are performed, which equipment is used, which muscle groups they work, number of sets and repetitions per set (for example, a client performs leg exercises on Monday, then he uses guided squats, 4 sets of 15 repetitions each, seated calf raises, 4 sets of 10 repetitions each, and so on). All of this must be registered in the system.

- The system must also allow the customer to register their profile with photo, with current contract, start and end date of the contract and possible promotions that the customer can join.

---

## Questions

### 1st Part - Entity & Relationship Model

- Prepare DER, Relational Project and Data Dictionaries

### 2nd Part - Create

- Write table creation scripts

### 3 Part - Insert

- Write data insertion scripts

### 4th Question - Query

- List the candidates by name and CPF who obtained votes in the city of "Campina Grande" and who spent more than R$5,000.00 on the campaign;
- List candidates by name and CPF and party code, total declaredo, total received in donations and total spent on campaigns that had more than 500 votes in the 2018 election in the city of Campina Grande;
- List the names of all cities and their respective total votes in the 2018 election for the candidate “João”.
- Select the total party donation received by candidates from the ‘CDU’ party.
- Present a list of the 3 parties that received the highest number of party funds
- List the 5 best-voted parties in the 2018 election in Paraíba (only consider candidates whose registration is valid).
- List the 10 richest candidates who were elected in Paraíba in 2018 (only consider candidates whose records are valid)
- Create a function that returns a list of all cities and their respective number of votes for candidates from the ‘CDU’ party. Order by the number of valid votes.
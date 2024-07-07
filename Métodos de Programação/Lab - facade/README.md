#Lab7: Facade

The activity consisted of implementing the General Information System (GIS) used at UEPB, which comprises several subsystems responsible for activities related to specific sectors of the university.

## Activity description

We implemented GIS using the Facade design pattern, which provides a unified interface for a set of interfaces in a subsystem. This simplifies use and communication with the different GIS subsystems, such as:

- **Administrative:** Scheduled meetings with the board, interviews.
- **Financial:** Balance of accounts, payroll.
- **Teachers:** Allocation by subject, time spent at home.
- **Students:** History and RDM.
- **Warehouse:** Stock, purchase order.
- **Infrastructure:** Allocation of rooms.

The Facade pattern allowed us to encapsulate the complexity of interactions between GIS subsystems, providing a simplified interface for use by system users.

## Implemented Features

- Unified interface for accessing GIS subsystems.
- Methods for obtaining administrative, financial information related to teachers, students, warehouse and infrastructure.

## Project Structure

The project is structured as follows:

## Admin and School
- `entities`: Contains the classes of geometric figures.
- `facades`: Implementation of facade to combine features.
- `services`: Services of a given class.
- `tests`: All figure and facade tests.
- `errors`: Custom exceptions.
- `Main`: Creation and results of the facade.